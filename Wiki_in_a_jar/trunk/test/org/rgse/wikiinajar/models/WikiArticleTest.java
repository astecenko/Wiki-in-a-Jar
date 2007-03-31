package org.rgse.wikiinajar.models;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.rgse.wikiinajar.models.WikiArticle;

import junit.framework.TestCase;

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
