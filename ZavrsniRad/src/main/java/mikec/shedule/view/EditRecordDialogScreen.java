/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.view;

import com.github.lgooddatepicker.components.DatePickerSettings;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import mikec.shedule.controller.RecordController;
import mikec.shedule.model.Record;
import mikec.shedule.util.Application;
import mikec.shedule.util.BaseException;
import javax.swing.JOptionPane;
import mikec.shedule.controller.LabelController;
import mikec.shedule.controller.UserController;
import mikec.shedule.model.Label;
import mikec.shedule.model.User;
import mikec.shedule.util.Tools;

/**
 *
 * @author Ivan
 */
public class EditRecordDialogScreen extends javax.swing.JDialog {

    private LabelController labelController;
    private UserController userController;
    private RecordController recordController;
    private List<Label> labelList;
    private List<User> userList;
    private Record record;

    public EditRecordDialogScreen(java.awt.Frame parent, boolean modal, Record record) throws BaseException {
        super(parent, modal);
        initComponents();
        this.record = record;
        recordController = new RecordController();
        labelController = new LabelController();
        userController = new UserController();
        settings();
        load();
    }

    public void settings() {
        setTitle(Application.getTitle("Edit record"));
        setIconImage(Application.getIcon());
        DatePickerSettings dpSettings = new DatePickerSettings();
        dpSettings.setFormatForDatesCommonEra("dd.MM.yyyy.");
        dpDate.setSettings(dpSettings);
        setLocationRelativeTo(null);
    }

    private void load() throws BaseException {
        loadLabels();
        loadUsers();
        setDate();
    }

    private void loadLabels() {
        labelList = labelController.read();
        Vector labelComboBoxItems = new Vector();
        Label label = new Label();
        label.setName("Delete");
        label.setAbbreviation("-");
        labelComboBoxItems.add(label);
        for (Label l : labelList) {
            labelComboBoxItems.add(l);
        }
        DefaultComboBoxModel cbLabelModel = new DefaultComboBoxModel(labelComboBoxItems);
        cmbLabel.setModel(cbLabelModel);
        try {
            cmbLabel.setSelectedIndex(findLabelIndex(record.getLabel()));
        } catch (Exception e) {
            cmbLabel.setSelectedIndex(0);
        }
    }

    private int findLabelIndex(Label l) {
        int index = 0;
        for (Label lb : labelList) {
            if (lb.getAbbreviation() == record.getLabel().getAbbreviation()) {
                ++index;
                break;
            }
            index++;
        }
        return index;
    }

    private void loadUsers() {
        userList = userController.read();
        Vector userComboBoxItems = new Vector();
        for (User u : userList) {
            userComboBoxItems.add(u);
        }
        DefaultComboBoxModel cbUSerModel = new DefaultComboBoxModel(userComboBoxItems);
        cmbUser.setModel(cbUSerModel);
        try {
            cmbUser.setSelectedIndex(findUserIndex(record.getUser()));
        } catch (Exception e) {
            cmbUser.setSelectedIndex(0);
        }
    }

    private int findUserIndex(User u) {
        int index = 0;
        for (User us : userList) {
            if (us.getUsername() == record.getUser().getUsername()) {
                break;
            }
            index++;
        }
        return index;
    }

    private void setDate() {
        dpDate.setDate(Tools.dateToLocalDate(record.getDate()));
    }

    private void showTable() {
        String year = Tools.formatYear(Tools.LocalDateToDate(dpDate.getDate()));
        String month = Tools.formatMonth(Tools.LocalDateToDate(dpDate.getDate()));
        try {
            dispose();
            new SheduleAdminDisplayTableScreen(String.valueOf(year), String.valueOf(month));
        } catch (BaseException ex) {

        }
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
        jLabel3 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        cmbLabel = new javax.swing.JComboBox<>();
        cmbUser = new javax.swing.JComboBox<>();
        dpDate = new com.github.lgooddatepicker.components.DatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Label");

        jLabel2.setText("User");

        jLabel3.setText("Date");

        btnAdd.setText("Add / Edit / Delete");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnClose.setText("Cancel");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(cmbUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(cmbLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(cmbLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dpDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnClose))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        recordController.setEntity(new Record());
        setEntityValues();
        try {
            recordController.create();
            btnCloseActionPerformed(null);
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(getParent(), ex.getMessage());
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    public void setEntityValues() {
        var e = recordController.getEntity();
        e.setUser((User) cmbUser.getSelectedItem());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClose;
    private javax.swing.JComboBox<Label> cmbLabel;
    private javax.swing.JComboBox<User> cmbUser;
    private com.github.lgooddatepicker.components.DatePicker dpDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}