/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.rasporedrada.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mikec.rasporedrada.model.NumOfWorkersForDay;
import mikec.rasporedrada.model.NumOfWorkersForDayItem;
import mikec.rasporedrada.model.RegularWorkingHours;
import mikec.rasporedrada.model.RegularWorkingHoursItem;
import org.hibernate.Session;

public class BaseValues {

    private Session session;
    private List<RegularWorkingHoursItem> rwhItems;
    private List<NumOfWorkersForDayItem> nwfdItems;

    public BaseValues(Session session) {
        this.session = session;
    }

    public void load() {
        loadRegularWorkingHoursItems();
        loadRegularWorkingHours();
        loadNumOfWorkersForDayItem();
        loadNumOfWorkersForDay();

    }

    private void loadRegularWorkingHoursItems() {
        rwhItems = new ArrayList<RegularWorkingHoursItem>();
        String[] popis = {
            "ponedjeljakOd","ponedjeljakDo",
            "utorakOd","utorakDo","srijedaOd","srijedaDo",
            "cetvrtakOd","cetvrtakDo","petakOd","petakDo",
            "subotaOd","subotaDo","nedjeljaOd","nedjeljaDo"
        };
        
        for(String stavka : popis){
           rwhItems.add(new RegularWorkingHoursItem(stavka)); 
        }
        
        
        session.beginTransaction();        
        rwhItems.forEach(rrvs -> {            
            session.save(rrvs);            
        });        
        session.getTransaction().commit();
    }

    private void loadRegularWorkingHours() {
        List<RegularWorkingHours> redovnaRadnaVremena = new ArrayList<RegularWorkingHours>();

        for (int i = 0; i < 14; i++) {
            String vrijeme = (i%2==0 || i==0) ? "22:00" : "00:00";
            redovnaRadnaVremena.add(new RegularWorkingHours(
                            rwhItems.get(i),
                            LocalTime.parse(vrijeme),
                            LocalDate.parse("2017-11-15"),
                            LocalDate.parse("2017-12-15"),
                            30
                    )
            );
        }
        
        
        session.beginTransaction();
        redovnaRadnaVremena.forEach(rrv -> {           
            session.save(rrv);           
        });
        session.getTransaction().commit();
    }
    
    private void loadNumOfWorkersForDayItem() {
        nwfdItems = new ArrayList<NumOfWorkersForDayItem>();
        String[] list = {
            "ponedjeljak","utorak","srijeda","cetvrtak",
            "petak","subota","nedjelja"
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
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
