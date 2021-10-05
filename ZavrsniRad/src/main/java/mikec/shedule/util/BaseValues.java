/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.util;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import mikec.shedule.controller.ExceptionalWorkingHoursController;
import mikec.shedule.controller.LabelController;
import mikec.shedule.controller.PersonController;
import mikec.shedule.controller.UserController;
import mikec.shedule.model.ExceptionalWorkingHours;
import mikec.shedule.model.Label;
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
    private static UserController userController;    
    private static LabelController labelController;    
    private static ExceptionalWorkingHoursController exceptionalWorkingHoursController;    
    private static Person person;
    private static ExceptionalWorkingHours exceptionalWorkingHours;
    private static User user;    
    private static Label label;    

    public BaseValues(Session session) {
        this.session = session;
    }

    public void load() throws BaseException {
        loadPerson("PersonName", "PersonSurname", "", "", "");
        loadUser(person, "user1", Tools.hashPass("user1"), "123-5", 1, true);
        loadPerson("PersonName2", "PersonSurname2", "", "", "");
        loadUser(person, "user2", Tools.hashPass("user2"), "123-56", 2, true);
        loadExceptionalWorkingHours(
                Tools.parseDate("12.12.2020."), 
                Tools.parseTime("00:00:00"), 
                Tools.parseTime("02:00:00"), 
                "Test footnote", 
                30);
        loadRegularWorkingHoursItems();
        loadRegularWorkingHours();
        loadNumOfWorkersForDayItem();
        loadNumOfWorkersForDay();
        loadLabels("Work with break","w");
        loadLabels("Work without break","w*");
    }  
    
    public static void loadPerson(String firstName, String lastName, String phoneNr, String email, String address) throws BaseException{
        personController = new PersonController();
        person = new Person(firstName, lastName, phoneNr, email, address);
        personController.setEntity(person);     
        
        try {
            personController.create();
        } catch (BaseException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void loadLabels(String name, String abbreviation) throws BaseException{
        labelController = new LabelController();
        label = new Label(name, abbreviation);
        labelController.setEntity(label);     
        
        try {
            labelController.create();
        } catch (BaseException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void loadExceptionalWorkingHours(
                Date date, 
                Time startTime, 
                Time endTime, 
                String footnote, 
                int breakDuration) throws BaseException{
        exceptionalWorkingHoursController = new ExceptionalWorkingHoursController();
        exceptionalWorkingHours = new ExceptionalWorkingHours(date, startTime, endTime, footnote, breakDuration);
        exceptionalWorkingHoursController.setEntity(exceptionalWorkingHours);     
        
        try {
            exceptionalWorkingHoursController.create();
        } catch (BaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void loadUser(Person person, String username, String pass, String prs_id, int level, boolean aktiv) throws BaseException {
        userController = new UserController();
        user = new User(person, username, pass, prs_id, level, aktiv);
        userController.setEntity(user);     
        
        try {
            userController.create();
        } catch (BaseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void loadRegularWorkingHoursItems() {
        rwhItems = new ArrayList<RegularWorkingHoursItem>();
        String[] listOfItems = {
            "monStarts","monEnds",
            "tueStarts","tueEnds",
            "wedStarts","wedEnds",
            "thuStarts","thuEnds",
            "friStarts","friEnds",
            "satStarts","satEnds",
            "sunStarts","sunEnds"
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

    private void loadRegularWorkingHours() throws BaseException {
        List<RegularWorkingHours> regularWorkingHours = new ArrayList<RegularWorkingHours>();

        for (int i = 0; i < 14; i++) {
            String time = (i%2==0 || i==0) ? "22:00:00" : "00:00:00";
            regularWorkingHours.add(new RegularWorkingHours(
                            rwhItems.get(i),
                            Tools.parseTime(time),
                            Tools.parseDate("15.11.2017."),
                            Tools.parseDate("15.12.2017."),
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
            "monday","tuesday","wednesday","thursday",
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
    
    private void loadNumOfWorkersForDay() throws BaseException {
        List<NumOfWorkersForDay> numOfWorkersForDay = new ArrayList<NumOfWorkersForDay>();

        for (int i = 0; i < 7; i++) {
            
            numOfWorkersForDay.add(new NumOfWorkersForDay(
                            nwfdItems.get(i),
                            getRandomNumberInRange(1, 7),
                            Tools.parseDate("15.10.2017."),
                            Tools.parseDate("14.11.2017.")
                    )
            );
            
            numOfWorkersForDay.add(new NumOfWorkersForDay(
                            nwfdItems.get(i),
                            getRandomNumberInRange(1, 7),
                            Tools.parseDate("15.11.2018."),
                            Tools.parseDate("15.12.2018.")
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
