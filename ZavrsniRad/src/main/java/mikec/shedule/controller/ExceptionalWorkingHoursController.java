/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.controller;

import java.lang.reflect.Method;
import java.util.List;
import javax.swing.JOptionPane;
import mikec.shedule.model.ExceptionalWorkingHours;
import mikec.shedule.util.BaseException;

public class ExceptionalWorkingHoursController extends BaseController<ExceptionalWorkingHours>{

    public ExceptionalWorkingHoursController() throws BaseException {        
        super();
    }

    @Override
    public List<ExceptionalWorkingHours> read() {
        return session.createQuery("FROM exceptionalWorkingHours ORDER BY date").list();
    }

    @Override
    protected void createControl() throws BaseException {
        createExistsControl();
    }

    @Override
    protected void updateControl() throws BaseException {
        updateExistsControl();        
    }

    @Override
    protected void deleteControl() throws BaseException {
       
    }
    
    private void lengthControl(String variable, Integer length) throws BaseException{   
        if(getVariable(variable).length()>length){
            throw new BaseException("Value of input field '" + variable + 
                    "' must not exceed " + length + " characters");
        }    
    }
    
    private void notEmptyControl(String variable) throws BaseException{        
        if(getVariable(variable)==null || getVariable(variable).trim().length()==0){
           throw new BaseException("Input '" + variable + "' cannot be empty");
       }    
    }
    
    private String getVariable(String variable){            
        String text = "";
        try { 
            Method method = ExceptionalWorkingHours.class.getDeclaredMethod("get" + variable, null);
            text = (String) method.invoke(entity, null);           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }        
       return text;
    }
    
    private void createExistsControl() throws BaseException{         
        Long recordDateExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM exceptionalWorkingHours WHERE "
                        + "date=:date")
               .setParameter("date", entity.getDate())
               .uniqueResult();      
        if(recordDateExists!=0){
            throw new BaseException("Record with that date already exists");
        }
        
        Long recordFootnoteExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM exceptionalWorkingHours WHERE "
                        + "footnote=:footnote")
               .setParameter("footnote", entity.getFootnote())
               .uniqueResult();      
        if(recordFootnoteExists!=0){
            throw new BaseException("Record with that footnote already exists");
        }
    }
    
    private void updateExistsControl() throws BaseException{         
       Long recordDateExists = (Long) session.createQuery(
                 "SELECT COUNT(id) FROM exceptionalWorkingHours WHERE "
                        + "date=:date AND id!=:id")
               .setParameter("date", entity.getDate())
               .setParameter("id", entity.getId())
               .uniqueResult();        
        if(recordDateExists!=0){
            throw new BaseException("Record with that date already exists");
        }
        
        Long recordFootnoteExists = (Long) session.createQuery(
                 "SELECT COUNT(id) FROM exceptionalWorkingHours WHERE "
                        + "footnote=:footnote AND id!=:id")
               .setParameter("footnote", entity.getFootnote())
               .setParameter("id", entity.getId())
               .uniqueResult();        
        if(recordFootnoteExists!=0){
            throw new BaseException("Record with that footnote/description already exists");
        }
    }
}
