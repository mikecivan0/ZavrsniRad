/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mikec.rasporedrada.model;


import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name = "redovnaRadnaVremena")
public class RedovnoRadnoVrijeme {

    public RedovnoRadnoVrijeme() {
    }

    public RedovnoRadnoVrijeme(
            RedovnoRadnoVrijemeStavka redovnaRadnaVremenaStavka, 
            LocalTime vrijednost, 
            LocalDate vrijediOd, 
            LocalDate vrijediDo, 
            int trajanjePauzeUMinutama) {
        this.redovnaRadnaVremenaStavka = redovnaRadnaVremenaStavka;
        this.vrijednost = vrijednost;
        this.vrijediOd = vrijediOd;
        this.vrijediDo = vrijediDo;
        this.trajanjePauzeUMinutama = trajanjePauzeUMinutama;
    }
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "redovnaRadnaVremenaStavkaId")
    private RedovnoRadnoVrijemeStavka redovnaRadnaVremenaStavka;
    
    @Column(
            nullable = false
    )
    private LocalTime vrijednost;
    
    @Column(
            nullable = false
    )
    private LocalDate vrijediOd;
    
    @Column(
            nullable = false
    )
    private LocalDate vrijediDo;
    
    @Column(
            nullable = false
    )
    private int trajanjePauzeUMinutama;

}
