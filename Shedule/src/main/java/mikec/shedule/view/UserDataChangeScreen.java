/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.view;


import java.awt.event.KeyEvent;
import mikec.shedule.controller.UserController;
import mikec.shedule.util.Application;
import mikec.shedule.util.BaseException;
import javax.swing.JOptionPane;
import mikec.shedule.util.Tools;

public class UserDataChangeScreen extends javax.swing.JFrame{
    
    private UserController controller;
    

    public UserDataChangeScreen() throws BaseException {
        initComponents();
        controller = new UserController();
        settings();
        load();
    }
    
    public void settings(){
        setTitle(Application.getTitle("Change login data"));
        setIconImage(Application.getIcon());
    }   
    
    public void load(){
        txtUsername.setText(Application.getUser().getUsername());
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
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        pswPassword = new javax.swing.JPasswordField();
        pswRePassword = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Username");

        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });

        jLabel2.setText("Password");

        btnSave.setText("Save changes");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        pswPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pswPasswordKeyPressed(evt);
            }
        });

        jLabel7.setText("Retype password");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(pswPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pswRePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(122, 122, 122))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pswPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pswRePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
   
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            checkAreDatesValid();            
            checkPasswordMatch();
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(getParent(), ex.getMessage());
            return;
        }
        controller.setEntity(Application.getUser());
        setEntityValues(String.copyValueOf(pswPassword.getPassword()).length()>0);
        try {
            controller.update();
            JOptionPane.showMessageDialog(getParent(), "New login datas are saved");
            dispose();
        } catch (BaseException ex) {
           JOptionPane.showMessageDialog(getParent(), ex.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            pswPassword.requestFocus();
        }
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void pswPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pswPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            pswRePassword.requestFocus();
        }
    }//GEN-LAST:event_pswPasswordKeyPressed
   
     public void setEntityValues(boolean addPass){
        var e = controller.getEntity();
        e.setUsername(txtUsername.getText());
        if(addPass){
            e.setPass(Tools.hashPass(String.copyValueOf(pswPassword.getPassword())));
        }  
    }
    
    private void checkAreDatesValid() throws BaseException{
        if(txtUsername.getText().trim().length()==0){
            throw new BaseException("Username cannot be empty");
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
    private javax.swing.ButtonGroup btnGroupLevel;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField pswPassword;
    private javax.swing.JPasswordField pswRePassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
