package mikec.shedule.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.awt.Desktop;
import java.net.URI;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;

public class Tools {

    public static Scanner scanner;
    public static final String DATE_FORMAT = "d.M.yyyy.";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String YEAR_FORMAT = "yyyy";
    public static final String MONTH_FORMAT = "M";
    public static final String DAY_FORMAT = "d";
    public static final String DAY_IN_WEEK_FORMAT = "u";
    public static final String DAY_NAME_IN_WEEK_FORMAT = "EEE";
    private static SimpleDateFormat dateFormat;
    private static SimpleDateFormat timeFormat;
    private static SimpleDateFormat yearFormat;
    private static SimpleDateFormat monthFormat;
    private static SimpleDateFormat dayFormat;
    private static SimpleDateFormat dayInWeekFormat;
    private static Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);

    public static Date parseDate(String input) throws BaseException {
        dateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            return dateFormat.parse(input);
        } catch (Exception e) {
            throw new BaseException("Enter date in format " + dateFormat.format(new Date()));
        }
    }

    public static Time parseTime(String input) throws BaseException {
        timeFormat = new SimpleDateFormat(TIME_FORMAT);
        try {
            long ms = timeFormat.parse(input).getTime();
            return new Time(ms);
        } catch (Exception e) {
            throw new BaseException("Enter time in format " + timeFormat.format(new Date()));
        }
    }

    public static int parseNumber(String input, String errorMessage, int min, int max) {
        int number = Integer.parseInt(input);
        while (true) {
            try {
                if (number < min || number > max) {
                    JOptionPane.showMessageDialog(null, "Enter number between " + min + " and " + max);
                    continue;
                }
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, errorMessage);
            }
        }
        return number;
    }

    public static String parseString(String input, String errorMessage, int minLength, int maxLength) {
        while (true) {
            if (input.trim().equals("")) {
                JOptionPane.showMessageDialog(null, errorMessage);
            }
            if (input.length() < minLength || input.length() > maxLength) {
                JOptionPane.showMessageDialog(null, "Input length must bee between "
                        + minLength + " and " + maxLength + " characters"
                );
                continue;
            }
            break;
        }
        return input;
    }

    public static boolean yesNo(String input, String errorMessage) {
        while (true) {
            if (input.equals("yes")) {
                return true;
            }
            if (input.equals("no")) {
                return false;
            }
            JOptionPane.showMessageDialog(null, errorMessage);
        }
    }

    public static String formatYear(Date datum) {
        yearFormat = new SimpleDateFormat(YEAR_FORMAT);
        return yearFormat.format(datum);
    }

    public static String formatMonth(Date datum) {
        monthFormat = new SimpleDateFormat(MONTH_FORMAT);
        return monthFormat.format(datum);
    }

    public static String formatDay(Date datum) {
        dayFormat = new SimpleDateFormat(DAY_FORMAT);
        return dayFormat.format(datum);
    }

    public static String formatDate(Date datum) {
        dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(datum);
    }

    public static String formatTime(Time time) {
        timeFormat = new SimpleDateFormat(TIME_FORMAT);
        return timeFormat.format(time);
    }

    public static String formatDayInWeek(Date datum) {
        dayInWeekFormat = new SimpleDateFormat(DAY_IN_WEEK_FORMAT);
        return dayInWeekFormat.format(datum);
    }

    public static String formatDayNameInWeek(Date datum) {
        dayInWeekFormat = new SimpleDateFormat(DAY_NAME_IN_WEEK_FORMAT);
        return dayInWeekFormat.format(datum);
    }

    public static String parseBool(boolean bool) {
        return (bool) ? "yes" : "no";
    }

    public static String hashPass(String pass) {
        return argon2.hash(4, 1024 * 10, 8, pass.getBytes());
    }

    public static boolean verifyPass(String hash, String pass) {
        return argon2.verify(hash, pass.getBytes());
    }

    public static void openLink(String link) {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            desktop.browse(new URI(link));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error is occured. Try open link later.");
        }
    }

    public static List<Date> getDatesBetweenTwoDates(Date startdate, Date enddate) {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate) || calendar.getTime().equals(enddate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    public static boolean isDateBetween(
            Date startDate,
            Date endDate,
            Date targetDate) {
        return startDate.compareTo(targetDate) * targetDate.compareTo(endDate) >= 0;

    }

    public static Date LocalDateToDate(LocalDate localDate) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    }

    public static LocalDate dateToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalTime timeToLocalTime(Time time) {
        return time.toLocalTime();
    }

    public static Time localTimeToTime(LocalTime localTime) {
        return Time.valueOf(localTime);
    }

    public static boolean isDayInWeek(String str, Date date) {
        String rwhItemShort = str.substring(0, 3);
        String dayInWeekNameShort = formatDayNameInWeek(date);
        return rwhItemShort.equalsIgnoreCase(
                convertNameOfDayInWeek(dayInWeekNameShort)
        );
    }

    private static String convertNameOfDayInWeek(String croName) {
        String engName = croName;
        switch (croName) {
            case "pon" ->
                engName = "mon";
            case "uto" ->
                engName = "tue";
            case "sri" ->
                engName = "wed";
            case "čet" ->
                engName = "thu";
            case "pet" ->
                engName = "fri";
            case "sub" ->
                engName = "sat";
            case "ned" ->
                engName = "sun";
        }
        return engName;
    }

}
