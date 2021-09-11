/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mikec.rasporedrada.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "osobe")
public class Osoba {

    public Osoba() {
    }

    public Osoba(String ime, String prezime, String telefon, String email, String adresa) {
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.email = email;
        this.adresa = adresa;
    }
    
    

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private Long id;
    
    @Column(
            columnDefinition = "VARCHAR(25)",
            nullable = false
    )
    private String ime;
    
    @Column(columnDefinition = "VARCHAR(25)",
            nullable = false
    )
    private String prezime;
    
    @Column(columnDefinition = "VARCHAR(20)")
    private String telefon;
    
    @Column(columnDefinition = "VARCHAR(50)")
    private String email;
    
    @Column(columnDefinition = "VARCHAR(100)")
    private String adresa;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
   
}
