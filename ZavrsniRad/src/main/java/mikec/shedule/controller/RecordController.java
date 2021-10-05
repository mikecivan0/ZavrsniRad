/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.controller;

import java.util.List;
import mikec.shedule.model.Record;
import mikec.shedule.model.User;
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
    protected void createControll() throws BaseException {
        createRecordExistsControll();
    }
    
    @Override
    protected void updateControll() throws BaseException {       
        updateRecordExistsControll();
    }

    @Override
    protected void deleteControll() throws BaseException {
       
    }   
 
    public List<Integer> getYears(){            
        return session.createNativeQuery("select distinct YEAR(date) as year from records")
                .list();
    }
    
    public List<Integer> getMonths(String year){            
        return session.createNativeQuery("select distinct MONTH(date) as month "
                + "from records where YEAR(date)=:year")
                .setParameter("year", year)
                .list();
    }
    
    public List<User> getUsersForMonth(Integer year, Integer month){            
        return session.createNativeQuery("select distinct userId from records "
                + "where YEAR(date)=:year AND MONTH(date)=:month").
                setParameter(year, year)
                .setParameter("month", month)
                .list();
    }
    
    public List<Record> getRecordForDateAndUser(Integer year, Integer month){            
        return session.createNativeQuery("select distinct userId from records "
                + "where YEAR(date)=:year AND MONTH(date)=:month").
                setParameter(year, year)
                .setParameter("month", month)
                .list();
    }

    private void createRecordExistsControll() throws BaseException{         
        Long recordExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM records WHERE "
                        + "userId=:user "
                        + "AND date=:date")
               .setParameter("user", entity.getUser())
               .setParameter("date", entity.getDate())
               .uniqueResult();      
        if(recordExists!=0){
            throw new BaseException("Record for that user on that date allready exists");
        }
    }
    
    private void updateRecordExistsControll() throws BaseException{         
       Long personExists = (Long) session.createQuery(
                 "SELECT COUNT(id) FROM records WHERE "
                        + "userId=:user "
                        + "AND date=:date"
                         + "AND id!=:id")
               .setParameter("user", entity.getUser())
               .setParameter("date", entity.getDate())
               .setParameter("id", entity.getId())
               .uniqueResult(); 
        if(personExists!=0){
            throw new BaseException("Record for that user on that date allready exists");
        }
    }
    

    
}
