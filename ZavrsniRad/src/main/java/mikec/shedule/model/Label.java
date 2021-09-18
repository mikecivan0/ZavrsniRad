/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mikec.shedule.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity(name = "labels")
public class Label {

    public Label() {
    }

    public Label(Long id, String name, String abbreviation) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy = "label")
    private Set<Record> rasporedi = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Record> getRasporedi() {
        return rasporedi;
    }

    public void setRasporedi(Set<Record> rasporedi) {
        this.rasporedi = rasporedi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    
    @Column(
            columnDefinition = "VARCHAR(30)",
            unique = true,
            nullable = false
    )
    private String name;
    
    @Column(
            columnDefinition = "VARCHAR(3)",
            unique = true,
            nullable = false
    )
    private String abbreviation;
    
}
