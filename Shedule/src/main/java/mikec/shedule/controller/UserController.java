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
    
    public List<User> getActiveUsers() {
        return session.createQuery("FROM users WHERE "
                + "active IS TRUE").list();
    }
    
    @Override
    protected void createControl() throws BaseException {
        notEmptyControl("Username");
        notEmptyControl("Prs_id");
        lengthControl("Username", 50);
        createUsernameExistsControl();
        createPersonIsUserControl();
        createPrsIdExistsControl();
    }
    
    @Override
    protected void updateControl() throws BaseException {
        notEmptyControl("Username");
        notEmptyControl("Prs_id");
        lengthControl("Username", 50);
        updateUsernameExistsControl();
        updatePersonIsUserControl();
        updatePrsIdExistsControl();
    }

    @Override
    protected void deleteControl() throws BaseException {
        if(entity.getRecords().size()>0){
            throw new BaseException("User cannot be deleted because it is already in shedule");
        }
    }   
    
    public User authorize(String username, String pass) throws BaseException {
        User user = null;
        
        try {
            user = (User) session.createQuery("FROM users WHERE username=:username")
                .setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            throw new BaseException(e.getMessage());
        }
        
        if(user==null){
            return null;
        }
        
        return Tools.verifyPass(user.getPass(), pass) ? user : null;       
    }   
    
    private void lengthControl(String variable, Integer length) throws BaseException{   
        if(getVariable(variable).length()>length){
            throw new BaseException("Input lenth of '" + variable 
                    + "' cannot be greather from " + length + " chars");
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
            Method method = User.class.getDeclaredMethod("get" + variable, null);
            text = (String) method.invoke(entity, null);           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }        
       return text;
    }

    private void createUsernameExistsControl() throws BaseException{         
        Long recordExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM users WHERE "
                        + "username=:username")
               .setParameter("username", entity.getUsername())
               .uniqueResult();      
        if(recordExists!=0){
            throw new BaseException("Username already taken");
        }
    }
    
    private void updateUsernameExistsControl() throws BaseException{         
       Long personExists = (Long) session.createQuery(
                 "SELECT COUNT(id) FROM users WHERE "
                        + "username=:username "
                         + "AND id!=:id")
               .setParameter("username", entity.getUsername())
               .setParameter("id", entity.getId())
               .uniqueResult(); 
        if(personExists!=0){
            throw new BaseException("Username already taken");
        }
    }
    
    private void createPersonIsUserControl() throws BaseException{         
        Long recordExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM users WHERE "
                        + "person_id=:person")
               .setParameter("person", entity.getPerson())
               .uniqueResult();      
        if(recordExists!=0){
            throw new BaseException("Person is already a user/admin");
        }
    }
    
    private void updatePersonIsUserControl() throws BaseException{         
        Long recordExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM users WHERE "
                        + "person_id=:person "
                        + "AND id!=:id")
               .setParameter("person", entity.getPerson())
               .setParameter("id", entity.getId())
               .uniqueResult();      
        if(recordExists!=0){
            throw new BaseException("Person is already a user/admin");
        }
    }
    
    private void createPrsIdExistsControl() throws BaseException{         
        Long recordExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM users WHERE "
                        + "prs_id=:prsId")
               .setParameter("prsId", entity.getPrs_id())
               .uniqueResult();      
        if(recordExists!=0){
            throw new BaseException("Personal id already taken");
        }
    }
    
    private void updatePrsIdExistsControl() throws BaseException{         
        Long recordExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM users WHERE "
                        + "prs_id=:prsId "
                        + "AND id!=:id")
               .setParameter("prsId", entity.getPrs_id())
               .setParameter("id", entity.getId())
               .uniqueResult();      
        if(recordExists!=0){
             throw new BaseException("Personal id already taken");
        }
    }
    
}
