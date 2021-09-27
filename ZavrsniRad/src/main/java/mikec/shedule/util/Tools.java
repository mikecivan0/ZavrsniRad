package mikec.shedule.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;

public class Tools {

	public static Scanner scanner;
	public static final String DATE_FORMAT = "d.M.yyyy.";
	public static final String TIME_FORMAT = "HH:mm";
	public static final String YEAR_FORMAT = "yyyy";
	public static final String MONTH_FORMAT = "M";
	public static final String DAY_FORMAT = "d";
	public static final String DAY_IN_WEEK_FORMAT = "u";
	private static SimpleDateFormat dateFormat;
	private static SimpleDateFormat timeFormat;
	private static SimpleDateFormat yearFormat;
	private static SimpleDateFormat monthFormat;
	private static SimpleDateFormat dayFormat;
	private static SimpleDateFormat dayInWeekFormat;
        private static Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);        

	public static Date parseDate(String input) throws BaseException{
            dateFormat = new SimpleDateFormat(DATE_FORMAT);                
            try {
                return dateFormat.parse(input);
            } catch (Exception e) {
                throw new BaseException("Enter date in format " + dateFormat.format(new Date()));             
            }
        }

	public static Date parseTime(String input) {
            timeFormat = new SimpleDateFormat(TIME_FORMAT);
            while (true) {
		try {
                    return timeFormat.parse(input);
		} catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Enter time in format " + timeFormat.format(new Date()));
		}
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

	public static String formatTime(Date datum) {
		dateFormat = new SimpleDateFormat(TIME_FORMAT);
		return dateFormat.format(datum);
	}
	
	public static String formatDayInWeek(Date datum) {
		dayInWeekFormat = new SimpleDateFormat(DAY_IN_WEEK_FORMAT);
		return dayInWeekFormat.format(datum);
	}

	public static String parseBool(boolean bool) {
		return (bool) ? "yes" : "no";
	}
        
        public static String hashPass(String pass){          
            return argon2.hash(4, 1024*10, 8, pass.getBytes());
        }
        
        public static boolean verifyPass(String hash, String pass){
            return argon2.verify(hash, pass.getBytes());
        }
        
        public static void openLink(String link){
            try {
                Desktop desktop = java.awt.Desktop.getDesktop();
                desktop.browse(new URI(link));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error is occured. Try open link later.");
            }
        }
public static List<Date> getDatesBetweenTwoDates(Date startdate, Date enddate)
{
    List<Date> dates = new ArrayList<Date>();
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(startdate);
 
    while (calendar.getTime().before(enddate) || calendar.getTime().equals(enddate))
    {
        Date result = calendar.getTime();
        dates.add(result);
        calendar.add(Calendar.DATE, 1);
    }
    return dates;
}
  
}
