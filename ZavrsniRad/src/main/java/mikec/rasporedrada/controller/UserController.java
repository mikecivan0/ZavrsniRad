/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.rasporedrada.controller;

import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import mikec.rasporedrada.model.User;
import mikec.rasporedrada.util.Alati;
import mikec.rasporedrada.util.BaseException;

/**
 *
 * @author Ivan
 */
public class UserController extends BaseController<User>{

    @Override
    protected void updateControll() throws BaseException {

    }

    @Override
    protected void deleteControll() throws BaseException {

    }

    @Override
    public List<User> read() {
        return session.createQuery("from Korisnik").list();
    }
    
    public User authorize(String username, String pass){
        User user = null;
        
        try {
            user = (User) session.createQuery("from korisnici where username=:username")
                .setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        
        if(user==null){
            return null;
        }
        
        return Alati.verifyPass(user.getPass(), pass) ? user : null;       
    }

    @Override
    protected void createControll() throws BaseException {
        notEmptyControll("Username");
        notEmptyControll("Pass");
        notEmptyControll("Prs_id");
        lengthControll("Username", 50);
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
            Method method = User.class.getDeclaredMethod("get" + variable, null);
            text = (String) method.invoke(entity, null);           
        } catch (Exception ex) {
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }        
       return text;
    }

    
}
