/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.view;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings;
import java.util.Date;
import mikec.shedule.util.Application;
import mikec.shedule.util.BaseException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import mikec.shedule.controller.ExceptionalWorkingHoursController;
import mikec.shedule.model.ExceptionalWorkingHours;
import mikec.shedule.util.Tools;

public class ExceptionalWorkingHoursScreen extends javax.swing.JFrame{
    
    private ExceptionalWorkingHoursController controller;

    public ExceptionalWorkingHoursScreen() throws BaseException {
        initComponents();
        controller = new ExceptionalWorkingHoursController();
        settings();
        loadList();
        selectFirstItemOnList();
    }
    
    public void settings(){
        setTitle(Application.getTitle("Exceptional working hours"));
        DatePickerSettings datePickerDateSettings = new DatePickerSettings();
        TimePickerSettings timePickerStartsSettings = new TimePickerSettings();        
        TimePickerSettings timePickerEndsSettings = new TimePickerSettings();
        datePickerDateSettings.setFormatForDatesCommonEra("dd.MM.yyyy.");
        timePickerStartsSettings.setFormatForDisplayTime("HH:mm");
        timePickerEndsSettings.setFormatForDisplayTime("HH:mm");
        dateDate.setSettings(datePickerDateSettings);
    }
    
    public void loadList(){
        DefaultListModel<ExceptionalWorkingHours> m = new DefaultListModel<>();        
        controller.read().forEach(p->{m.addElement(p);});        
        lstEntites.setModel(m);
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timePicker2 = new com.github.lgooddatepicker.components.TimePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstEntites = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        dateDate = new com.github.lgooddatepicker.components.DatePicker();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        timeStarts = new com.github.lgooddatepicker.components.TimePicker();
        timeEnds = new com.github.lgooddatepicker.components.TimePicker();
        jLabel5 = new javax.swing.JLabel();
        txtBreakDuration = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtFootnote = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lstEntites.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstEntites.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstEntitesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstEntites);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Date");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

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

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Start hours");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("End hours");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Break duration in minutes");

        txtBreakDuration.setMinimumSize(new java.awt.Dimension(7, 23));
        txtBreakDuration.setPreferredSize(new java.awt.Dimension(7, 23));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Footnote/description");

        txtFootnote.setColumns(20);
        txtFootnote.setRows(5);
        jScrollPane2.setViewportView(txtFootnote);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(timeStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(timeEnds, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtBreakDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(93, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeEnds, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBreakDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEdit)
                            .addComponent(btnDelete)
                            .addComponent(btnAdd))))
                .addGap(32, 32, 32))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            checkAreDatasValid();
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(getParent(), ex.getMessage());
            return;
        }
        controller.setEntity(new ExceptionalWorkingHours());
        setEntityValues();        
        try {
            controller.create();
            loadList();
            selectItem(controller.getEntity().getDate());
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(getParent(), ex.getMessage());
        }        
    }//GEN-LAST:event_btnAddActionPerformed
    
    public void setEntityValues(){
        var e = controller.getEntity();
        e.setDate(Tools.LocalDateToDate(dateDate.getDate()));
        e.setStartTime(Tools.localTimeToTime(timeStarts.getTime()));
        e.setEndTime(Tools.localTimeToTime(timeEnds.getTime()));
        e.setBreakDuration(Integer.parseInt(txtBreakDuration.getText()));
        e.setFootnote(txtFootnote.getText());        
    }
    
    private void checkAreDatasValid() throws BaseException{
        if(dateDate.getDate()==null){
            throw new BaseException("Date must not be empty");
        }
        if(timeStarts.getTime()==null){
            throw new BaseException("Starts time for monday cannot be empty");
        }
        if(timeEnds.getTime()==null){
            throw new BaseException("Ends time for monday cannot be empty");
        }
        if(txtBreakDuration.getText()==null){
            throw new BaseException("Break duration cannot be empty");
        }
        if(txtFootnote.getText()==null){
            throw new BaseException("Footnote/description cannot be empty");
        }
        try {
            Integer.parseInt(txtBreakDuration.getText());
        } catch (Exception e) {
            throw new BaseException("Only numbers are allowed in Break duration field");
        }
    }
    
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        setEntityValues();
        try {
            controller.update();
            loadList();
            selectItem(controller.getEntity().getDate());
        } catch (BaseException ex) {
           JOptionPane.showMessageDialog(getParent(), ex.getMessage());
        }
    }//GEN-LAST:event_btnEditActionPerformed
   
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       if(JOptionPane.showConfirmDialog(
               rootPane, 
               "Do you really want to delete this record?", 
               "Record delete", 
               JOptionPane.WARNING_MESSAGE)==0){
           try {
               controller.delete();
               loadList();
               selectFirstItemOnList();
           } catch (BaseException ex) {
               JOptionPane.showMessageDialog(getParent(), ex.getMessage());
           }
           
       }       
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void lstEntitesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstEntitesValueChanged
        if(evt.getValueIsAdjusting() || lstEntites.getSelectedValue()==null){
            return;
        }
        
        controller.setEntity(lstEntites.getSelectedValue());
        var e = controller.getEntity();
        dateDate.setDate(Tools.dateToLocalDate(e.getDate()));
        timeStarts.setTime(Tools.timeToLocalTime(e.getStartTime()));
        timeEnds.setTime(Tools.timeToLocalTime(e.getEndTime()));     
        txtBreakDuration.setText(String.valueOf(e.getBreakDuration()));  
        txtFootnote.setText(e.getFootnote());  
    }//GEN-LAST:event_lstEntitesValueChanged
   
    public void selectFirstItemOnList(){
       lstEntites.setSelectedIndex(0);
    }
    
    public void selectItem(Date date){
        for(int i = 0; i< lstEntites.getModel().getSize();i++){
            if(lstEntites.getModel().getElementAt(i).getDate().equals(date)){
                lstEntites.setSelectedIndex(i);
                return;
            }
        }
    }
    
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private com.github.lgooddatepicker.components.DatePicker dateDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<ExceptionalWorkingHours> lstEntites;
    private com.github.lgooddatepicker.components.TimePicker timeEnds;
    private com.github.lgooddatepicker.components.TimePicker timePicker2;
    private com.github.lgooddatepicker.components.TimePicker timeStarts;
    private javax.swing.JTextField txtBreakDuration;
    private javax.swing.JTextArea txtFootnote;
    // End of variables declaration//GEN-END:variables

    
}
