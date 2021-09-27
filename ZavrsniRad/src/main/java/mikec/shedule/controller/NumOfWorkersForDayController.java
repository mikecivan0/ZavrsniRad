/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.controller;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import mikec.shedule.model.NumOfWorkersForDay;
import mikec.shedule.util.BaseException;
import mikec.shedule.util.Tools;

/**
 *
 * @author Ivan
 */
public class NumOfWorkersForDayController extends BaseController<NumOfWorkersForDay>{

    public NumOfWorkersForDayController() throws BaseException {
        super();
    }    
    
    @Override
    public List<NumOfWorkersForDay> read() {
        return session.createQuery("from numsOfWorkersForDay group by starts order by starts").list();
    }
    
    public List<NumOfWorkersForDay> fetchAll() {
        return session.createQuery("from numsOfWorkersForDay").list();
    }
    
     public List<NumOfWorkersForDay> fetchByStartsDate(Date startsDate) {
        return session.createQuery("from numsOfWorkersForDay where starts=:starts")
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
           session.createQuery("delete from numsOfWorkersForDay where starts=:starts")
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
                "select count(id) from numsOfWorkersForDay where "
                        + ":date between starts and expires "
                        + "and numOfWorkersForDayItemId=:nwfd")
               .setParameter("date", date)
               .setParameter("nwfd", entity.getNumOfWorkersForDayItem())
               .uniqueResult();        
        if(startRecordExists>0){
            throw new BaseException("Dates range overlap existing records");
        }
    }
    
    public boolean checkUpdateOverlap(Date dateFromList, Date selctedValueDate) throws BaseException{
        boolean bool = false;
        Long startRecordExists = (Long) session.createQuery(
                "select count(id) from numsOfWorkersForDay where "
                        + ":date between starts and expires and id not in "
                        + "(select id from numsOfWorkersForDay where starts=:starts)")
               .setParameter("date", dateFromList)
               .setParameter("starts", selctedValueDate)
               .uniqueResult();        
        if(startRecordExists>0){
            bool = true;
        }   
        return bool;
    }
    
}
