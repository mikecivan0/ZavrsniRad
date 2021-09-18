package mikec.rasporedrada.util;

import mikec.rasporedrada.controller.PersonController;
import mikec.rasporedrada.controller.UserController;
import mikec.rasporedrada.model.Korisnik;
import mikec.rasporedrada.model.Osoba;

public class StartData {
    
    private static PersonController prsCont;
    private static UserController usrCont;    
    private static Osoba person;
    private static Korisnik user;
   
    
    
    public static void addPerson(){
        prsCont = new PersonController();
        person = new Osoba("user", "novi", "", "", "");
        prsCont.setEntity(person);     
        
        try {
            prsCont.create();
        } catch (BaseException ex) {
            System.out.println(ex.getPoruka());
        }
        
        addUser(person);
    }
    
    public static void changePerson(){
        prsCont = new PersonController();
        person = prsCont.getSession().load(Osoba.class, (long)1);
        person.setIme("Marko");
        prsCont.setEntity(person);     
        
        try {
            prsCont.update();
        } catch (BaseException ex) {
            System.out.println(ex.getPoruka());
        }
    }

    private static void addUser(Osoba osoba) {
        

        usrCont = new UserController();
        user = new Korisnik(osoba, "usr", Alati.hashPass("12345"), "123-5", 1, true);
        usrCont.setEntity(user);     
        
        try {
            usrCont.create();
        } catch (BaseException ex) {
            System.out.println(ex.getPoruka());
        }
    }
    
    
}
