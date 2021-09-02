/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mikec.rasporedrada.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name = "redovnaRadnaVremena")
public class RedovnoRadnoVrijeme {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "redovnaRadnaVremenaStavkaId")
    private RedovnoRadnoVrijemeStavke redovnaRadnaVremenaStavka;
    
    @Column(
            nullable = false
    )
    private int vrijednost;
    
    @Column(
            nullable = false
    )
    private Date vrijediOd;
    
    @Column(
            nullable = false
    )
    private Date vrijediDo;
    
    @Column(
            nullable = false
    )
    private int trajanjePauzeUMinutama;

}
