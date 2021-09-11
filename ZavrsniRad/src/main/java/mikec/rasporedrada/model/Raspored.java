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

@Entity(name = "raspored")
public class Raspored {

    public Raspored() {
    }

    public Raspored(Long id, Korisnik korisnik, OznakaUnosaURaspored oznakaUnosaURaspored, Date datum, boolean radSaPauzom) {
        this.id = id;
        this.korisnik = korisnik;
        this.oznakaUnosaURaspored = oznakaUnosaURaspored;
        this.datum = datum;
        this.radSaPauzom = radSaPauzom;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "korisnikId")
    private Korisnik korisnik;
    
    @ManyToOne
    @JoinColumn(name = "oznakaUnosaURasporedId")
    private OznakaUnosaURaspored oznakaUnosaURaspored;
    
    @Column(nullable = false)
    private Date datum;
    
    @Column(nullable = false)
    private boolean radSaPauzom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public OznakaUnosaURaspored getOznakaUnosaURaspored() {
        return oznakaUnosaURaspored;
    }

    public void setOznakaUnosaURaspored(OznakaUnosaURaspored oznakaUnosaURaspored) {
        this.oznakaUnosaURaspored = oznakaUnosaURaspored;
    }

     public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public boolean isRadSaPauzom() {
        return radSaPauzom;
    }

    public void setRadSaPauzom(boolean radSaPauzom) {
        this.radSaPauzom = radSaPauzom;
    }   

}
