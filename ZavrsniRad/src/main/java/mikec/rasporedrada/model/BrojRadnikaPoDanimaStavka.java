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

@Entity(name = "brojRadnikaPoDanimaStavke")
public class BrojRadnikaPoDanimaStavka {

    public BrojRadnikaPoDanimaStavka() {
    }

    public BrojRadnikaPoDanimaStavka(String naziv) {
        this.naziv = naziv;
    }   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
     @Column(
            columnDefinition = "VARCHAR(20)",
            nullable = false
            )
    private String naziv;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
}
