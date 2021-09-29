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

@Entity(name = "records")
public class Record {

    public Record() {
    }

    public Record(Long id, User user, Label label, Date date, boolean withBreak) {
        this.id = id;
        this.user = user;
        this.label = label;
        this.date = date;
        this.withBreak = withBreak;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne()
    @JoinColumn(name = "userId")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "labelId")
    private Label label;
    
    @Column(nullable = false)
    private Date date;
    
    @Column(nullable = false)
    private boolean withBreak;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

     public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isWithBreak() {
        return withBreak;
    }

    public void setWithBreak(boolean withBreak) {
        this.withBreak = withBreak;
    }   

}
