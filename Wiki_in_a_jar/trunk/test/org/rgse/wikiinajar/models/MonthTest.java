package org.rgse.wikiinajar.models;

import java.util.Calendar;

import junit.framework.TestCase;

public class MonthTest extends TestCase {
	
	
	public void testMonth() {
		Month fixture = new Month(Calendar.MARCH, 2007);
		assertEquals(31, fixture.getDays().size());
	}

}
