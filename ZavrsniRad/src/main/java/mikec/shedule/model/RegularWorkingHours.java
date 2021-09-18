/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mikec.shedule.model;


import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name = "regularWorkingHours")
public class RegularWorkingHours {

    public RegularWorkingHours() {
    }

    public RegularWorkingHours(
            RegularWorkingHoursItem regularWorkingHoursItem, 
            LocalTime value, 
            LocalDate starts, 
            LocalDate expires, 
            int breakDurationInMinutes) {
        this.regularWorkingHoursItem = regularWorkingHoursItem;
        this.value = value;
        this.starts = starts;
        this.expires = expires;
        this.breakDurationInMinutes = breakDurationInMinutes;
    }
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "regularWorkingHoursItemId")
    private RegularWorkingHoursItem regularWorkingHoursItem;
    
    @Column(
            nullable = false
    )
    private LocalTime value;
    
    @Column(
            nullable = false
    )
    private LocalDate starts;
    
    @Column(
            nullable = false
    )
    private LocalDate expires;
    
    @Column(
            nullable = false
    )
    private int breakDurationInMinutes;

}
