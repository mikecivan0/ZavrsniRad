/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.controller;

import java.lang.reflect.Method;
import java.util.List;
import javax.swing.JOptionPane;
import mikec.shedule.model.Record;
import mikec.shedule.model.User;
import mikec.shedule.util.BaseException;


public class RecordController extends BaseController<Record>{

    public RecordController() throws BaseException {
        super();
    }
    
    @Override
    public List<Record> read() {
        return session.createQuery("FROM records").list();
    }
    
    @Override
    protected void createControll() throws BaseException {
        notEmptyControll("User");
        notEmptyControll("Label");
        notEmptyControll("Date");
//        createRecordExistsControll();
    }
    
    @Override
    protected void updateControll() throws BaseException {
        notEmptyControll("User");
        notEmptyControll("Label");
        notEmptyControll("Date");
//        updateRecordExistsControll();
    }

    @Override
    protected void deleteControll() throws BaseException {
       
    }   
   
    private void notEmptyControll(String variable) throws BaseException{        
        if(getVariable(variable)==null || getVariable(variable).trim().length()==0){
           throw new BaseException("Input '" + variable + "' cannot be empty");
       }    
    }
    
    private String getVariable(String variable){            
        String text = "";
        try { 
            Method method = Record.class.getDeclaredMethod("get" + variable, null);
            text = (String) method.invoke(entity, null);           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }        
       return text;
    }
    
    public List<Integer> getYears(){            
        return session.createNativeQuery("select distinct YEAR(date) as year from records")
                .list();
    }
    
    public List<Integer> getMonths(String year){            
        return session.createNativeQuery("select distinct MONTH(date) as month "
                + "from records where YEAR(date)=:year")
                .setParameter("year", year)
                .list();
    }
    
    public List<User> getUsersForMonth(Integer year, Integer month){            
        return session.createNativeQuery("select distinct userId from records "
                + "where YEAR(date)=:year AND MONTH(date)=:month").
                setParameter(year, year)
                .setParameter("month", month)
                .list();
    }
    
    public List<Record> getRecordForDatehAndUser(Integer year, Integer month){            
        return session.createNativeQuery("select distinct userId from records "
                + "where YEAR(date)=:year AND MONTH(date)=:month").
                setParameter(year, year)
                .setParameter("month", month)
                .list();
    }

//    private void createRecordExistsControll() throws BaseException{         
//        Long recordExists = (Long) session.createQuery(
//                "SELECT COUNT(id) FROM users WHERE "
//                        + "userId=:user "
//                        + "AND YEAR(date)=:year "
//                        + "AND MONTH(date)=:month")
//               .setParameter("user", entity.getUser())
//               .setParameter("year", entity.getUser())
//               .setParameter("month", entity.getUser())
//               .uniqueResult();      
//        if(recordExists!=0){
//            throw new BaseException("Username allready taken");
//        }
//    }
//    
//    private void updateRecordExistsControll() throws BaseException{         
//       Long personExists = (Long) session.createQuery(
//                 "SELECT COUNT(id) FROM users WHERE "
//                        + "username=:username "
//                         + "AND id!=:id")
//               .setParameter("username", entity.getUsername())
//               .setParameter("id", entity.getId())
//               .uniqueResult(); 
//        if(personExists!=0){
//            throw new BaseException("Username allready taken");
//        }
//    }
//    

    
}
