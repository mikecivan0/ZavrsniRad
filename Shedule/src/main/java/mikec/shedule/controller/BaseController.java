/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.controller;

import java.util.List;
import mikec.shedule.util.BaseException;
import mikec.shedule.util.HibernateUtil;
import org.hibernate.Session;

public abstract class BaseController<T> {
    
    protected Session session;
    protected T entity;
    
    public abstract List<T> read();
    protected abstract void createControl() throws BaseException;
    protected abstract void updateControl() throws BaseException;
    protected abstract void deleteControl() throws BaseException;

    public BaseController() throws BaseException {
        this.session = HibernateUtil.getSession();
    }
    
    public T create() throws BaseException{
        createControl();
        save();
        return entity;        
    }
    
    public T update() throws BaseException{
        updateControl();
        save();
        return entity;    
    }
    
    public void delete() throws BaseException{
        deleteControl();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    private void save() {
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }   

    public Session getSession() {
        return session;
    }
    
    
    
}
