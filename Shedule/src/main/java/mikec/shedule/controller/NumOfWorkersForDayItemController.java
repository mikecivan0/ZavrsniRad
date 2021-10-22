/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.controller;


import java.util.List;
import mikec.shedule.model.NumOfWorkersForDayItem;
import mikec.shedule.util.BaseException;


public class NumOfWorkersForDayItemController extends BaseController<NumOfWorkersForDayItem>{

    public NumOfWorkersForDayItemController() throws BaseException {
        super();
    }  

    @Override
    public List<NumOfWorkersForDayItem> read() {
        return session.createQuery("FROM numOfWorkersForDayItems").list();
    }

    @Override
    protected void createControl() throws BaseException {
           
    }

    @Override
    protected void updateControl() throws BaseException {
      
    }

    @Override
    protected void deleteControl() throws BaseException {
       
    }
    
    public NumOfWorkersForDayItem find(int i){
        return (NumOfWorkersForDayItem) session.createQuery(
                "FROM numOfWorkersForDayItems WHERE id=:id")
                .setParameter("id", i)
                .uniqueResult();
    }
    
   
 
}
