/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.rasporedrada.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "korisnici")
public class Korisnik{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne()
    private Osoba osoba;
    
    @OneToMany(mappedBy = "korisnik")
    private Set<Raspored> rasporedi = new HashSet<>();

    public Set<Raspored> getRasporedi() {
        return rasporedi;
    }

    public void setRasporedi(Set<Raspored> rasporedi) {
        this.rasporedi = rasporedi;
    }
    
    @Column(
            columnDefinition = "VARCHAR(50)",
            nullable = false
    )
    private String username;
    
    @Column(
            columnDefinition = "VARCHAR(100)",
            nullable = false
    )
    private String pass;
    
    @Column(
            columnDefinition = "VARCHAR(10)",
            nullable = false
    )
    private String prs_id;
    
    @Column(
            columnDefinition = "TINYINT",
            nullable = false
    )    
    private int razina;
    
    @Column(nullable = false)
    private boolean aktivan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

     public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPrs_id() {
        return prs_id;
    }

    public void setPrs_id(String prs_id) {
        this.prs_id = prs_id;
    }

    public int getRazina() {
        return razina;
    }

    public void setRazina(int razina) {
        this.razina = razina;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

}
