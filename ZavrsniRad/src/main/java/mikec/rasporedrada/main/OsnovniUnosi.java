/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.rasporedrada.main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mikec.rasporedrada.model.BrojRadnikaPoDanima;
import mikec.rasporedrada.model.BrojRadnikaPoDanimaStavka;
import mikec.rasporedrada.model.RedovnoRadnoVrijeme;
import mikec.rasporedrada.model.RedovnoRadnoVrijemeStavka;
import org.hibernate.Session;

public class OsnovniUnosi {

    private Session session;
    List<RedovnoRadnoVrijemeStavka> rrvStavke;
    List<BrojRadnikaPoDanimaStavka> brdStavke;

    public OsnovniUnosi(Session session) {
        this.session = session;
    }

    public void ucitaj() {
        ucitajRedovnoRadnoVrijemeStavke();
        ucitajRedovnoRadnoVrijeme();
        ucitajBrojRadnikaPoDanimaStavke();
        ucitajBrojRadnikaPoDanima();

    }

    private void ucitajRedovnoRadnoVrijemeStavke() {
        rrvStavke = new ArrayList<RedovnoRadnoVrijemeStavka>();
        String[] popis = {
            "ponedjeljakOd","ponedjeljakDo",
            "utorakOd","utorakDo","srijedaOd","srijedaDo",
            "cetvrtakOd","cetvrtakDo","petakOd","petakDo",
            "subotaOd","subotaDo","nedjeljaOd","nedjeljaDo"
        };
        
        for(String stavka : popis){
           rrvStavke.add(new RedovnoRadnoVrijemeStavka(stavka)); 
        }

        rrvStavke.forEach(rrvs -> {
            session.beginTransaction();
            session.save(rrvs);
            session.getTransaction().commit();
        });
    }

    private void ucitajRedovnoRadnoVrijeme() {
        List<RedovnoRadnoVrijeme> redovnaRadnaVremena = new ArrayList<RedovnoRadnoVrijeme>();

        for (int i = 0; i < 14; i++) {
            String vrijeme = (i%2==0 || i==0) ? "22:00" : "00:00";
            redovnaRadnaVremena.add(
                    new RedovnoRadnoVrijeme(
                            rrvStavke.get(i),
                            LocalTime.parse(vrijeme),
                            LocalDate.parse("2017-11-15"),
                            LocalDate.parse("2017-12-15"),
                            30
                    )
            );
        }
        
        redovnaRadnaVremena.forEach(rrv -> {
            session.beginTransaction();
            session.save(rrv);
            session.getTransaction().commit();
        });
    }
    
    private void ucitajBrojRadnikaPoDanimaStavke() {
        brdStavke = new ArrayList<BrojRadnikaPoDanimaStavka>();
        String[] popis = {
            "ponedjeljak","utorak","srijeda","cetvrtak",
            "petak","subota","nedjelja"
        };
        
        for(String stavka : popis){
           brdStavke.add(new BrojRadnikaPoDanimaStavka(stavka)); 
        }

        brdStavke.forEach(brds -> {
            session.beginTransaction();
            session.save(brds);
            session.getTransaction().commit();
        });
    }
    
    private void ucitajBrojRadnikaPoDanima() {
        List<BrojRadnikaPoDanima> brojRadnika = new ArrayList<BrojRadnikaPoDanima>();

        for (int i = 0; i < 7; i++) {
            
            brojRadnika.add(
                    new BrojRadnikaPoDanima(
                            brdStavke.get(i),
                            getRandomNumberInRange(1, 7),
                            LocalDate.parse("2017-11-15"),
                            LocalDate.parse("2017-12-15")
                    )
            );
        }
        
        brojRadnika.forEach(brd -> {
            session.beginTransaction();
            session.save(brd);
            session.getTransaction().commit();
        });
    }
    
    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
