/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.controller;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import mikec.shedule.model.RegularWorkingHours;
import mikec.shedule.util.BaseException;
import mikec.shedule.util.Tools;

public class RegularWorkingHoursController extends BaseController<RegularWorkingHours>{

    public RegularWorkingHoursController() throws BaseException {
        super();
    }    
    
    @Override
    public List<RegularWorkingHours> read() {
        return session.createQuery("FROM regularWorkingHours GROUP BY starts ORDER BY starts").list();
    }
    
    public List<RegularWorkingHours> fetchAll() {
        return session.createQuery("FROM regularWorkingHours").list();
    }
    
     public List<RegularWorkingHours> fetchByStartsDate(Date startsDate) {
        return session.createQuery("FROM regularWorkingHours WHERE starts=:starts")
                .setParameter("starts", startsDate)
                .list();
    }

    @Override
    protected void createControll() throws BaseException {
        createExistsControll();       
    }

    @Override
    protected void updateControll() throws BaseException {
    
    }

    @Override
    protected void deleteControll() throws BaseException {      
       
    }
 
    public void delete(Date date){        
          try {
           session.beginTransaction();
           session.createQuery("DELETE FROM regularWorkingHours WHERE starts=:starts")
               .setParameter("starts", date)
               .executeUpdate(); 
           session.getTransaction().commit();             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }             
    }  
    
    private void createExistsControll() throws BaseException {
      List<Date> datesBetween = Tools.getDatesBetweenTwoDates(entity.getStarts(), entity.getExpires());
      for(Date date : datesBetween){      
        checkInsertOverlap(date);        
      }        
    }
    
    private void checkInsertOverlap(Date date) throws BaseException{
        Long startRecordExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM regularWorkingHours WHERE "
                        + ":date BETWEEN starts AND expires "
                        + "AND regularWorkingHoursItemId=:rwhItem")
               .setParameter("date", date)
               .setParameter("rwhItem", entity.getRegularWorkingHoursItem())
               .uniqueResult();        
        if(startRecordExists>0){
            throw new BaseException("Dates range overlap existing records");
        }
    }
    
    public void checkUpdateOverlap(Date starts, Date expires, Date selected) throws BaseException{
        List<Date> listOfDates = Tools.getDatesBetweenTwoDates(starts, expires);
        for(Date date : listOfDates){
             Long startRecordExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM regularWorkingHours WHERE "
                        + ":date BETWEEN starts AND expires "
                        + "AND id NOT IN "
                        + "(SELECT id FROM regularWorkingHours WHERE starts=:starts)")
               .setParameter("date", date)
               .setParameter("starts", selected)
               .uniqueResult();        
            if(startRecordExists>0){
               throw new BaseException("Dates range overlap existing records");   
            }          
        }        
    }
    
}
