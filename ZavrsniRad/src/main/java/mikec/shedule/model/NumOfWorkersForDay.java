/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mikec.shedule.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import mikec.shedule.util.Tools;


@Entity(name = "numsOfWorkersForDay")
public class NumOfWorkersForDay {

    public NumOfWorkersForDay() {
    }

    public NumOfWorkersForDay(
            NumOfWorkersForDayItem numOfWorkersForDayItem, 
            int value, 
            Date starts, 
            Date expires) 
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
    private Date starts;
    
    @Column(
            nullable = false
    )
    private Date expires;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NumOfWorkersForDayItem getNumOfWorkersForDayItem() {
        return numOfWorkersForDayItem;
    }

    public void setNumOfWorkersForDayItem(NumOfWorkersForDayItem numOfWorkersForDayItem) {
        this.numOfWorkersForDayItem = numOfWorkersForDayItem;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getStarts() {
        return starts;
    }

    public void setStarts(Date starts) {
        this.starts = starts;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return Tools.formatDate(starts) + "-" +
               Tools.formatDate(expires);
    }
    
    
    
           

    
}
