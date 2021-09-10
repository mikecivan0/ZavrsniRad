package mikec.rasporedrada.util;

import mikec.rasporedrada.controller.PersonController;
import mikec.rasporedrada.model.Osoba;

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
