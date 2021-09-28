/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.view;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import mikec.shedule.util.Application;
import mikec.shedule.util.BaseException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import mikec.shedule.controller.RegularWorkingHoursController;
import mikec.shedule.controller.RegularWorkingHoursItemController;
import mikec.shedule.model.RegularWorkingHours;
import mikec.shedule.model.RegularWorkingHoursItem;
import mikec.shedule.util.Tools;

/**
 *
 * @author dell
 */
public class RegularWorkingHoursScreen extends javax.swing.JFrame{
    
    private RegularWorkingHoursController controller;
    private RegularWorkingHoursItemController rwhItemController;
    private List<RegularWorkingHoursItem> rwhItems;  

    public RegularWorkingHoursScreen() throws BaseException {
        initComponents();
        controller = new RegularWorkingHoursController();
        rwhItemController = new RegularWorkingHoursItemController();
        rwhItems = rwhItemController.read();
        settings();
        loadList();
        selectFirstItemOnList();
    }
    
    public void settings(){
        setTitle(Application.getTitle("Regular working hours"));
        DatePickerSettings datePickerStartsSettings = new DatePickerSettings();
        DatePickerSettings datePickerExpiresSettings = new DatePickerSettings();
        TimePickerSettings timePickerMonStartsSettings = new TimePickerSettings();
        TimePickerSettings timePickerMonEndsSettings = new TimePickerSettings();
        TimePickerSettings timePickerTueStartsSettings = new TimePickerSettings();
        TimePickerSettings timePickerTueEndsSettings = new TimePickerSettings();
        TimePickerSettings timePickerWedStartsSettings = new TimePickerSettings();
        TimePickerSettings timePickerWedEndsSettings = new TimePickerSettings();
        TimePickerSettings timePickerFriStartsSettings = new TimePickerSettings();
        TimePickerSettings timePickerFriEndsSettings = new TimePickerSettings();
        TimePickerSettings timePickerSatStartsSettings = new TimePickerSettings();
        TimePickerSettings timePickerSatEndsSettings = new TimePickerSettings();
        TimePickerSettings timePickerSunStartsSettings = new TimePickerSettings();        
        TimePickerSettings timePickerSunEndsSettings = new TimePickerSettings();
        datePickerStartsSettings.setFormatForDatesCommonEra("dd.MM.yyyy.");
        datePickerExpiresSettings.setFormatForDatesCommonEra("dd.MM.yyyy.");
        timePickerMonStartsSettings.setFormatForDisplayTime("HH:mm");
        timePickerMonEndsSettings.setFormatForDisplayTime("HH:mm");
        timePickerTueStartsSettings.setFormatForDisplayTime("HH:mm");
        timePickerTueEndsSettings.setFormatForDisplayTime("HH:mm");
        timePickerWedStartsSettings.setFormatForDisplayTime("HH:mm");
        timePickerWedEndsSettings.setFormatForDisplayTime("HH:mm");
        timePickerFriStartsSettings.setFormatForDisplayTime("HH:mm");
        timePickerFriEndsSettings.setFormatForDisplayTime("HH:mm");
        timePickerSatStartsSettings.setFormatForDisplayTime("HH:mm");
        timePickerSatEndsSettings.setFormatForDisplayTime("HH:mm");
        timePickerSunStartsSettings.setFormatForDisplayTime("HH:mm");
        timePickerSunEndsSettings.setFormatForDisplayTime("HH:mm");
        dateStarts.setSettings(datePickerStartsSettings);
        dateExpires.setSettings(datePickerExpiresSettings);
    }
    
    public void loadList(){
        DefaultListModel<RegularWorkingHours> m = new DefaultListModel<>();        
        controller.read().forEach(p->{m.addElement(p);});        
        lstEntites.setModel(m);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstEntites = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        dateStarts = new com.github.lgooddatepicker.components.DatePicker();
        dateExpires = new com.github.lgooddatepicker.components.DatePicker();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        timeWedEnds = new com.github.lgooddatepicker.components.TimePicker();
        timeMonStarts = new com.github.lgooddatepicker.components.TimePicker();
        timeTueStarts = new com.github.lgooddatepicker.components.TimePicker();
        timeTueEnds = new com.github.lgooddatepicker.components.TimePicker();
        timeWedStarts = new com.github.lgooddatepicker.components.TimePicker();
        timeMonEnds = new com.github.lgooddatepicker.components.TimePicker();
        timeThuStarts = new com.github.lgooddatepicker.components.TimePicker();
        timeThuEnds = new com.github.lgooddatepicker.components.TimePicker();
        timeFriStarts = new com.github.lgooddatepicker.components.TimePicker();
        timeFriEnds = new com.github.lgooddatepicker.components.TimePicker();
        timeSatStarts = new com.github.lgooddatepicker.components.TimePicker();
        timeSatEnds = new com.github.lgooddatepicker.components.TimePicker();
        timeSunStarts = new com.github.lgooddatepicker.components.TimePicker();
        timeSunEnds = new com.github.lgooddatepicker.components.TimePicker();
        jLabel5 = new javax.swing.JLabel();
        txtBreakDuration = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lstEntites.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstEntitesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstEntites);

        jLabel1.setText("Starts date");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Monday");

        btnAdd.setText("Add new");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel6.setText("Expires date");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Tuesday");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Wednesday");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Thursday");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Friday");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Saturday");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Sunday");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Start hours");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("End hours");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Break duration in minutes");

        txtBreakDuration.setMinimumSize(new java.awt.Dimension(7, 23));
        txtBreakDuration.setPreferredSize(new java.awt.Dimension(7, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtBreakDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timeMonStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(timeThuStarts, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(timeTueStarts, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(timeWedStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(timeFriStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(timeSatStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(timeSunStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeSunEnds, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeSatEnds, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeFriEnds, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeThuEnds, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeWedEnds, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeTueEnds, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateExpires, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeMonEnds, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateExpires, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeMonStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeMonEnds, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeTueEnds, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeTueStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeWedStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeWedEnds, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeThuStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeThuEnds, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeFriStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeFriEnds, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeSatStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeSatEnds, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeSunStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeSunEnds, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtBreakDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEdit)
                            .addComponent(btnDelete)
                            .addComponent(btnAdd))
                        .addGap(20, 20, 20))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            checkAreDatesAndTimesValid();
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(getParent(), ex.getMessage());
            return;
        }
        int i = 1;
        boolean proceed = true;
        for(RegularWorkingHoursItem rwhItem : rwhItems){
            if(proceed){
                controller.setEntity(new RegularWorkingHours());
                try {        
                   setEntityValuesInsert(rwhItem, i++);
               } catch (BaseException ex) {
                    JOptionPane.showMessageDialog(getParent(), ex.getMessage());
               }
               try {
                   controller.create();
               } catch (BaseException ex) {
                   JOptionPane.showMessageDialog(getParent(), ex.getMessage());
                   proceed=false;
               }      
            }            
        }
       loadList();
       selectItem(controller.getEntity().getStarts());
    }//GEN-LAST:event_btnAddActionPerformed
    
    public void setEntityValuesInsert(RegularWorkingHoursItem rwhItem, int i) throws BaseException{   
        var e = controller.getEntity();
        e.setStarts(Tools.LocalDateToDate(dateStarts.getDate()));
        e.setExpires(Tools.LocalDateToDate(dateExpires.getDate()));
        e.setRegularWorkingHoursItem(rwhItem);
        e.setValue(loadValueForField(i));
    }
    
    private void checkAreDatesAndTimesValid() throws BaseException{
        if(dateStarts.getDate()==null){
            throw new BaseException("Stars date must not be empty");
        }
        if(dateExpires.getDate()==null){
            throw new BaseException("Expires date must not be empty");
        }
        if(Tools.LocalDateToDate(dateStarts.getDate()).after(Tools.LocalDateToDate(dateExpires.getDate()))){
            throw new BaseException("Starts date cannot be greather as expires date");
        }
        if(timeMonStarts.getTime()==null){
            throw new BaseException("Starts time for monday cannot be empty");
        }
        if(timeMonEnds.getTime()==null){
            throw new BaseException("Ends time for monday cannot be empty");
        }
        if(timeTueStarts.getTime()==null){
            throw new BaseException("Starts time for tuesday cannot be empty");
        }
        if(timeTueEnds.getTime()==null){
            throw new BaseException("Ends time for tuesday cannot be empty");
        }
        if(timeWedStarts.getTime()==null){
            throw new BaseException("Starts time for wednesday cannot be empty");
        }
        if(timeWedEnds.getTime()==null){
            throw new BaseException("Ends time for wednesday cannot be empty");
        }
        if(timeThuStarts.getTime()==null){
            throw new BaseException("Starts time for thursday cannot be empty");
        }
        if(timeThuEnds.getTime()==null){
            throw new BaseException("Ends time for thursday cannot be empty");
        }
        if(timeFriStarts.getTime()==null){
            throw new BaseException("Starts time for friday cannot be empty");
        }
        if(timeFriEnds.getTime()==null){
            throw new BaseException("Ends time for friday cannot be empty");
        }
        if(timeSatStarts.getTime()==null){
            throw new BaseException("Starts time for saturday cannot be empty");
        }
        if(timeSatEnds.getTime()==null){
            throw new BaseException("Ends time for saturday cannot be empty");
        }
        if(timeSunStarts.getTime()==null){
            throw new BaseException("Starts time for sunday cannot be empty");
        }
        if(timeSunEnds.getTime()==null){
            throw new BaseException("Ends time for sunday cannot be empty");
        }
        if(txtBreakDuration.getText()==null){
            throw new BaseException("Break duration cannot be empty");
        }
        try {
            Integer.parseInt(txtBreakDuration.getText());
        } catch (Exception e) {
            throw new BaseException("Only numbers are allowed in Break duration field");
        }
    }
    
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        boolean proceed = true;
        try {
            checkAreDatesAndTimesValid(); 
            controller.checkUpdateOverlap(
                    Tools.LocalDateToDate(dateStarts.getDate()), 
                    Tools.LocalDateToDate(dateExpires.getDate()), 
                    lstEntites.getSelectedValue().getStarts());
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(getParent(), ex.getMessage());
            proceed = false;
        }  
        
        if(proceed){
            int i = 1;
            List<RegularWorkingHours> rwhByDateList = 
                    controller.fetchByStartsDate(
                            lstEntites.getSelectedValue().getStarts()
                    );
            for(RegularWorkingHours rwh : rwhByDateList){
                controller.setEntity(rwh);
                try {        
                    setEntityValuesUpdate(rwh, i++);
                    controller.update();
                } catch (BaseException ex) {
                    JOptionPane.showMessageDialog(getParent(), ex.getMessage());
                }
            }
            loadList();
            selectItem(controller.getEntity().getStarts()); 
        }     
    }//GEN-LAST:event_btnEditActionPerformed
        
    public void setEntityValuesUpdate(RegularWorkingHours rwh, int i) throws BaseException{   
        rwh.setStarts(Tools.LocalDateToDate(dateStarts.getDate()));
        rwh.setExpires(Tools.LocalDateToDate(dateExpires.getDate()));
        rwh.setBreakDurationInMinutes(Integer.parseInt(txtBreakDuration.getText()));
        rwh.setValue(loadValueForField(i));
    }
    
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       if(JOptionPane.showConfirmDialog(
               rootPane, 
               "Do you really want to delete this record?", 
               "Record delete", 
               JOptionPane.WARNING_MESSAGE)==0){
           controller.delete(lstEntites.getSelectedValue().getStarts());
           loadList();
           selectFirstItemOnList();
       }       
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void lstEntitesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstEntitesValueChanged
        if(evt.getValueIsAdjusting() || lstEntites.getSelectedValue()==null){
            return;
        }
        
        RegularWorkingHours rwh = lstEntites.getSelectedValue();
        Date starts = rwh.getStarts();
        Date expires = rwh.getExpires();  
        String breakDuration = String.valueOf(rwh.getBreakDurationInMinutes());
       
        dateStarts.setDate(Tools.dateToLocalDate(starts));
        dateExpires.setDate(Tools.dateToLocalDate(expires));
        txtBreakDuration.setText(breakDuration);
   
        controller.fetchAll().stream()
                .filter(record -> (Tools.formatDate(starts).contains(Tools.formatDate(record.getStarts()))))
                .forEachOrdered(record -> {
                    loadFields(record);
        });       
    }//GEN-LAST:event_lstEntitesValueChanged
   
    public void selectFirstItemOnList(){
       lstEntites.setSelectedIndex(0);
    }
    
    public void selectItem(Date date){
        for(int i = 0; i< lstEntites.getModel().getSize();i++){
            if(lstEntites.getModel().getElementAt(i).getStarts().equals(date)){
                lstEntites.setSelectedIndex(i);
                return;
            }
        }
    }
     
    public void loadFields(RegularWorkingHours rwh){
        LocalTime value = Tools.timeToLocalTime(rwh.getValue());
        switch(rwh.getRegularWorkingHoursItem().getName()){
            case "monStarts" -> timeMonStarts.setTime(value);
            case "monEnds" -> timeMonEnds.setTime(value);
            case "tueStarts" -> timeTueStarts.setTime(value);
            case "tueEnds" -> timeTueEnds.setTime(value);
            case "wedStarts" -> timeWedStarts.setTime(value);
            case "wedEnds" -> timeWedEnds.setTime(value);
            case "thuStarts" -> timeThuStarts.setTime(value);
            case "thuEnds" -> timeThuEnds.setTime(value);
            case "friStarts" -> timeFriStarts.setTime(value);
            case "friEnds" -> timeFriEnds.setTime(value);
            case "satStarts" -> timeSatStarts.setTime(value);
            case "satEnds" -> timeSatEnds.setTime(value);
            case "sunStarts" -> timeSunStarts.setTime(value);
            case "sunEnds" -> timeSunEnds.setTime(value);
        }    
    }
     
     private Time loadValueForField(int i){
         LocalTime val = null;
         switch(i){
             case 1 -> val = timeMonStarts.getTime();
             case 2 -> val = timeMonEnds.getTime();
             case 3 -> val = timeTueStarts.getTime();
             case 4 -> val = timeTueEnds.getTime();
             case 5 -> val = timeWedStarts.getTime();
             case 6 -> val = timeWedEnds.getTime();
             case 7 -> val = timeThuStarts.getTime();         
             case 8 -> val = timeThuEnds.getTime();         
             case 9 -> val = timeFriStarts.getTime();         
             case 10 -> val = timeFriEnds.getTime();         
             case 11 -> val = timeSatStarts.getTime();         
             case 12 -> val = timeSatEnds.getTime();         
             case 13 -> val = timeSunStarts.getTime();         
             case 14 -> val = timeSunEnds.getTime();         
            }   
         return Tools.localTimeToTime(val);
         }
         
     
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private com.github.lgooddatepicker.components.DatePicker dateExpires;
    private com.github.lgooddatepicker.components.DatePicker dateStarts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<RegularWorkingHours> lstEntites;
    private com.github.lgooddatepicker.components.TimePicker timeFriEnds;
    private com.github.lgooddatepicker.components.TimePicker timeFriStarts;
    private com.github.lgooddatepicker.components.TimePicker timeMonEnds;
    private com.github.lgooddatepicker.components.TimePicker timeMonStarts;
    private com.github.lgooddatepicker.components.TimePicker timeSatEnds;
    private com.github.lgooddatepicker.components.TimePicker timeSatStarts;
    private com.github.lgooddatepicker.components.TimePicker timeSunEnds;
    private com.github.lgooddatepicker.components.TimePicker timeSunStarts;
    private com.github.lgooddatepicker.components.TimePicker timeThuEnds;
    private com.github.lgooddatepicker.components.TimePicker timeThuStarts;
    private com.github.lgooddatepicker.components.TimePicker timeTueEnds;
    private com.github.lgooddatepicker.components.TimePicker timeTueStarts;
    private com.github.lgooddatepicker.components.TimePicker timeWedEnds;
    private com.github.lgooddatepicker.components.TimePicker timeWedStarts;
    private javax.swing.JTextField txtBreakDuration;
    // End of variables declaration//GEN-END:variables

    
}
