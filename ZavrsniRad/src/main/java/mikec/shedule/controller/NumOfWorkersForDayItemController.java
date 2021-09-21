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
import mikec.shedule.model.NumOfWorkersForDayItem;
import mikec.shedule.util.BaseException;

/**
 *
 * @author Ivan
 */
public class NumOfWorkersForDayItemController extends BaseController<NumOfWorkersForDayItem>{

    @Override
    public List<NumOfWorkersForDayItem> read() {
        return session.createQuery("from NumOfWorkersForDayItem").list();
    }

    @Override
    protected void createControll() throws BaseException {
        notEmptyControll("Name");
        lengthControll("Name",20);
    }

    @Override
    protected void updateControll() throws BaseException {
        createControll();
    }

    @Override
    protected void deleteControll() throws BaseException {

    }
    
    private void lengthControll(String variable, Integer length) throws BaseException{   
        if(getVariable(variable).length()>length){
            throw new BaseException("Value of input field '" + variable + 
                    "' must not exceed " + length + " characters");
        }    
    }
    
    private void notEmptyControll(String variable) throws BaseException{        
        if(getVariable(variable)==null || getVariable(variable).trim().length()==0){
           throw new BaseException("Input '" + variable + "' cannot be empty");
       }    
    }
    
    private String getVariable(String variable){            
        String text = "";
        try { 
            Method method = NumOfWorkersForDayItem.class.getDeclaredMethod("get" + variable, null);
            text = (String) method.invoke(entity, null);           
        } catch (Exception ex) {
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }        
       return text;
    }
    
}
