/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.controller;

import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mikec.shedule.model.NumOfWorkersForDay;
import mikec.shedule.util.BaseException;

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
        Long startRecordExists = (Long) session.createQuery(
                "select count(id) from numsOfWorkersForDay where "
                        + ":starts between starts and expires "
                        + "and numOfWorkersForDayItemId=:nwfdItem")
               .setParameter("starts", entity.getStarts())
               .setParameter("nwfdItem", entity.getNumOfWorkersForDayItem())
               .uniqueResult();        
        if(startRecordExists>0){
            throw new BaseException("Starts date overlap existing records");
        }
        
        Long expiresRecordExists = (Long) session.createQuery(
                "select count(id) from numsOfWorkersForDay where "
                        + ":expires between starts and expires "
                        + "and numOfWorkersForDayItemId=:nwfdItem")
               .setParameter("expires", entity.getExpires())
               .setParameter("nwfdItem", entity.getNumOfWorkersForDayItem())
               .uniqueResult();        
        if(expiresRecordExists!=0){
            throw new BaseException("Expires date overlap existing records");
        }
    }
    
    private void updateExistsControll() throws BaseException {
        Long startRecordExists = (Long) session.createQuery(
                "select count(id) from numsOfWorkersForDay where "
                        + ":starts between starts and expires "
                        + "and id!=:id")
               .setParameter("starts", entity.getStarts())
               .setParameter("id", entity.getId())
               .uniqueResult();        
        if(startRecordExists!=0){
            throw new BaseException("Starts date overlap existing records");
        }
        
        Long expiresRecordExists = (Long) session.createQuery(
                "select count(id) from numsOfWorkersForDay where "
                        + ":expires between starts and expires "
                        + "and id!=:id")
               .setParameter("expires", entity.getExpires())
               .setParameter("id", entity.getId())
               .uniqueResult();        
        if(expiresRecordExists!=0){
            throw new BaseException("Expires date overlap existing records");
        }
    }
    
}
