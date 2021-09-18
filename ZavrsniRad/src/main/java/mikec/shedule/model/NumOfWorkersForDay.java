/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mikec.shedule.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name = "numsOfWorkersForDay")
public class NumOfWorkersForDay {

    public NumOfWorkersForDay() {
    }

    public NumOfWorkersForDay(
            NumOfWorkersForDayItem numOfWorkersForDayItem, 
            int value, 
            LocalDate starts, 
            LocalDate expires) 
    {
        this.numOfWorkersForDayItem = numOfWorkersForDayItem;
        this.value = value;
        this.starts = starts;
        this.expires = expires;
    }    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "numOfWorkersForDayItemId")
    private NumOfWorkersForDayItem numOfWorkersForDayItem;    
    
    @Column(
            nullable = false
    )
    private int value;
    
    @Column(
            nullable = false
    )
    private LocalDate starts;
    
    @Column(
            nullable = false
    )
    private LocalDate expires;

}
