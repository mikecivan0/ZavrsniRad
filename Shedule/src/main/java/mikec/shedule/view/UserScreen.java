/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.view;

import java.awt.Dialog;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultComboBoxModel;
import mikec.shedule.controller.UserController;
import mikec.shedule.model.User;
import mikec.shedule.util.Application;
import mikec.shedule.util.BaseException;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import mikec.shedule.controller.PersonController;
import mikec.shedule.model.Person;
import mikec.shedule.util.Tools;

public class UserScreen extends javax.swing.JFrame{
    
    private UserController controller;
    private PersonController personController;

    public UserScreen() throws BaseException {
        initComponents();
        controller = new UserController();
        personController = new PersonController();
        settings();
        loadList();
        loadPersons();
        selectFirstItemOnList();
    }
    
    public void settings(){
        setTitle(Application.getTitle("Users"));
        setIconImage(Application.getIcon());
    }
    
    public void loadList(){
        DefaultListModel<User> m = new DefaultListModel<>();        
        controller.read().forEach(p->{m.addElement(p);});        
        lstEntites.setModel(m);
    }
    
    public void loadPersons() throws BaseException{
        DefaultComboBoxModel<Person> mp = new DefaultComboBoxModel<>();
        personController.read().forEach(pr->{mp.addElement(pr);});
        cmbPerson.setModel(mp);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupLevel = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstEntites = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPersonalNumber = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        pswPassword = new javax.swing.JPasswordField();
        chbActive = new javax.swing.JCheckBox();
        cmbPerson = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        pswRePassword = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        rbtUser = new javax.swing.JRadioButton();
        rbtAdmin = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        btnNewPerson = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lstEntites.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstEntites.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstEntitesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstEntites);

        jLabel1.setText("Username");

        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });

        jLabel2.setText("Password");

        jLabel3.setText("Personal number");

        txtPersonalNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPersonalNumberKeyPressed(evt);
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

        pswPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pswPasswordKeyPressed(evt);
            }
        });

        chbActive.setText("Is active");

        jLabel6.setText("Person");

        jLabel7.setText("Retype password");

        btnGroupLevel.add(rbtUser);
        rbtUser.setText("User");

        btnGroupLevel.add(rbtAdmin);
        rbtAdmin.setText("Admin");

        jLabel4.setText("Level");

        btnNewPerson.setText("New Person");
        btnNewPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewPersonActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPerson, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtPersonalNumber, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pswPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                .addComponent(pswRePassword, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(chbActive, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbtUser)
                                .addGap(18, 18, 18)
                                .addComponent(rbtAdmin))
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addComponent(btnAdd)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 51, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNewPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNewPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPersonalNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pswPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pswRePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtUser)
                            .addComponent(rbtAdmin))
                        .addGap(7, 7, 7)
                        .addComponent(chbActive)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAdd)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnEdit)
                                .addComponent(btnDelete)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            checkAreDatesValid();
            checkNewUserAreDatesValid();
            checkPasswordMatch();
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(getParent(), ex.getMessage());
            return;
        }
        controller.setEntity(new User());
        setEntityValues(true);        
        try {
            controller.create();
            loadList();
            selectItem(controller.getEntity().getUsername());
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(getParent(), ex.getMessage());
        }        
    }//GEN-LAST:event_btnAddActionPerformed
   
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        try {
            checkAreDatesValid();            
            checkPasswordMatch();
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(getParent(), ex.getMessage());
            return;
        }
        controller.setEntity(lstEntites.getSelectedValue());
        setEntityValues(String.copyValueOf(pswPassword.getPassword()).length()>0);
        try {
            controller.update();
            loadList();
            selectItem(controller.getEntity().getUsername());
        } catch (BaseException ex) {
           JOptionPane.showMessageDialog(getParent(), ex.getMessage());
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       if(JOptionPane.showConfirmDialog(
               rootPane, 
               "Do you really want to delete " + controller.getEntity().getUsername() + "?", 
               "User delete", 
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
        selectItemInComboBox(e.getPerson());
        txtUsername.setText(e.getUsername());
        txtPersonalNumber.setText(e.getPrs_id());
        if(e.getLevel() == 1) {
            rbtUser.setSelected(true);           
        }else{
            rbtAdmin.setSelected(true); 
        }
        chbActive.setSelected(e.isActive());        
    }//GEN-LAST:event_lstEntitesValueChanged

    private void btnNewPersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewPersonActionPerformed
        try {
            NewPersonDialogScreen dialog = new NewPersonDialogScreen(this, true);
            dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setLocationRelativeTo(this);
            dialog.addWindowListener(new WindowAdapter() 
            {
              public void windowClosed(WindowEvent e)
              {
                   JOptionPane.showMessageDialog(null, 
                           "After entering a new person, the 'Refresh' button must be clicked"
                           + "\nin order for the new person to appear in the list");
              }
            });
            dialog.setVisible(true);
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnNewPersonActionPerformed

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPersonalNumber.requestFocus();
        }
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void txtPersonalNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalNumberKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            pswPassword.requestFocus();
        }
    }//GEN-LAST:event_txtPersonalNumberKeyPressed

    private void pswPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pswPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            pswRePassword.requestFocus();
        }
    }//GEN-LAST:event_pswPasswordKeyPressed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        try {
            loadPersons(); 
            selectItemInComboBox(lstEntites.getSelectedValue().getPerson());
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnRefreshActionPerformed
   
     public void setEntityValues(boolean addPass){
        var e = controller.getEntity();
        e.setPerson((Person) cmbPerson.getSelectedItem());
        e.setUsername(txtUsername.getText());
        e.setPrs_id(txtPersonalNumber.getText());
        if(addPass){
            e.setPass(Tools.hashPass(String.copyValueOf(pswPassword.getPassword())));
        }        
        e.setLevel((rbtUser.isSelected()) ? 1 : 2);        
        e.setActive(chbActive.isSelected());
    }
     
    public void selectFirstItemOnList(){
       lstEntites.setSelectedIndex(0);
    }
    
    public void selectItem(String username){
        for(int i = 0; i< lstEntites.getModel().getSize();i++){
            if(lstEntites.getModel().getElementAt(i).getUsername().equals(username)){
                lstEntites.setSelectedIndex(i);
                return;
            }
        }
    }
    
    private void selectItemInComboBox(Person person){
        DefaultComboBoxModel<Person> m = (DefaultComboBoxModel<Person>) cmbPerson.getModel();
        for(int i=0;i<m.getSize();i++){
            if(m.getElementAt(i).getId().equals(person.getId())){
                cmbPerson.setSelectedIndex(i);
                break;
            }
        }    
    }
    
    private void checkAreDatesValid() throws BaseException{
        if(txtUsername.getText().trim().length()==0){
            throw new BaseException("Username cannot be empty");
        }
         if(txtPersonalNumber.getText().trim().length()==0){
            throw new BaseException("Personal number cannot be empty");
        }
    }
     
    private void checkNewUserAreDatesValid() throws BaseException{
        if(String.copyValueOf(pswPassword.getPassword()).trim().length()==0){
            throw new BaseException("Password cannot be empty");
        }
        if(String.copyValueOf(pswRePassword.getPassword()).trim().length()==0){
            throw new BaseException("Retype password cannot be empty");
        }
    }
    
    private void checkPasswordMatch() throws BaseException{
        String pass = String.copyValueOf(pswPassword.getPassword());
        String rePass = String.copyValueOf(pswRePassword.getPassword());
        if(!pass.equals(rePass)){
            throw new BaseException("Password and Retype password not match");
        }
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.ButtonGroup btnGroupLevel;
    private javax.swing.JButton btnNewPerson;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JCheckBox chbActive;
    private javax.swing.JComboBox<Person> cmbPerson;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<User> lstEntites;
    private javax.swing.JPasswordField pswPassword;
    private javax.swing.JPasswordField pswRePassword;
    private javax.swing.JRadioButton rbtAdmin;
    private javax.swing.JRadioButton rbtUser;
    private javax.swing.JTextField txtPersonalNumber;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
