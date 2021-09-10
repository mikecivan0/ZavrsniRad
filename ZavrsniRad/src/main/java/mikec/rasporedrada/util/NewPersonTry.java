/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.rasporedrada.util;

import mikec.rasporedrada.controller.PersonController;
import mikec.rasporedrada.model.Osoba;

/**
 *
 * @author Ivan
 */
public class NewPersonTry {
    
    public static void addPerson(){
        PersonController prsCont = new PersonController();
        Osoba o = new Osoba("Ivan", "Mikec", "", "", "");
        prsCont.setEntity(o);     
        
        try {
            prsCont.create();
        } catch (BaseException ex) {
            System.out.println(ex.getPoruka());
        }
    }
}
