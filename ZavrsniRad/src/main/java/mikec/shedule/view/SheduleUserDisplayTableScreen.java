/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.view;

import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mikec.shedule.controller.ExceptionalWorkingHoursController;
import mikec.shedule.controller.LabelController;
import mikec.shedule.controller.RecordController;
import mikec.shedule.controller.RegularWorkingHoursController;
import mikec.shedule.model.ExceptionalWorkingHours;
import mikec.shedule.model.User;
import mikec.shedule.model.Record;
import mikec.shedule.model.RegularWorkingHours;
import mikec.shedule.util.Application;
import mikec.shedule.util.BaseException;
import mikec.shedule.util.Tools;

public class SheduleUserDisplayTableScreen {

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
    private LabelController labelController;
    private Integer yearInt, monthInt, numOfDaysInMoth;
    private String strYear, strMonth;
    private JTable table;
    private JFrame frame;
    private Record record;

    public SheduleUserDisplayTableScreen(String year, String month) throws BaseException {
        table = new JTable();
        frame = new JFrame();
        frame.setIconImage(Application.getIcon());
        recordController = new RecordController();
        labelController = new LabelController();
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
        rows = usersInMonth.size() + 1;
        cols = numOfDaysInMoth;
        nameColWidth = 250;
        dataColsWidth = 25;
        rowsHeigth = 30;
        totalWidth = cols * dataColsWidth + nameColWidth;
        totalHeigth = rows * rowsHeigth + 50;
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
        table.setModel(defaultTableModel);

        for (User u : usersInMonth) {
            Object[] array = new Object[numOfDaysInMoth + 1];
            array[0] = u.getPerson();
            for (int i = 1; i <= numOfDaysInMoth; i++) {
                Date iterDate = Tools.parseDate(i + "." + strMonth + "." + strYear + ".");
                for (Record rc : recordsForMonth) {
                    String iterDateStr = Tools.formatDate(iterDate);
                    String curDateStr = Tools.formatDate(rc.getDate());
                    if (rc.getUser().getPrs_id().equals(u.getPrs_id())
                            && iterDateStr.equals(curDateStr)) {
                        array[i] = rc.getLabel().getAbbreviation();
                        break;
                    }
                }
            }
            defaultTableModel.addRow(array);
        }
    }

    private void setTableProperties() {
        table = new JTable(defaultTableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(nameColWidth);
        table.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(nameColWidth);
        for (int i = 1; i <= cols; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(dataColsWidth);
            table.getTableHeader().getColumnModel().getColumn(i).setPreferredWidth(dataColsWidth);
        }
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int column = target.getSelectedColumn();
                    try {
                        Date date = Tools.parseDate(column + "." + strMonth + "." + strYear + ".");
                        new ShowWorkingHoursForSelectedDateDialog(date).setVisible(true);
                    } catch (BaseException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });
    }

    private class ShowWorkingHoursForSelectedDateDialog extends JDialog {

        private String workStartHours = "No record",
                workEndHours = "No record",
                breakDuration = "No record",
                dateStr = "";

        public ShowWorkingHoursForSelectedDateDialog(Date date) throws BaseException {
            super(frame, true);
            dateStr = Tools.formatDate(date);
            setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(frame);
            setTitle(Application.getTitle("Details"));
            JLabel workingHoursLabel = new JLabel();
            workingHoursLabel.setBounds(10, 10, 390, 110);
            populateWorkingHours(date);
            setLayout(new FlowLayout());
            setSize(400, 120);
            workingHoursLabel.setText(
                    "<html>Date: " + dateStr + "<br>"
                    + "Starts: " + workStartHours + "<br>"
                    + "Ends: " + workEndHours + "<br>"
                    + "Break: " + breakDuration + " minutes</html>");
            add(workingHoursLabel);
        }

        private void populateWorkingHours(Date date) throws BaseException {
            RegularWorkingHoursController rwhController = new RegularWorkingHoursController();
            ExceptionalWorkingHoursController ewhController = new ExceptionalWorkingHoursController();
            for (RegularWorkingHours rwh : rwhController.fetchAll()) {
                if (Tools.isDateBetween(rwh.getStarts(), rwh.getExpires(), date)
                        && Tools.isDayInWeek(rwh.getRegularWorkingHoursItem().getName(), date)) {
                    breakDuration = String.valueOf(rwh.getBreakDurationInMinutes());
                    if (rwh.getRegularWorkingHoursItem().getName().contains("Starts")) {
                        workStartHours = Tools.formatTime(rwh.getValue())
                                .substring(0, workEndHours.length() - 2);
                    }
                    if (rwh.getRegularWorkingHoursItem().getName().contains("Ends")) {
                        workEndHours = Tools.formatTime(rwh.getValue())
                                .substring(0, workEndHours.length() - 2);
                        break;
                    }
                }
            }
            for (ExceptionalWorkingHours ewh : ewhController.read()) {
                if (date.equals(ewh.getDate())) {
                    breakDuration = String.valueOf(ewh.getBreakDuration());
                    workStartHours = Tools.formatTime(ewh.getStartTime())
                            .substring(0, workEndHours.length() - 2);
                    workEndHours = Tools.formatTime(ewh.getEndTime())
                            .substring(0, workEndHours.length() - 2);
                    dateStr += " (" + ewh.getFootnote() + ")";
                    break;
                }
            }
        }
    }

    private void display() {
        table.setRowSelectionInterval(0, table.getRowCount() - 1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(totalWidth, totalHeigth);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(new JScrollPane(table));
    }

}
