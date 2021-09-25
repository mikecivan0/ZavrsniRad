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
        notEmptyControll("Value");
        notEmptyControll("Starts");
        notEmptyControll("Expires");
        createExistsControll();       
    }

    @Override
    protected void updateControll() throws BaseException {
        notEmptyControll("Value");
        notEmptyControll("Starts");
        notEmptyControll("Expires");
        updateExistsControll();
    }

    @Override
    protected void deleteControll() throws BaseException {
       
    }
    
    private void notEmptyControll(String variable) throws BaseException{        
        if(getVariable(variable)==null || getVariable(variable).trim().length()==0){
           throw new BaseException("Input '" + variable + "' cannot be empty");
       }    
    }
    
    private String getVariable(String variable){            
        String text = "";
        try { 
            Method method = NumOfWorkersForDay.class.getDeclaredMethod("get" + variable, null);
            text = (String) method.invoke(entity, null);           
        } catch (Exception ex) {
            Logger.getLogger(NumOfWorkersForDayController.class.getName()).log(Level.SEVERE, null, ex);
        }        
       return text;
    }
    
    private void createExistsControll() throws BaseException {
        Long startRecordExists = (Long) session.createQuery(
                "select count(id) from numsOfWorkersForDay where "
                        + ":starts between starts and expires")
               .setParameter("starts", entity.getStarts())
               .uniqueResult();        
        if(startRecordExists!=0){
            throw new BaseException("Starts date overlap existing records");
        }
        
        Long expiresRecordExists = (Long) session.createQuery(
                "select count(id) from numsOfWorkersForDay where "
                        + ":expires between starts and expires")
               .setParameter("expires", entity.getExpires())
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
