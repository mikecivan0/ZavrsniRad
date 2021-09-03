/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.rasporedrada.main;

import mikec.rasporedrada.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author dell
 */
public class Start {
    
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
       
        OsnovniUnosi unosi = new OsnovniUnosi(session);
        unosi.ucitaj();
       
    }
    
}
