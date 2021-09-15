/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.rasporedrada.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mikec.rasporedrada.model.Osoba;
import mikec.rasporedrada.util.BaseException;

/**
 *
 * @author Ivan
 */
public class PersonController extends BaseController<Osoba>{

    @Override
    public List<Osoba> read() {
        return session.createQuery("from Osoba").list();
    }

    @Override
    protected void createControll() throws BaseException {
        notEmptyControll("Ime");
        notEmptyControll("Prezime");
        lengthControll("Ime",25);
        lengthControll("Prezime",25);
        lengthControll("Adresa",100);
        lengthControll("Telefon",50);
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
            Method method = Osoba.class.getDeclaredMethod("get" + variable, null);
            text = (String) method.invoke(entity, null);           
        } catch (Exception ex) {
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }        
       return text;
    }
    
}