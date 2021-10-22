/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mikec.shedule.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity(name = "persons")
public class Person {

    public Person() {
    }

    public Person(String firstName, String lastName, String phoneNr, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNr = phoneNr;
        this.email = email;
        this.address = address;
    }
    
    @OneToOne(mappedBy = "person")
    private User user;

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private Long id;
    
    @Column(
            columnDefinition = "VARCHAR(25)",
            nullable = false
    )
    private String firstName;
    
    @Column(columnDefinition = "VARCHAR(25)",
            nullable = false
    )
    private String lastName;
    
    @Column(columnDefinition = "VARCHAR(20)")
    private String phoneNr;
    
    @Column(columnDefinition = "VARCHAR(50)")
    private String email;
    
    @Column(columnDefinition = "VARCHAR(100)")
    private String address;
    
    public User getUser() {
        return user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
    
    
   
}
