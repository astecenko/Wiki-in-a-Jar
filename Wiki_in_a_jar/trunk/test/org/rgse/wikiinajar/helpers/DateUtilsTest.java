/*
 * Wiki_in_a_jar
 * 
 * Copyright (C) 2007 rico_g AT users DOT sourceforge DOT net
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License s published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 * 
 */

package org.rgse.wikiinajar.helpers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import org.rgse.wikiinajar.helpers.DateUtils;

/**
 * 
 * @author rico_g AT users DOT sourceforge DOT net
 *
 */
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
