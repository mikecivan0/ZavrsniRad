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
import org.mindrot.jbcrypt.BCrypt;
import mikec.rasporedrada.model.Korisnik;
import mikec.rasporedrada.util.BaseException;

/**
 *
 * @author Ivan
 */
public class UserController extends BaseController<Korisnik>{

    public UserController() {
    }
    
    

    @Override
    protected void updateControll() throws BaseException {

    }

    @Override
    protected void deleteControll() throws BaseException {

    }

    @Override
    public List<Korisnik> read() {
        return session.createQuery("from Korisnik").list();
    }
    
    public Korisnik authorize(String username, String pass){
        Korisnik user = null;
        
        try {
            user = (Korisnik) session.createQuery("from korisnici 0 where o.username=:username")
                .setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        
        if(user==null){
            return null;
        }
        return BCrypt.checkpw(pass, user.getPass()) ? user : null;       
    }

    @Override
    protected void createControll() throws BaseException {
        notEmptyControll("Username");
        notEmptyControll("Pass");
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
            Method method = Korisnik.class.getDeclaredMethod("get" + variable, null);
            text = (String) method.invoke(entity, null);           
        } catch (Exception ex) {
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }        
       return text;
    }

    
}
