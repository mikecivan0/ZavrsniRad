/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.model;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import mikec.shedule.util.Tools;

@Entity(name = "exceptionalWorkingHours")
public class ExceptionalWorkingHours {
    
        public ExceptionalWorkingHours() {		
	}
    
        public ExceptionalWorkingHours(
                Date date, 
                Time startTime, 
                Time endTime, 
                String footnote, 
                int breakDuration) 
        {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.footnote = footnote;
		this.breakDuration = breakDuration;
	}
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @Column(nullable = false)
	private Date date;
        
        @Column(nullable = false)
	private Time startTime;
        
        @Column(nullable = false)
	private Time endTime;
        
        @Column(nullable = false)
	private String footnote;
        
        @Column(nullable = false)
	private int breakDuration;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
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

    @Override
    public String toString() {
        return Tools.formatDate(date) + ", " + footnote;
    }
	
        

}

