/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.controller;

import java.lang.reflect.Method;
import java.util.List;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;
import mikec.shedule.model.User;
import mikec.shedule.util.Tools;
import mikec.shedule.util.BaseException;


public class UserController extends BaseController<User>{

    public UserController() throws BaseException {
        super();
    }
    
    @Override
    protected void updateControll() throws BaseException {

    }

    @Override
    protected void deleteControll() throws BaseException {

    }

    @Override
    public List<User> read() {
        return session.createQuery("from User").list();
    }
    
    public User authorize(String username, String pass){
        User user = null;
        
        try {
            user = (User) session.createQuery("from users where username=:username")
                .setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        
        if(user==null){
            return null;
        }
        
        return Tools.verifyPass(user.getPass(), pass) ? user : null;       
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
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }        
       return text;
    }

    
}
