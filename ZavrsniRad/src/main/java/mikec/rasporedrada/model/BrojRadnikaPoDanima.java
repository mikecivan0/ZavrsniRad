/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mikec.rasporedrada.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name = "brojRadnikaPoDanima")
public class BrojRadnikaPoDanima {

    public BrojRadnikaPoDanima() {
    }

    public BrojRadnikaPoDanima(
            BrojRadnikaPoDanimaStavka brojRadnikaPoDanimaStavka, 
            int vrijednost, 
            LocalDate vrijediOd, 
            LocalDate vrijediDo) 
    {
        this.brojRadnikaPoDanimaStavka = brojRadnikaPoDanimaStavka;
        this.vrijednost = vrijednost;
        this.vrijediOd = vrijediOd;
        this.vrijediDo = vrijediDo;
    }    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "brojRadnikaPoDanimaStavkaId")
    private BrojRadnikaPoDanimaStavka brojRadnikaPoDanimaStavka;    
    
    @Column(
            nullable = false
    )
    private int vrijednost;
    
    @Column(
            nullable = false
    )
    private LocalDate vrijediOd;
    
    @Column(
            nullable = false
    )
    private LocalDate vrijediDo;

}
