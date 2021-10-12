/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.controller;

import java.lang.reflect.Method;
import java.util.List;
import javax.swing.JOptionPane;
import mikec.shedule.model.Person;
import mikec.shedule.util.BaseException;

public class PersonController extends BaseController<Person>{

    public PersonController() throws BaseException {        
        super();
    }

    @Override
    public List<Person> read() {
        return session.createQuery("FROM persons").list();
    }

    @Override
    protected void createControl() throws BaseException {
        notEmptyControl("FirstName");
        notEmptyControl("LastName");
        lengthControl("FirstName",25);
        lengthControl("LastName",25);
        lengthControl("Address",100);
        lengthControl("PhoneNr",50);
        lengthControl("Email",50);
        createExistsControl();
    }

    @Override
    protected void updateControl() throws BaseException {
        notEmptyControl("FirstName");
        notEmptyControl("LastName");
        lengthControl("FirstName",25);
        lengthControl("LastName",25);
        lengthControl("Address",100);
        lengthControl("PhoneNr",50);
        lengthControl("Email",50);
        updateExistsControl();        
    }

    @Override
    protected void deleteControl() throws BaseException {
        if(entity.getUser()!=null){
            throw new BaseException("Person cannot be deleted because it is a user");
        }
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
            Method method = Person.class.getDeclaredMethod("get" + variable, null);
            text = (String) method.invoke(entity, null);           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }        
       return text;
    }
    
    private void createExistsControl() throws BaseException{         
        Long recordExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM persons WHERE "
                        + "firstName=:firstName "
                        + "AND lastName=:lastName "
                        + "AND phoneNr=:phoneNr "
                        + "AND email=:email "
                        + "AND address=:address")
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
    
    public Person getLastAddedPerson(){
        List<Person> persons = read();
        return persons.get(persons.size()-1);
    }
    
    private void updateExistsControl() throws BaseException{         
       Long personExists = (Long) session.createQuery(
                 "SELECT COUNT(id) FROM persons WHERE "
                        + "firstName=:firstName "
                        + "AND lastName=:lastName "
                        + "AND phoneNr=:phoneNr "
                        + "AND email=:email "
                        + "AND address=:address "
                        + "AND id!=:id")
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
