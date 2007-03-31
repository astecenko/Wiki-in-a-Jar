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

package org.rgse.wikiinajar.models;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.rgse.wikiinajar.models.WikiArticle;

import junit.framework.TestCase;

/**
 * 
 * @author rico_g AT users DOT sourceforge DOT net
 *
 */
public class WikiArticleTest extends TestCase {

	public void testExtractDueDate() throws ParseException, IOException {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date expectedDate = df.parse("2007-03-10");

		assertDate("2007-03-10 Some title", expectedDate);
		assertDate("Text 2007-03-10 Some title", expectedDate);
		assertDate("Text 2007-03-10", expectedDate);
		assertNoDate("Some text");
		assertNoDate("2007 03 10");
	}

	private void assertNoDate(String string) throws IOException {
		WikiArticle fixture = new WikiArticle(string);
		assertNull(fixture.extractDueDate(string));
	}

	private void assertDate(String title, Date expectedDate) throws IOException {
		WikiArticle fixture = new WikiArticle(title);
		Date actual = fixture.extractDueDate(title);
		assertNotNull(actual);

		assertEquals(expectedDate, actual);

	}

}
