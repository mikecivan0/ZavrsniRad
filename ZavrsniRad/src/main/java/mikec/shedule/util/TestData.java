package mikec.shedule.util;

import mikec.shedule.controller.PersonController;
import mikec.shedule.controller.UserController;
import mikec.shedule.model.User;
import mikec.shedule.model.Person;

public class TestData {
    
    private static PersonController prsCont;
    private static UserController usrCont;    
    private static Person person;
    private static User user;
   
    
    
    public static void load(){
        prsCont = new PersonController();
        person = new Person("user", "novi", "", "", "");
        prsCont.setEntity(person);     
        
        try {
            prsCont.create();
        } catch (BaseException ex) {
            System.out.println(ex.getPoruka());
        }
        
        loadUser(person);
    }
    
    public static void changePerson(){
        prsCont = new PersonController();
        person = prsCont.getSession().load(Person.class, (long)1);
        person.setFirstName("Marko");
        prsCont.setEntity(person);     
        
        try {
            prsCont.update();
        } catch (BaseException ex) {
            System.out.println(ex.getPoruka());
        }
    }

    private static void loadUser(Person person) {
        

        usrCont = new UserController();
        user = new User(person, "usr", Alati.hashPass("12345"), "123-5", 1, true);
        usrCont.setEntity(user);     
        
        try {
            usrCont.create();
        } catch (BaseException ex) {
            System.out.println(ex.getPoruka());
        }
    }
    
    
}
