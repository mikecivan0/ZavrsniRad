/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.controller;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mikec.shedule.model.NumOfWorkersForDay;
import mikec.shedule.util.BaseException;
import mikec.shedule.util.Tools;

/**
 *
 * @author Ivan
 */
public class NumOfWorkersForDayController extends BaseController<NumOfWorkersForDay>{

    @Override
    public List<NumOfWorkersForDay> read() {
        return session.createQuery("from numsOfWorkersForDay group by starts order by starts").list();
    }
    
    public List<NumOfWorkersForDay> fetchAll() {
        return session.createQuery("from numsOfWorkersForDay").list();
    }

    @Override
    protected void createControll() throws BaseException {
        createExistsControll();       
    }

    @Override
    protected void updateControll() throws BaseException {
        updateExistsControll();
    }

    @Override
    protected void deleteControll() throws BaseException {
       
    }
    
    private void createExistsControll() throws BaseException {
      List<Date> datesBetween = Tools.getDaysBetweenDates(entity.getStarts(), entity.getExpires());
      boolean overlap = false;
      for(Date date : datesBetween){
          if(!overlap){
             checkInsertOverlap(date); 
          }          
      }        
    }
    
    private boolean checkInsertOverlap(Date date) throws BaseException{
        boolean bool = false;
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
        return bool;
    }
    
    private void updateExistsControll() throws BaseException {
//        Long startRecordExists = (Long) session.createQuery(
//                "select count(id) from numsOfWorkersForDay where "
//                        + ":starts between starts and expires "
//                        + "and id!=:id")
//               .setParameter("starts", entity.getStarts())
//               .setParameter("id", entity.getId())
//               .uniqueResult();        
//        if(startRecordExists!=0){
//            throw new BaseException("Starts date overlap existing records");
//        }
//        
//        Long expiresRecordExists = (Long) session.createQuery(
//                "select count(id) from numsOfWorkersForDay where "
//                        + ":expires between starts and expires "
//                        + "and id!=:id")
//               .setParameter("expires", entity.getExpires())
//               .setParameter("id", entity.getId())
//               .uniqueResult();        
//        if(expiresRecordExists!=0){
//            throw new BaseException("Expires date overlap existing records");
//        }
    }
    
}
