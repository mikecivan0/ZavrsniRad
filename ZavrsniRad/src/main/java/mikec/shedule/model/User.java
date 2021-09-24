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
import javax.persistence.OneToOne;

@Entity(name = "users")
public class User{

    public User() {
    }
    
    public User(Person person, String username, String pass, String prs_id, int level, boolean aktiv) {
        this.id = id;
        this.person = person;
        this.username = username;
        this.pass = pass;
        this.prs_id = prs_id;
        this.level = level;
        this.aktiv = aktiv;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne()
    private Person person;
    
    @OneToMany(mappedBy = "user")
    private Set<Record> records = new HashSet<>();

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }
    
    @Column(
            columnDefinition = "VARCHAR(50)",
            nullable = false
    )
    private String username;
    
    @Column(
            columnDefinition = "VARCHAR(256)",
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
    private int level;
    
    @Column(nullable = false)
    private boolean aktiv;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

     public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

}
