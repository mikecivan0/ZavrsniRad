/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.view;

import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mikec.shedule.controller.NumOfWorkersForDayController;
import mikec.shedule.controller.RecordController;
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
    private int selectedRow, selectedColumn;

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
        frame.setIconImage(Application.getIcon());
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
                rec.setId(null);
                for (Record rc : recordsForMonth) {
                    String iterDateStr = Tools.formatDate(iterDate);
                    String curDateStr = Tools.formatDate(rc.getDate());
                    if (rc.getUser().getPrs_id().equals(u.getPrs_id())
                            && iterDateStr.equals(curDateStr)) {
                        rec.setLabel(rc.getLabel());
                        rec.setId(rc.getId());
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

        array[0] = "Too many / deficit of workers";
        for (int i = 1; i <= numOfDaysInMoth; i++) {
            array[i] = "";
        }
        defaultTableModel.addRow(array);

    }

    private void populateLastRow() throws BaseException {
        for (int i = 1; i <= numOfDaysInMoth; i++) {
            updateNumberOfWorkersForCell(i);
        }
    }
    
    private void updateNumberOfWorkersForCell(int column) throws BaseException {
            String result = "N/A", nwfd = "", enrolledWorkers = "";
            Date date = Tools.parseDate(column + "." + strMonth + "." + strYear + ".");

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

            table.setValueAt(result, table.getRowCount() - 1, column);
     
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
                    selectedRow = table.rowAtPoint(e.getPoint());
                    selectedColumn = table.columnAtPoint(e.getPoint());
                    try {
                        record = (Record) table.getValueAt(selectedRow, selectedColumn);
                        displayDialog();
                    } catch (Exception ex) {
                        
                    }
                }
            }
        });
    }

    private void displayDialog() throws BaseException {
        RecordEditDialogScreen dialog = new RecordEditDialogScreen(frame, true, record);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(frame);
        dialog.addWindowListener(new WindowAdapter() 
            {
              public void windowClosed(WindowEvent e)
              {
                updateValuesAfterChange();                 
              }           
            });
        dialog.setVisible(true);
    }
    
    private void updateValuesAfterChange() {
        Record newRecord = recordController.findRecord(record);
        table.setValueAt(newRecord, selectedRow, selectedColumn);
        try {
            updateNumberOfWorkersForCell(selectedColumn);
        } catch (BaseException ex) {
            
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

}
