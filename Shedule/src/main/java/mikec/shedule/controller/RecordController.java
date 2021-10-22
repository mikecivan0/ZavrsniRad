/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.controller;

import java.util.Date;
import java.util.List;
import mikec.shedule.model.Record;
import mikec.shedule.util.Application;
import mikec.shedule.util.BaseException;


public class RecordController extends BaseController<Record>{

    public RecordController() throws BaseException {
        super();
    }
    
    @Override
    public List<Record> read() {
        return session.createQuery("FROM records").list();
    }
    
    @Override
    protected void createControl() throws BaseException {
        createRecordExistsControl();
    }
    
    @Override
    protected void updateControl() throws BaseException {       
        updateRecordExistsControl();
    }

    @Override
    protected void deleteControl() throws BaseException {
       
    }   
 
    public List<Integer> getYears(){            
        return session.createNativeQuery("SELECT DISTINCT YEAR(date) AS year FROM records")
                .list();
    }
    
    public List<Integer> getYearsForUser(){            
        return session.createNativeQuery("SELECT DISTINCT YEAR(date) FROM records "
                + "WHERE userId=:user")
                .setParameter("user", Application.getUser())
                .list();
    }
    
    public List<Integer> getMonths(String year){            
        return session.createNativeQuery("SELECT DISTINCT MONTH(date) as month "
                + "from records where YEAR(date)=:year")
                .setParameter("year", year)
                .list();
    }
    
    public List<Record> getRecordsForMonth(String year, String month){            
        return session.createQuery("FROM records "
                + "WHERE YEAR(date)=:year AND MONTH(date)=:month").
                setParameter("year", Integer.parseInt(year))
                .setParameter("month", Integer.parseInt(month))
                .list();
    }

    private void createRecordExistsControl() throws BaseException{         
        Long recordExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM records WHERE "
                        + "userId=:user "
                        + "AND date=:date")
               .setParameter("user", entity.getUser())
               .setParameter("date", entity.getDate())
               .uniqueResult();      
        if(recordExists!=0){
            throw new BaseException("Record for that user on that date already exists");
        }
    }
    
    private void updateRecordExistsControl() throws BaseException{         
       Long personExists = (Long) session.createQuery(
                 "SELECT COUNT(id) FROM records WHERE "
                        + "userId=:user "
                        + "AND date=:date "
                         + "AND id!=:id")
               .setParameter("user", entity.getUser())
               .setParameter("date", entity.getDate())
               .setParameter("id", entity.getId())
               .uniqueResult(); 
        if(personExists!=0){
            throw new BaseException("Record for that user on that date already exists");
        }
    }

    public Record findRecord(Record record) {
        try {
            return (Record) session.createQuery("FROM records WHERE "
                        + "userId=:user "
                        + "AND date=:date")
               .setParameter("user", record.getUser())
               .setParameter("date", record.getDate())
               .getSingleResult();  
        } catch (Exception e) {
            return new Record();
        }
          
    }

    public int getNumberOfWorkersForDate(Date date) {        
        Long numberOfEnrolledWorkers = (Long) session.createQuery(
                 "SELECT COUNT(id) FROM records WHERE "
                        + "date=:date "
                         + "AND (labelId=1 OR labelId=2)")
               .setParameter("date", date)
               .uniqueResult(); 
        return  Math.toIntExact(numberOfEnrolledWorkers);
    }

    public void deleteById(Long id) {
        session.beginTransaction();
        session.createQuery("DELETE FROM records WHERE id=:id")
               .setParameter("id", id)
               .executeUpdate(); 
        session.getTransaction().commit();
    }   
}
