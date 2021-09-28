/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.controller;


import java.util.List;
import mikec.shedule.model.RegularWorkingHoursItem;
import mikec.shedule.util.BaseException;

/**
 *
 * @author Ivan
 */
public class RegularWorkingHoursItemController extends BaseController<RegularWorkingHoursItem>{

    public RegularWorkingHoursItemController() throws BaseException {
        super();
    }  

    @Override
    public List<RegularWorkingHoursItem> read() {
        return session.createQuery("FROM regularWorkingHoursItems").list();
    }

    @Override
    protected void createControll() throws BaseException {
           
    }

    @Override
    protected void updateControll() throws BaseException {
      
    }

    @Override
    protected void deleteControll() throws BaseException {
       
    }
    
    public RegularWorkingHoursItem find(int i){
        return (RegularWorkingHoursItem) session.createQuery(
                "FROM regularWorkingHoursItems WHERE id=:id")
                .setParameter("id", i)
                .uniqueResult();
    }
    
   
 
}
