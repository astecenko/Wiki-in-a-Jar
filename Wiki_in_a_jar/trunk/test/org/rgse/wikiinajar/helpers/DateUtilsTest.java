package org.rgse.wikiinajar.helpers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import org.rgse.wikiinajar.helpers.DateUtils;

public class DateUtilsTest extends TestCase {

	public void testParseDate() throws ParseException, IOException {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date expectedDate = df.parse("2007-03-10");

		assertDate(expectedDate, "2007-03-10");
		assertDate(expectedDate, "20070310");
		assertNull(DateUtils.parseDate("not a date"));
	}

	public void testIsSameDay() {

		assertSameDay(true, "2007-03-03", "2007-03-03", false);
		assertSameDay(true, "2007-03-03", "2007-03-03", true);
		assertSameDay(true, "2000-03-03", "2007-03-03", true);
		assertSameDay(false, "2000-03-03", "2007-03-03", false);
	}

	public void testIsBeforeDay() {
		assertBeforeDay(true, "2000-03-01", "2007-03-02", false);
		assertBeforeDay(false, "2007-03-02", "2007-03-02", false);
		assertBeforeDay(false, "2007-03-03", "2007-03-02", false);
		
		assertBeforeDay(true, "2007-03-01", "2000-03-02", true);
		assertBeforeDay(false, "2007-03-02", "2000-03-02", true);
		assertBeforeDay(false, "2007-03-03", "2000-03-02", true);
	}

	private void assertBeforeDay(boolean expected, String date1, String date2,
			boolean ignoreYear) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(DateUtils.parseDate(date1));
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(DateUtils.parseDate(date2));

		assertEquals(expected, DateUtils.isBeforeDay(cal1, cal2, ignoreYear));
	}

	private void assertSameDay(boolean expected, String date1, String date2,
			boolean ignoreYear) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(DateUtils.parseDate(date1));
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(DateUtils.parseDate(date2));

		assertEquals(expected, DateUtils.isSameDay(cal1, cal2, ignoreYear));
	}

	private void assertDate(Date expectedDate, String string) {
		Date actual = DateUtils.parseDate(string);
		assertNotNull(actual);
		assertEquals(expectedDate, actual);
	}

}
