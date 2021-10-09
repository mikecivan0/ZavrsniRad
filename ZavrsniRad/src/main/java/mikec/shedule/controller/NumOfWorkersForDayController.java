/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.controller;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import mikec.shedule.model.NumOfWorkersForDay;
import mikec.shedule.util.BaseException;
import mikec.shedule.util.Tools;

public class NumOfWorkersForDayController extends BaseController<NumOfWorkersForDay> {

    public NumOfWorkersForDayController() throws BaseException {
        super();
    }

    @Override
    public List<NumOfWorkersForDay> read() {
        return session.createQuery("FROM numsOfWorkersForDay GROUP BY starts ORDER BY starts").list();
    }

    public List<NumOfWorkersForDay> fetchAll() {
        return session.createQuery("FROM numsOfWorkersForDay").list();
    }

    public List<NumOfWorkersForDay> fetchByStartsDate(Date startsDate) {
        return session.createQuery("FROM numsOfWorkersForDay WHERE starts=:starts")
                .setParameter("starts", startsDate)
                .list();
    }

    @Override
    protected void createControll() throws BaseException {
        createExistsControll();
    }

    @Override
    protected void updateControll() throws BaseException {

    }

    @Override
    protected void deleteControll() throws BaseException {

    }

    public void delete(Date date) {
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM numsOfWorkersForDay WHERE starts=:starts")
                    .setParameter("starts", date)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void createExistsControll() throws BaseException {
        List<Date> datesBetween = Tools.getDatesBetweenTwoDates(entity.getStarts(), entity.getExpires());
        for (Date date : datesBetween) {
            checkInsertOverlap(date);
        }
    }

    private void checkInsertOverlap(Date date) throws BaseException {
        Long startRecordExists = (Long) session.createQuery(
                "SELECT COUNT(id) FROM numsOfWorkersForDay WHERE "
                + ":date BETWEEN starts AND expires "
                + "AND numOfWorkersForDayItemId=:nwfd")
                .setParameter("date", date)
                .setParameter("nwfd", entity.getNumOfWorkersForDayItem())
                .uniqueResult();
        if (startRecordExists > 0) {
            throw new BaseException("Dates range overlap existing records");
        }
    }

    public void checkUpdateOverlap(Date starts, Date expires, Date selected) throws BaseException {
        List<Date> listOfDates = Tools.getDatesBetweenTwoDates(starts, expires);
        for (Date date : listOfDates) {
            Long startRecordExists = (Long) session.createQuery(
                    "SELECT COUNT(id) FROM numsOfWorkersForDay WHERE "
                    + ":date BETWEEN starts AND expires "
                    + "AND id NOT IN "
                    + "(SELECT id FROM numsOfWorkersForDay WHERE starts=:starts)")
                    .setParameter("date", date)
                    .setParameter("starts", selected)
                    .uniqueResult();
            if (startRecordExists > 0) {
                throw new BaseException("Dates range overlap existing records");
            }
        }
    }


}
