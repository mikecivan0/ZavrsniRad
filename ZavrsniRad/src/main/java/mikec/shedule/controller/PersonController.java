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
import mikec.shedule.model.Person;
import mikec.shedule.util.BaseException;

/**
 *
 * @author Ivan
 */
public class PersonController extends BaseController<Person>{

    @Override
    public List<Person> read() {
        return session.createQuery("from Person").list();
    }

    @Override
    protected void createControll() throws BaseException {
        notEmptyControll("FirstName");
        notEmptyControll("LastName");
        lengthControll("FirstName",25);
        lengthControll("LastName",25);
        lengthControll("Address",100);
        lengthControll("PhoneNr",50);
        lengthControll("Email",50);
    }

    @Override
    protected void updateControll() throws BaseException {
        this.createControll();
    }

    @Override
    protected void deleteControll() throws BaseException {

    }

    private void lengthControll(String variable, Integer length) throws BaseException{   
        if(getVariable(variable).length()>length){
            throw new BaseException("Unos '" + variable + "' ne može biti duži od " + length + " znakova");
        }    
    }
    
    private void notEmptyControll(String variable) throws BaseException{        
        if(getVariable(variable)==null || getVariable(variable).trim().length()==0){
           throw new BaseException("Unos '" + variable + "' ne smije biti prazan");
       }    
    }
    
    private String getVariable(String variable){            
        String text = "";
        try { 
            Method method = Person.class.getDeclaredMethod("get" + variable, null);
            text = (String) method.invoke(entity, null);           
        } catch (Exception ex) {
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }        
       return text;
    }
    
}
