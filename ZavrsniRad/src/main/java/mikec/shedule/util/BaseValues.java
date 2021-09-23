/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mikec.shedule.controller.PersonController;
import mikec.shedule.controller.UserController;
import mikec.shedule.model.NumOfWorkersForDay;
import mikec.shedule.model.NumOfWorkersForDayItem;
import mikec.shedule.model.Person;
import mikec.shedule.model.RegularWorkingHours;
import mikec.shedule.model.RegularWorkingHoursItem;
import mikec.shedule.model.User;
import org.hibernate.Session;

public class BaseValues {

    private Session session;
    private List<RegularWorkingHoursItem> rwhItems;
    private List<NumOfWorkersForDayItem> nwfdItems;
    private static PersonController personController;
    private static UserController userConttroler;    
    private static Person person;
    private static User user;    

    public BaseValues(Session session) {
        this.session = session;
    }

    public void load() {
        loadPerson("PersonName", "PersonSurname", "", "", "");
        loadUser(person, "user1", Tools.hashPass("user1"), "123-5", 1, true);
        loadPerson("PersonName2", "PersonSurname2", "", "", "");
        loadUser(person, "user2", Tools.hashPass("user2"), "123-56", 2, true);
        loadRegularWorkingHoursItems();
        loadRegularWorkingHours();
        loadNumOfWorkersForDayItem();
        loadNumOfWorkersForDay();

    }  
    
    public static void loadPerson(String firstName, String lastName, String phoneNr, String email, String address){
        personController = new PersonController();
        person = new Person(firstName, lastName, phoneNr, email, address);
        personController.setEntity(person);     
        
        try {
            personController.create();
        } catch (BaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void loadUser(Person person, String username, String pass, String prs_id, int level, boolean aktiv) {
        userConttroler = new UserController();
        user = new User(person, username, pass, prs_id, level, aktiv);
        userConttroler.setEntity(user);     
        
        try {
            userConttroler.create();
        } catch (BaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void loadRegularWorkingHoursItems() {
        rwhItems = new ArrayList<RegularWorkingHoursItem>();
        String[] listOfItems = {
            "monStart","monEnd",
            "tueStart","tueEnd",
            "wedStart","wedEnd",
            "thuStart","thuEnd",
            "friStart","friEnd",
            "satStart","satEnd",
            "sunStart","sunEnd"
        };
        
        for(String item : listOfItems){
           rwhItems.add(new RegularWorkingHoursItem(item)); 
        }
        
        
        session.beginTransaction();        
        rwhItems.forEach(rwh -> {            
            session.save(rwh);            
        });        
        session.getTransaction().commit();
    }

    private void loadRegularWorkingHours() {
        List<RegularWorkingHours> regularWorkingHours = new ArrayList<RegularWorkingHours>();

        for (int i = 0; i < 14; i++) {
            String time = (i%2==0 || i==0) ? "22:00" : "00:00";
            regularWorkingHours.add(new RegularWorkingHours(
                            rwhItems.get(i),
                            LocalTime.parse(time),
                            LocalDate.parse("2017-11-15"),
                            LocalDate.parse("2017-12-15"),
                            30
                    )
            );
        }        
        
        session.beginTransaction();
        regularWorkingHours.forEach(rwh -> {           
            session.save(rwh);           
        });
        session.getTransaction().commit();
    }
    
    private void loadNumOfWorkersForDayItem() {
        nwfdItems = new ArrayList<NumOfWorkersForDayItem>();
        String[] list = {
            "monday","tuesday","wdnesday","thursday",
            "friday","saturday","sunday"
        };
        
        for(String item : list){
           nwfdItems.add(new NumOfWorkersForDayItem(item)); 
        }
        
        
        session.beginTransaction();
        nwfdItems.forEach(nwfdItem -> {            
            session.save(nwfdItem);            
        });
        session.getTransaction().commit();
    }
    
    private void loadNumOfWorkersForDay() {
        List<NumOfWorkersForDay> numOfWorkersForDay = new ArrayList<NumOfWorkersForDay>();

        for (int i = 0; i < 7; i++) {
            
            numOfWorkersForDay.add(new NumOfWorkersForDay(
                            nwfdItems.get(i),
                            getRandomNumberInRange(1, 7),
                            LocalDate.parse("2017-11-15"),
                            LocalDate.parse("2017-12-15")
                    )
            );
        }
        
        
        session.beginTransaction();
        numOfWorkersForDay.forEach(nwfd -> {            
            session.save(nwfd);            
        });
        session.getTransaction().commit();
    }
    
    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    
}
