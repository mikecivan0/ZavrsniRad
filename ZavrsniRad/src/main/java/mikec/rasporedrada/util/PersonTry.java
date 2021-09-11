package mikec.rasporedrada.util;

import mikec.rasporedrada.controller.PersonController;
import mikec.rasporedrada.model.Osoba;

public class PersonTry {
    
    private static PersonController prsCont;
    private static Osoba osoba;
    
    public static void addPerson(){
        prsCont = new PersonController();
        osoba = new Osoba("Ivan", "Mikec", "", "", "");
        prsCont.setEntity(osoba);     
        
        try {
            prsCont.create();
        } catch (BaseException ex) {
            System.out.println(ex.getPoruka());
        }
    }
    
    public static void changePerson(){
        prsCont = new PersonController();
        osoba = prsCont.getSession().load(Osoba.class, (long)1);
        osoba.setIme("Marko");
        prsCont.setEntity(osoba);     
        
        try {
            prsCont.update();
        } catch (BaseException ex) {
            System.out.println(ex.getPoruka());
        }
    }
}
