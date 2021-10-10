/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mikec.shedule.controller.LabelController;
import mikec.shedule.controller.NumOfWorkersForDayController;
import mikec.shedule.controller.RecordController;
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
    private DefaultComboBoxModel<Label> defaultCbModel;
    private DefaultTableModel defaultTableModel;
    private List<User> usersInMonth;
    private List<Record> recordsForMonth;
    private JComboBox<Label> cmbLabel;
    private List<Label> labels;
    private RecordController recordController;
    private LabelController labelController;
    private NumOfWorkersForDayController nwfdController;
    private Integer yearInt, monthInt, numOfDaysInMoth;
    private String strYear, strMonth;
    private JTable table;
    private JFrame frame;
    private Record record;

    public SheduleAdminDisplayTableScreen(String year, String month) throws BaseException {
        table = new JTable();
        frame = new JFrame();
        recordController = new RecordController();
        labelController = new LabelController();
        nwfdController = new NumOfWorkersForDayController();
        yearInt = Integer.parseInt(year);
        monthInt = Integer.parseInt(month);
        strYear = year;
        strMonth = month;
        frameSettings();
        labels = loadLabels();
        numOfDaysInMoth = calculateNumOfDaysInMonth(yearInt, monthInt);
        recordsForMonth = recordController.getRecordsForMonth(year, month);
        loadUsersInMonth();
        setProperties();
        generateJComboBox();
        generateDefaultCBModel();
        setLabelModel();
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

    private int getIndexOfLabel(Label label) {
        int index = 0;
        DefaultComboBoxModel<Label> um = (DefaultComboBoxModel<Label>) cmbLabel.getModel();
        for (int i = 0; i < um.getSize(); i++) {
            if (um.getElementAt(i).getAbbreviation() == (label.getAbbreviation())) {
                index = i;
                break;
            }
        }
        return index;
    }

    private Integer calculateNumOfDaysInMonth(Integer yearInt, Integer monthInt) {
        YearMonth obj;
        obj = YearMonth.of(yearInt, monthInt);
        return obj.lengthOfMonth();
    }

    private void generateJComboBox() {
        cmbLabel = new JComboBox<>();
        cmbLabel.addItemListener(new ItemChangeListener());
    }

    private List<Label> loadLabels() {
        List<Label> startList = new ArrayList<>();
        startList.add(new Label("", "-"));
        labelController.read().forEach(l -> {
            startList.add(l);
        });
        return startList;
    }

    private void frameSettings() {
        frame.setTitle(Application.getTitle("Shedule for " + strMonth + "/" + strYear));
    }

    private class ItemChangeListener implements ItemListener {

        private int rowInListener;
        private int columnInListener;

        public ItemChangeListener() {
            rowInListener = -1;
            columnInListener = -1;
        }

        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED
                    && (rowInListener == -1 || columnInListener == -1
                    || rowInListener != table.getSelectedRow()
                    || columnInListener != table.getSelectedColumn())) {
                rowInListener = table.getSelectedRow();
                columnInListener = table.getSelectedColumn();
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    Label selectedLabel = (Label) event.getItem();
                    User selectedUser = usersInMonth.get(table.getSelectedRow());
                    try {
                        Date selectedDate = new Date();
                        selectedDate = Tools.parseDate(table.getSelectedColumn() + "." + strMonth + "." + strYear + ".");
                        record = new Record(selectedUser, selectedLabel, selectedDate);
                        if (record.getLabel().getId() == null) {
                            recordDelete();
                        } else {
                            recordInsertOrUpdate();
                        }

                        table.changeSelection(0, 0, true, false);
                        columnInListener = 0;
                        rowInListener = 0;
                        table.requestFocus();
                        populateLastRow();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        }
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

    private void generateDefaultCBModel() {
        defaultCbModel = new DefaultComboBoxModel<>();
        labels.forEach(s -> {
            defaultCbModel.addElement(s);
        });
    }

    private void setLabelModel() {
        cmbLabel.setModel(defaultCbModel);
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

                return column>0 && (row<rows-2 || row==0);
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
        defineCellEditors();
        addUserRows();       
        addLastTwoRows();
        populateLastRow();
    }
    
    
    private void defineCellEditors() {
       for (int i = 1; i <= numOfDaysInMoth; i++) {
            table.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(cmbLabel));
        }
    }
    
    private void addUserRows() throws BaseException {
      for (User u : usersInMonth) {
            Object[] array = new Object[numOfDaysInMoth + 1];
            array[0] = u.getPerson();
            for (int i = 1; i <= numOfDaysInMoth; i++) {
                int index = 0;
                Date iterDate = Tools.parseDate(i + "." + strMonth + "." + strYear + ".");
                for (Record rc : recordsForMonth) {
                    String iterDateStr = Tools.formatDate(iterDate);
                    String curDateStr = Tools.formatDate(rc.getDate());
                    if (rc.getUser().getPrs_id().equals(u.getPrs_id())
                            && iterDateStr.equals(curDateStr)) {
                        index = getIndexOfLabel(rc.getLabel());
                        break;
                    }
                }
                array[i] = labels.get(index);
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
            String result = "N/N", nwfd = "", enrolledWorkers = "";
            Date date = Tools.parseDate(i + "." + strMonth + "." + strYear + ".");
            
            for(NumOfWorkersForDay numwfd : nwfdController.fetchAll()){
                if(Tools.isDateBetween(numwfd.getStarts(), numwfd.getExpires(), date) 
                        && Tools.isDayInWeek(
                                String.valueOf(numwfd.getNumOfWorkersForDayItem().getName()), 
                                                date)){
                    nwfd = String.valueOf(numwfd.getValue());  
                    break;
                }
            }
            enrolledWorkers = String.valueOf(recordController.getNumberOfWorkersForDate(date));
            
            if(nwfd!=""){
                int nwfdInt = Integer.parseInt(nwfd);
                int enrolledWorkersInt = Integer.parseInt(enrolledWorkers);
                int resultInt = enrolledWorkersInt-nwfdInt;
                if(resultInt==0){
                    result = "OK";
                }else{
                    result = formatSign(resultInt);
                }
                
            }
            
            defaultTableModel.setValueAt(result, table.getRowCount()-1, i);
        }
    }
    
    private String formatSign(int number) {
        return (number > 0 ? "+" : "" ) + number;
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
    }

    private void display() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(totalWidth, totalHeigth);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(new JScrollPane(table));
    }

}
