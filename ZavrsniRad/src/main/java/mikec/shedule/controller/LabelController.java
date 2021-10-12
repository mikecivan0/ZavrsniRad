
package mikec.shedule.controller;

import java.lang.reflect.Method;
import java.util.List;
import javax.swing.JOptionPane;
import mikec.shedule.model.Label;
import mikec.shedule.util.BaseException;

public class LabelController extends BaseController<Label>{

    public LabelController() throws BaseException {        
        super();
    }

    @Override
    public List<Label> read() {
        return session.createQuery("FROM labels").list();
    }

    @Override
    protected void createControll() throws BaseException {
        notEmptyControll("Name");
        notEmptyControll("Abbreviation");
        lengthControll("Name",30);
        lengthControll("Abbreviation",3);
        createExistsControll();
    }

    @Override
    protected void updateControll() throws BaseException {
        notEmptyControll("Name");
        notEmptyControll("Abbreviation");
        lengthControll("Name",30);
        lengthControll("Abbreviation",3);
        updateExistsControll();  
        if(entity.getId()<3){
            throw new BaseException("This label is not allowed to modify");
        }
    }

    @Override
    protected void deleteControll() throws BaseException {        
        if(entity.getRecords().size()>0){
            throw new BaseException("Label cannot be deleted becouse it is already used in records");
        }
        if(entity.getId()<3){
            throw new BaseException("This label is not allowed to delete");
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
            Method method = Label.class.getDeclaredMethod("get" + variable, null);
            text = (String) method.invoke(entity, null);           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }        
       return text;
    }
    
    private void createExistsControll() throws BaseException{         
        Long recordNameExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM labels WHERE "
                        + "name=:name")
               .setParameter("name", entity.getName())
               .uniqueResult();      
        if(recordNameExists!=0){
            throw new BaseException("Label with that name already exists in database");
        }
        
        Long recordAbbreviationExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM labels WHERE "
                        + "abbreviation=:abbreviation")
               .setParameter("abbreviation", entity.getAbbreviation())
               .uniqueResult();      
        if(recordAbbreviationExists!=0){
            throw new BaseException("Label with that abbreviation already exists in database");
        }
    }
    
    private void updateExistsControll() throws BaseException{         
       Long recordNameExists = (Long) session.createQuery(
                 "SELECT COUNT(id) FROM labels WHERE "
                        + "name=:name "
                        + "AND id!=:id")
               .setParameter("name", entity.getName())
               .setParameter("id", entity.getId())
               .uniqueResult();        
        if(recordNameExists!=0){
            throw new BaseException("Label with that name already exists in database");
        }
        
        Long recordAbbreviationExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM labels WHERE "
                        + "abbreviation=:abbreviation "
                        + "AND id!=:id")
               .setParameter("abbreviation", entity.getAbbreviation())
               .setParameter("id", entity.getId())
               .uniqueResult();      
        if(recordAbbreviationExists!=0){
            throw new BaseException("Label with that abbreviation already exists in database");
        }
    }
}
