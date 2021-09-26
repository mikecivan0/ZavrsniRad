/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.controller;


import java.util.List;
import mikec.shedule.model.NumOfWorkersForDayItem;
import mikec.shedule.util.BaseException;

/**
 *
 * @author Ivan
 */
public class NumOfWorkersForDayItemController extends BaseController<NumOfWorkersForDayItem>{

    public NumOfWorkersForDayItemController() throws BaseException {
        super();
    }  

    @Override
    public List<NumOfWorkersForDayItem> read() {
        return session.createQuery("from numOfWorkersForDayItems").list();
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
    
    public NumOfWorkersForDayItem find(int i){
        return (NumOfWorkersForDayItem) session.createQuery(
                "from numOfWorkersForDayItems where id=:id")
                .setParameter("id", i)
                .uniqueResult();
    }
    
   
 
}
