/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.rasporedrada.controller;

import java.util.List;
import mikec.rasporedrada.util.BaseException;
import mikec.rasporedrada.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Ivan
 */
public abstract class BaseController<T> {
    
    protected Session session;
    protected T entity;
    
    public abstract List<T> read();
    protected abstract void createControll() throws BaseException;
    protected abstract void updateControll() throws BaseException;
    protected abstract void deleteControll() throws BaseException;

    public BaseController() {
        this.session = HibernateUtil.getSession();
    }
    
    public T create() throws BaseException{
        createControll();
        save();
        return entity;        
    }
    
    public T update() throws BaseException{
        updateControll();
        save();
        return entity;    
    }
    
    public void delete() throws BaseException{
        deleteControll();
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
