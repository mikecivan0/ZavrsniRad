package mikec.shedule.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

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

        

	public static Date takeDate(String message) {

		dateFormat = new SimpleDateFormat(DATE_FORMAT);

		while (true) {
			System.out.print(message);
			try {
				return dateFormat.parse(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Enter date in format " + dateFormat.format(new Date()));
			}
		}

	}

	public static Date takeTime(String message) {

		timeFormat = new SimpleDateFormat(TIME_FORMAT);

		while (true) {
			System.out.print(message);
			try {
				return timeFormat.parse(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Enter time in format " + timeFormat.format(new Date()));
			}
		}

	}

	public static int takeNumber(String message, String errorMessage, int min, int max) {

		int broj = 0;

		while (true) {
			System.out.print(message);
			try {
				broj = Integer.parseInt(scanner.nextLine());

				if (broj < min || broj > max) {
					System.out.println("Enter number between " + min + " and " + max);
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println(errorMessage);
			}
		}

		return broj;
	}

	public static String takeString(String message, String errorMessage, int minLength, int maxLength) {

		String unos = "";

		while (true) {
			System.out.print(message);

			unos = scanner.nextLine();
			if (unos.trim().equals("")) {
				System.out.println(errorMessage);
			}
			if (unos.length() < minLength || unos.length() > maxLength) {
				System.out.println("Input length must bee between " 
                                        + minLength + " and " + maxLength + " characters");
				continue;
			}
			break;
		}

		return unos;
	}

	public static boolean yesNo(String message, String errorMessage) {
		String unos;

		while (true) {
			System.out.print(message);

			unos = scanner.nextLine().trim().toLowerCase();
			if (unos.equals("yes")) {
				return true;
			}
			if (unos.equals("no")) {
				return false;
			}
			System.out.println(errorMessage);
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

}
