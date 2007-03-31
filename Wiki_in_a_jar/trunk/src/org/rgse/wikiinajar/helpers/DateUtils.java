package org.rgse.wikiinajar.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final String[] WEEKDAYS = { "", "Sunday", "Monday",
			"Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

	public static final String[] MONTHS = { "January", "February", "March",
			"April", "May", "June", "July", "August", "September", "October",
			"November", "December" };

	private static final String[] DATE_FORMATS = { "yyyy-MM-dd", "yyyyMMdd" };

	/**
	 * Attempts to parse a date in various formats.
	 * 
	 * @param value
	 * @return the date or <code>null</code> if it could not be parsed.
	 */
	public static Date parseDate(String value) {
		for (int i = 0; i < DATE_FORMATS.length; i++) {
			SimpleDateFormat df = new SimpleDateFormat(DATE_FORMATS[i]);
			try {
				return df.parse(value);
			} catch (ParseException e) {
				// try next one
			}
		}
		return null;
	}

	/**
	 * Returns the current month as defined in {@link Calendar}.
	 * 
	 * @return
	 */
	public static int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH);
	}

	/**
	 * Returns <code>true</code> if the two specified dates are on the same
	 * day of the same month in the same year.
	 * 
	 * @param date1
	 * @param date2
	 * @param ignoreYear
	 *            If <code>true</code>, ignores year in comparison.
	 * @return
	 */
	public static boolean isSameDay(Calendar date1, Calendar date2,
			boolean ignoreYear) {
		return date1.get(Calendar.DAY_OF_MONTH) == date2
				.get(Calendar.DAY_OF_MONTH)
				&& date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH)
				&& (ignoreYear || date1.get(Calendar.YEAR) == date2
						.get(Calendar.YEAR));
	}

	/**
	 * Returns <code>true</code> if the first date is before the second
	 * comparing day, month, and year.
	 * 
	 * @param date1
	 * @param date2
	 * @param ignoreYear
	 * @return
	 */
	public static boolean isBeforeDay(Calendar date1, Calendar date2,
			boolean ignoreYear) {
		int year1 = date1.get(Calendar.YEAR);
		int year2 = date2.get(Calendar.YEAR);
		int month1 = date1.get(Calendar.MONTH);
		int month2 = date2.get(Calendar.MONTH);
		int day1 = date1.get(Calendar.DAY_OF_MONTH);
		int day2 = date2.get(Calendar.DAY_OF_MONTH);

		if (!ignoreYear && year1 < year2) {
			return true;
		}
		if (!ignoreYear && year1 > year2) {
			return false;
		}
		// same year:
		if (month1 < month2) {
			return true;
		}
		if (month1 > month2) {
			return false;
		}
		// same month
		return day1 < day2;
	}
}
