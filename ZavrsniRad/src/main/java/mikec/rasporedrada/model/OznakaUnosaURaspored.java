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


@Entity(name = "oznakeUnosaURaspored")
public class OznakaUnosaURaspored {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy = "oznakaUnosaURaspored")
    private Set<Raspored> rasporedi = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Raspored> getRasporedi() {
        return rasporedi;
    }

    public void setRasporedi(Set<Raspored> rasporedi) {
        this.rasporedi = rasporedi;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSkracenica() {
        return skracenica;
    }

    public void setSkracenica(String skracenica) {
        this.skracenica = skracenica;
    }
    
    @Column(
            columnDefinition = "VARCHAR(30)",
            unique = true,
            nullable = false
    )
    private String naziv;
    
    @Column(
            columnDefinition = "VARCHAR(3)",
            unique = true,
            nullable = false
    )
    private String skracenica;
    
}
