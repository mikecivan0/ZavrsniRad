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
        return session.createQuery("from persons").list();
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
        createExistsControll();
    }

    @Override
    protected void updateControll() throws BaseException {
        notEmptyControll("FirstName");
        notEmptyControll("LastName");
        lengthControll("FirstName",25);
        lengthControll("LastName",25);
        lengthControll("Address",100);
        lengthControll("PhoneNr",50);
        lengthControll("Email",50);
        updateExistsControll();        
    }

    @Override
    protected void deleteControll() throws BaseException {
        Long personExistsAsUser = (Long) session.createQuery(
                "select count(id) from users where person_id=:id")
                .setParameter("id", entity.getId())
                .uniqueResult();
        
        if(personExistsAsUser!=0){
            throw new BaseException("Person cannot be deleted because it is a user");
        }
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
            Method method = Person.class.getDeclaredMethod("get" + variable, null);
            text = (String) method.invoke(entity, null);           
        } catch (Exception ex) {
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }        
       return text;
    }
    
    private void createExistsControll() throws BaseException{         
        Long recordExists = (Long) session.createQuery(
                "select count(id) from persons where "
                        + "firstName=:firstName "
                        + "and lastName=:lastName "
                        + "and phoneNr=:phoneNr "
                        + "and email=:email "
                        + "and address=:address")
               .setParameter("firstName", entity.getFirstName())
               .setParameter("lastName", entity.getLastName())
               .setParameter("phoneNr", entity.getPhoneNr())
               .setParameter("email", entity.getEmail())
               .setParameter("address", entity.getAddress())
               .uniqueResult();      
        if(recordExists!=0){
            throw new BaseException("Person already exists in database");
        }
    }
    
    private void updateExistsControll() throws BaseException{         
       Long personExists = (Long) session.createQuery(
                "select count(id) from persons where "
                        + "firstName=:firstName "
                        + "and lastName=:lastName "
                        + "and phoneNr=:phoneNr "
                        + "and email=:email "
                        + "and address=:address "
                        + "and id!=:id")
               .setParameter("firstName", entity.getFirstName())
               .setParameter("lastName", entity.getLastName())
               .setParameter("phoneNr", entity.getPhoneNr())
               .setParameter("email", entity.getEmail())
               .setParameter("address", entity.getAddress())
               .setParameter("id", entity.getId())
               .uniqueResult();        
        if(personExists!=0){
            throw new BaseException("Person already exists in database");
        }
    }
}
