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
    public List<User> read() {
        return session.createQuery("FROM users").list();
    }
    
    @Override
    protected void createControll() throws BaseException {
        notEmptyControll("Username");
        notEmptyControll("Prs_id");
        lengthControll("Username", 50);
        createExistsControll();
    }
    
    @Override
    protected void updateControll() throws BaseException {
        notEmptyControll("Username");
        notEmptyControll("Prs_id");
        lengthControll("Username", 50);
        updateExistsControll();
    }

    @Override
    protected void deleteControll() throws BaseException {
        if(entity.getRecords().size()>0){
            throw new BaseException("User cannot be deleted because it is allready in shedule");
        }
    }   
    
    public User authorize(String username, String pass){
        User user = null;
        
        try {
            user = (User) session.createQuery("FROM users WHERE username=:username")
                .setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        
        if(user==null){
            return null;
        }
        
        return Tools.verifyPass(user.getPass(), pass) ? user : null;       
    }   
    
    private void lengthControll(String variable, Integer length) throws BaseException{   
        if(getVariable(variable).length()>length){
            throw new BaseException("Input lenth of '" + variable 
                    + "' cannot be greather from " + length + " chars");
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
            Method method = User.class.getDeclaredMethod("get" + variable, null);
            text = (String) method.invoke(entity, null);           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }        
       return text;
    }

    private void createExistsControll() throws BaseException{         
        Long recordExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM users WHERE "
                        + "username=:username")
               .setParameter("username", entity.getUsername())
               .uniqueResult();      
        if(recordExists!=0){
            throw new BaseException("Username allready taken");
        }
    }
    
    private void updateExistsControll() throws BaseException{         
       Long personExists = (Long) session.createQuery(
                 "SELECT COUNT(id) FROM users WHERE "
                        + "username=:username "
                         + "AND id=:id")
               .setParameter("username", entity.getUsername())
               .setParameter("id", entity.getId())
               .uniqueResult(); 
        if(personExists!=0){
            throw new BaseException("Username allready taken");
        }
    }
    
}
