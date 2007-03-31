package org.rgse.wikiinajar.models;

import org.rgse.wikiinajar.helpers.DateUtils;
import org.rgse.wikiinajar.models.Month.Event;

import junit.framework.TestCase;

public class EventTest extends TestCase {

	public void testIsInPast() {
		Event fixuture = new Event("id", DateUtils.parseDate("2000-01-01"), false );
		
		assertTrue(fixuture.isBeforeToday());
	}

}
