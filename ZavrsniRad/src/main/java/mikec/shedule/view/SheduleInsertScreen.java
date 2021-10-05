/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.view;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import mikec.shedule.controller.UserController;
import mikec.shedule.controller.LabelController;
import mikec.shedule.model.Record;
import mikec.shedule.util.Application;
import mikec.shedule.util.BaseException;
import javax.swing.JOptionPane;
import mikec.shedule.controller.RecordController;
import mikec.shedule.model.Label;
import mikec.shedule.model.User;
import mikec.shedule.util.Tools;

public class SheduleInsertScreen extends javax.swing.JFrame{
    
    private RecordController controller;
    private UserController userController;
    private LabelController labelController;
    

    public SheduleInsertScreen() throws BaseException {
        initComponents();
        controller = new RecordController();
        userController = new UserController();
        labelController = new LabelController();
        settings();
        load();
    }
    
    public void settings(){
        setTitle(Application.getTitle("Insert new record"));
    }   
    
    public void load(){
        setDate();
        loadUsers();
        loadLabels();
    }    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnInsert = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        dtDate = new com.github.lgooddatepicker.components.DatePicker();
        cmbUser = new javax.swing.JComboBox<>();
        cmbLabel = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Date");

        jLabel2.setText("User");

        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        jLabel7.setText("Label");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(cmbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(dtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(9, 9, 9)
                .addComponent(cmbLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnInsert)
                .addGap(26, 26, 26))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
   
    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        try {
            checkAreDatesValid(); 
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(getParent(), ex.getMessage());
            return;
        }
        controller.setEntity(new Record());
        setEntityValues();
        try {
            controller.create();
            JOptionPane.showMessageDialog(getParent(), "New record is saved");
            new SheduleMainScreen().setVisible(true);
            dispose();            
        } catch (BaseException ex) {
           JOptionPane.showMessageDialog(getParent(), ex.getMessage());
        }
    }//GEN-LAST:event_btnInsertActionPerformed
   
     public void setEntityValues(){
        var e = controller.getEntity();
        e.setDate(Tools.LocalDateToDate(dtDate.getDate()));
        e.setUser((User) cmbUser.getSelectedItem());
        e.setLabel((Label) cmbLabel.getSelectedItem());
    }
    
    private void checkAreDatesValid() throws BaseException{
        if(dtDate.getDate()==null){
            throw new BaseException("Date cannot be empty");
        }
    }
    
    public void setDate(){
        dtDate.setDateToToday();
    }
    
    public void loadUsers(){
        DefaultComboBoxModel<User> userModel = new DefaultComboBoxModel<>();
        userController.getActiveUsers().forEach(pr->{userModel.addElement(pr);});
        cmbUser.setModel(userModel);
    }
    
    public void loadLabels(){
        List<Label> labels = labelController.read();       
        DefaultComboBoxModel labelComboBox = new DefaultComboBoxModel();
        for(Label label : labels){
            labelComboBox.addElement(label);
        }
        cmbLabel.setModel(labelComboBox);
    }
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInsert;
    private javax.swing.JComboBox<Label> cmbLabel;
    private javax.swing.JComboBox<User> cmbUser;
    private com.github.lgooddatepicker.components.DatePicker dtDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
