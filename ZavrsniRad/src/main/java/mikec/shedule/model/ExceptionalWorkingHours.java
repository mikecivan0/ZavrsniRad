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

@Entity(name = "exceptionalWorkingHours")
public class ExceptionalWorkingHours {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @Column(nullable = false)
	private Date date;
        
        @Column(nullable = false)
	private Date startTime;
        
        @Column(nullable = false)
	private Date endTime;
        
        @Column(nullable = false)
	private String footnote;
        
        @Column(nullable = false)
	private int breakDuration;
	
	public ExceptionalWorkingHours() {
		
	}
	
	public ExceptionalWorkingHours(Date date, Date startTime, Date endTime, String footnote, int breakDuration) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.footnote = footnote;
		this.breakDuration = breakDuration;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getFootnote() {
		return footnote;
	}
	public void setFootnote(String footnote) {
		this.footnote = footnote;
	}
	public int getBreakDuration() {
		return breakDuration;
	}
	public void setBreakDuration(int breakDuration) {
		this.breakDuration = breakDuration;
	}
	

}

