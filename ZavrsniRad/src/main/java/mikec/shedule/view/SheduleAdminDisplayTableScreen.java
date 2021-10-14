/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mikec.shedule.controller.LabelController;
import mikec.shedule.controller.NumOfWorkersForDayController;
import mikec.shedule.controller.RecordController;
import mikec.shedule.controller.UserController;
import mikec.shedule.model.Label;
import mikec.shedule.model.NumOfWorkersForDay;
import mikec.shedule.model.User;
import mikec.shedule.model.Record;
import mikec.shedule.util.Application;
import mikec.shedule.util.BaseException;
import mikec.shedule.util.Tools;

public class SheduleAdminDisplayTableScreen {

    private int rows;
    private int cols;
    private int dataColsWidth;
    private int nameColWidth;
    private int rowsHeigth;
    private int totalWidth;
    private int totalHeigth;
    private DefaultTableModel defaultTableModel;
    private List<User> usersInMonth;
    private List<Record> recordsForMonth;
    private RecordController recordController;
    private NumOfWorkersForDayController nwfdController;
    private Integer yearInt, monthInt, numOfDaysInMoth;
    private String strYear, strMonth;
    private JTable table;
    private JFrame frame;
    private Record record;

    public SheduleAdminDisplayTableScreen(String year, String month) throws BaseException {
        table = new JTable();

        frame = new JFrame();
        frame.setIconImage(Application.getIcon());
        recordController = new RecordController();
        nwfdController = new NumOfWorkersForDayController();
        yearInt = Integer.parseInt(year);
        monthInt = Integer.parseInt(month);
        strYear = year;
        strMonth = month;
        frameSettings();
        numOfDaysInMoth = calculateNumOfDaysInMonth(yearInt, monthInt);
        recordsForMonth = recordController.getRecordsForMonth(year, month);
        loadUsersInMonth();
        setProperties();
        generateDefaultTableModel();
        createTable();
        display();
    }

    private void createTable() throws BaseException {
        addUsersColumn();
        addRestOfColumns();
        setTableProperties();
        addRows();
    }

    private Integer calculateNumOfDaysInMonth(Integer yearInt, Integer monthInt) {
        YearMonth obj;
        obj = YearMonth.of(yearInt, monthInt);
        return obj.lengthOfMonth();
    }

    private void frameSettings() {
        frame.setTitle(Application.getTitle("Shedule for " + strMonth + "/" + strYear));
    }

    private void loadUsersInMonth() {
        usersInMonth = new ArrayList<>();
        for (Record r : recordsForMonth) {
            if (!usersInMonth.contains(r.getUser())) {
                usersInMonth.add(r.getUser());
            }
        }
    }

    private void generateDefaultTableModel() {
        defaultTableModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };
    }

    private void setProperties() {
        rows = usersInMonth.size();
        cols = numOfDaysInMoth;
        nameColWidth = 250;
        dataColsWidth = 25;
        rowsHeigth = 30;
        totalWidth = cols * dataColsWidth + nameColWidth;
        totalHeigth = rows * rowsHeigth + 100;
    }

    private void addUsersColumn() {
        defaultTableModel.addColumn(strMonth + "/" + strYear);
    }

    private void addRestOfColumns() {
        for (int i = 1; i <= cols; i++) {
            defaultTableModel.addColumn(i);
        }
    }

    private void addRows() throws BaseException {
        addUserRows();
        addLastTwoRows();
        populateLastRow();
    }

    private void addUserRows() throws BaseException {
        
       
        for (User u : usersInMonth) {
            
            Object[] array = new Object[numOfDaysInMoth + 1];
            array[0] = u.getPerson();
            for (int i = 1; i <= numOfDaysInMoth; i++) {
                Record rec = new Record();
                rec.setUser(u);
                Date iterDate = Tools.parseDate(i + "." + strMonth + "." + strYear + ".");                
                rec.setDate(iterDate);
                rec.setLabel(null);
                for (Record rc : recordsForMonth) {
                    String iterDateStr = Tools.formatDate(iterDate);
                    String curDateStr = Tools.formatDate(rc.getDate());
                    if (rc.getUser().getPrs_id().equals(u.getPrs_id())
                            && iterDateStr.equals(curDateStr)) {
                        rec.setLabel(rc.getLabel());
                        break;
                    }
                }
                array[i] = rec;
            }
            defaultTableModel.addRow(array);
        }
    }

    private void addLastTwoRows() {
        String[] array = new String[numOfDaysInMoth + 1];
        for (int i = 0; i <= numOfDaysInMoth; i++) {
            array[i] = "";
        }
        defaultTableModel.addRow(array);

        array[0] = "Too many/deficit of workers";
        for (int i = 1; i <= numOfDaysInMoth; i++) {
            array[i] = "";
        }
        defaultTableModel.addRow(array);

    }

    private void populateLastRow() throws BaseException {
        for (int i = 1; i <= numOfDaysInMoth; i++) {
            String result = "N/A", nwfd = "", enrolledWorkers = "";
            Date date = Tools.parseDate(i + "." + strMonth + "." + strYear + ".");

            for (NumOfWorkersForDay numwfd : nwfdController.fetchAll()) {
                if (Tools.isDateBetween(numwfd.getStarts(), numwfd.getExpires(), date)
                        && Tools.isDayInWeek(
                                String.valueOf(numwfd.getNumOfWorkersForDayItem().getName()),
                                date)) {
                    nwfd = String.valueOf(numwfd.getValue());
                    break;
                }
            }
            enrolledWorkers = String.valueOf(recordController.getNumberOfWorkersForDate(date));

            if (nwfd != "") {
                int nwfdInt = Integer.parseInt(nwfd);
                int enrolledWorkersInt = Integer.parseInt(enrolledWorkers);
                int resultInt = enrolledWorkersInt - nwfdInt;
                if (resultInt == 0) {
                    result = "OK";
                } else {
                    result = formatSign(resultInt);
                }

            }

            defaultTableModel.setValueAt(result, table.getRowCount() - 1, i);
        }
    }

    private String formatSign(int number) {
        return (number > 0 ? "+" : "") + number;
    }

    private void setTableProperties() {
        table = new JTable(defaultTableModel);

        table.getColumnModel().getColumn(0).setPreferredWidth(nameColWidth);
        table.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(nameColWidth);
        for (int i = 1; i <= cols; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(dataColsWidth);
            table.getTableHeader().getColumnModel().getColumn(i).setPreferredWidth(dataColsWidth);
        }
        table.setModel(defaultTableModel);
        addMouseListener();
    }

    private void addMouseListener() {
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());                    
                    try {
                        record = (Record) table.getValueAt(row, col);
                        displayDialog();
                    } catch (Exception ex) {

                    }
                }
            }
        });
    }

    private void displayDialog() throws BaseException {
        ChangeRecordDialog dialog = new ChangeRecordDialog(frame, true);
        dialog.setVisible(true);

    }

    private void recordDelete() {
        Record foundedRecord = recordController.findRecord(record);
        if (foundedRecord.getId() != null) {
            recordController.setEntity(foundedRecord);
            try {
                recordController.delete();
            } catch (BaseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private void recordInsertOrUpdate() {
        Record foundedRecord = recordController.findRecord(record);
        if (foundedRecord.getId() != null) {
            foundedRecord.setLabel(record.getLabel());
            recordController.setEntity(foundedRecord);
            try {
                recordController.update();
            } catch (BaseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            recordController.setEntity(record);
            try {
                recordController.create();
            } catch (BaseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private void display() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(totalWidth, totalHeigth);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(new JScrollPane(table));
    }

    private class ChangeRecordDialog extends JDialog {

        private LabelController labelController;
        private UserController userController;
        private List<Label> labelList;
        private List<User> userList;
        private JButton btnCancel;
        private JButton btnSave;
        private JComboBox<Label> cmbLabel;
        private JLabel jLabel1;
        private JLabel jLabel2;
        private JLabel jLabel3;
        private DatePicker dpDate;
        private JComboBox<User> cmbUser;

        public ChangeRecordDialog(java.awt.Frame parent, boolean modal) throws BaseException {
            super(parent, modal);
            initComponents();
            labelController = new LabelController();
            userController = new UserController();
            labelList = labelController.read();
            userList = userController.read();
            settings();
            load();
        }

        private void settings() {
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

        private void initComponents() {

            jLabel1 = new JLabel();
            cmbLabel = new JComboBox<>();
            jLabel2 = new JLabel();
            dpDate = new DatePicker();
            jLabel3 = new JLabel();
            cmbUser = new JComboBox<>();
            btnSave = new JButton();
            btnCancel = new JButton();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            jLabel1.setText("Label");
            jLabel2.setText("Date");
            jLabel3.setText("User");
            btnSave.setText("Save");
            btnSave.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnSaveActionPerformed(evt);
                }
            });

            btnCancel.setText("Cancel");
            btnCancel.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnCancelActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1)
                                            .addComponent(cmbLabel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(dpDate, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbUser)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(28, 28, 28)
                                                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)))
                                    .addContainerGap(34, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(34, 34, 34)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnSave)
                                            .addComponent(btnCancel))
                                    .addContainerGap(60, Short.MAX_VALUE))
            );

            pack();
        }

        private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {

        }

        private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {

        }

        private void loadLabels() {
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

    }

}
