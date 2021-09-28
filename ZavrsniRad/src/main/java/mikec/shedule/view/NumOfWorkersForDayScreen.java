/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.view;

import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import mikec.shedule.util.Application;
import mikec.shedule.util.BaseException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import mikec.shedule.controller.NumOfWorkersForDayController;
import mikec.shedule.controller.NumOfWorkersForDayItemController;
import mikec.shedule.model.NumOfWorkersForDay;
import mikec.shedule.model.NumOfWorkersForDayItem;
import mikec.shedule.util.Tools;

/**
 *
 * @author dell
 */
public class NumOfWorkersForDayScreen extends javax.swing.JFrame{
    
    private NumOfWorkersForDayController controller;
    private NumOfWorkersForDayItemController nwfdItemController;
    private List<NumOfWorkersForDayItem> nwfdItems;  

    public NumOfWorkersForDayScreen() throws BaseException {
        initComponents();
        controller = new NumOfWorkersForDayController();
        nwfdItemController = new NumOfWorkersForDayItemController();
        nwfdItems = nwfdItemController.read();
        settings();
        loadList();
        selectFirstItemOnList();
    }
    
    public void settings(){
        setTitle(Application.getTitle("Number of workers for day"));
        DatePickerSettings datePickerStartsSettings = new DatePickerSettings();
        DatePickerSettings datePickerExpiresSettings = new DatePickerSettings();
        datePickerStartsSettings.setFormatForDatesCommonEra("dd.MM.yyyy.");
        datePickerExpiresSettings.setFormatForDatesCommonEra("dd.MM.yyyy.");
        dateStarts.setSettings(datePickerStartsSettings);
        dateExpires.setSettings(datePickerExpiresSettings);
    }
    
    public void loadList(){
        DefaultListModel<NumOfWorkersForDay> m = new DefaultListModel<>();        
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
        txtMondayNum = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTuesdayNum = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtWednesdayNum = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtThursdayNum = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFridayNum = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSaturdayNum = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtSundayNum = new javax.swing.JTextField();
        dateStarts = new com.github.lgooddatepicker.components.DatePicker();
        dateExpires = new com.github.lgooddatepicker.components.DatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lstEntites.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstEntitesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstEntites);

        jLabel1.setText("Starts date");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Monday");

        txtMondayNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMondayNumKeyPressed(evt);
            }
        });

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

        txtTuesdayNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTuesdayNumKeyPressed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Wednesday");

        txtWednesdayNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtWednesdayNumKeyPressed1(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Thursday");

        txtThursdayNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtThursdayNumKeyPressed(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Friday");

        txtFridayNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFridayNumKeyPressed(evt);
            }
        });

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Saturday");

        txtSaturdayNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSaturdayNumKeyPressed(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Sunday");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateStarts, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMondayNum, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTuesdayNum, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtWednesdayNum, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtThursdayNum, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFridayNum, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSaturdayNum, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSundayNum, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateExpires, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateStarts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateExpires, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMondayNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtTuesdayNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtWednesdayNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtThursdayNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtFridayNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtSaturdayNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtSundayNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAdd)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnEdit)
                                .addComponent(btnDelete)))
                        .addGap(0, 43, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            checkAreDatesValid();
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(getParent(), ex.getMessage());
            return;
        }
        int i = 1;
        boolean proceed = true;
        for(NumOfWorkersForDayItem nwfdItem : nwfdItems){
            if(proceed){
                controller.setEntity(new NumOfWorkersForDay());
                try {        
                   setEntityValuesInsert(nwfdItem, i++);
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
    
    public void setEntityValuesInsert(NumOfWorkersForDayItem nwfdItem, int i) throws BaseException{   
        var e = controller.getEntity();
        e.setStarts(Tools.LocalDateToDate(dateStarts.getDate()));
        e.setExpires(Tools.LocalDateToDate(dateExpires.getDate()));
        e.setNumOfWorkersForDayItem(nwfdItem);
        e.setValue(loadValueForField(i));
    }
    
    private void checkAreDatesValid() throws BaseException{
        if(dateStarts.getDate()==null){
            throw new BaseException("Stars date must not be empty");
        }
        if(dateExpires.getDate()==null){
            throw new BaseException("Expires date must not be empty");
        }
        if(Tools.LocalDateToDate(dateStarts.getDate()).after(Tools.LocalDateToDate(dateExpires.getDate()))){
            throw new BaseException("Starts date cannot be greather as expires date");
        }
    }
    
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        boolean proceed = true;
        try {
            checkAreDatesValid(); 
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
            List<NumOfWorkersForDay> nwfdByDateList = 
                    controller.fetchByStartsDate(
                            lstEntites.getSelectedValue().getStarts()
                    );
            for(NumOfWorkersForDay nwfd : nwfdByDateList){
                controller.setEntity(nwfd);
                try {        
                    setEntityValuesUpdate(nwfd, i++);
                    controller.update();
                } catch (BaseException ex) {
                    JOptionPane.showMessageDialog(getParent(), ex.getMessage());
                }
            }
            loadList();
            selectItem(controller.getEntity().getStarts()); 
        }     
    }//GEN-LAST:event_btnEditActionPerformed
        
    public void setEntityValuesUpdate(NumOfWorkersForDay nwfd, int i) throws BaseException{   
        nwfd.setStarts(Tools.LocalDateToDate(dateStarts.getDate()));
        nwfd.setExpires(Tools.LocalDateToDate(dateExpires.getDate()));
        nwfd.setValue(loadValueForField(i));
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
        
        NumOfWorkersForDay nwfd = lstEntites.getSelectedValue();
        Date starts = nwfd.getStarts();
        Date expires = nwfd.getExpires();        
       
        dateStarts.setDate(Tools.dateToLocalDate(starts));
        dateExpires.setDate(Tools.dateToLocalDate(expires));   
   
        controller.fetchAll().stream()
                .filter(record -> (Tools.formatDate(starts).contains(Tools.formatDate(record.getStarts()))))
                .forEachOrdered(record -> {
                    loadFields(record);
        });       
    }//GEN-LAST:event_lstEntitesValueChanged

    private void txtMondayNumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMondayNumKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTuesdayNum.requestFocus();
        }
    }//GEN-LAST:event_txtMondayNumKeyPressed

    private void txtTuesdayNumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTuesdayNumKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtWednesdayNum.requestFocus();
        }
    }//GEN-LAST:event_txtTuesdayNumKeyPressed

    private void txtWednesdayNumKeyPressed1(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtWednesdayNumKeyPressed1
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtThursdayNum.requestFocus();
        }
    }//GEN-LAST:event_txtWednesdayNumKeyPressed1

    private void txtThursdayNumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtThursdayNumKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtFridayNum.requestFocus();
        }
    }//GEN-LAST:event_txtThursdayNumKeyPressed

    private void txtFridayNumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFridayNumKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSaturdayNum.requestFocus();
        }
    }//GEN-LAST:event_txtFridayNumKeyPressed

    private void txtSaturdayNumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaturdayNumKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSundayNum.requestFocus();
        }
    }//GEN-LAST:event_txtSaturdayNumKeyPressed
   
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
     
    public void loadFields(NumOfWorkersForDay nwfd){
        String value = String.valueOf(nwfd.getValue());
        switch(nwfd.getNumOfWorkersForDayItem().getName()){
            case "monday" -> txtMondayNum.setText(value);
            case "tuesday" -> txtTuesdayNum.setText(value);
            case "wednesday" -> txtWednesdayNum.setText(value);
            case "thursday" -> txtThursdayNum.setText(value);
            case "friday" -> txtFridayNum.setText(value);
            case "saturday" -> txtSaturdayNum.setText(value);
            case "sunday" -> txtSundayNum.setText(value);
        }    
    }
     
     private int loadValueForField(int i){
         int val = 0;
         switch(i){
             case 1 -> {
                try {
                    val = Integer.parseInt(txtMondayNum.getText());
                } catch (Exception e) {
                }                    
            }
             case 2 -> {
                try {
                    val = Integer.parseInt(txtTuesdayNum.getText());
                } catch (Exception e) {
                }                    
            }
             case 3 -> {
                try {
                    val = Integer.parseInt(txtWednesdayNum.getText());
                } catch (Exception e) {
                }                    
            }
             case 4 -> {
                try {
                    val = Integer.parseInt(txtThursdayNum.getText());
                } catch (Exception e) {
                }                    
            }
             case 5 -> {
                try {
                    val = Integer.parseInt(txtFridayNum.getText());
                } catch (Exception e) {
                }                    
            }
             case 6 -> {
                try {
                    val = Integer.parseInt(txtSaturdayNum.getText());
                } catch (Exception e) {
                }                    
            }
             case 7 -> {
                try {
                    val = Integer.parseInt(txtSundayNum.getText());
                } catch (Exception e) {
                }                    
            }                   
         }
         return val;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<NumOfWorkersForDay> lstEntites;
    private javax.swing.JTextField txtFridayNum;
    private javax.swing.JTextField txtMondayNum;
    private javax.swing.JTextField txtSaturdayNum;
    private javax.swing.JTextField txtSundayNum;
    private javax.swing.JTextField txtThursdayNum;
    private javax.swing.JTextField txtTuesdayNum;
    private javax.swing.JTextField txtWednesdayNum;
    // End of variables declaration//GEN-END:variables

    
}
