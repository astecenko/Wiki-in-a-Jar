package org.rgse.wikiinajar;

import org.rgse.wikiinajar.helpers.DateUtilsTest;
import org.rgse.wikiinajar.helpers.TextUtilsTest;
import org.rgse.wikiinajar.helpers.wiki.render.WikiLinkTest;
import org.rgse.wikiinajar.models.EventTest;
import org.rgse.wikiinajar.models.MonthTest;
import org.rgse.wikiinajar.models.TagTreeTest;
import org.rgse.wikiinajar.models.WikiArticleTest;

import junit.framework.TestSuite;

public class AllTests extends TestSuite{
	
	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		
		suite.addTestSuite(WikiLinkTest.class);
		suite.addTestSuite(DateUtilsTest.class);
		suite.addTestSuite(TextUtilsTest.class);
		suite.addTestSuite(EventTest.class);
		suite.addTestSuite(MonthTest.class);
		suite.addTestSuite(TagTreeTest.class);
		suite.addTestSuite(WikiArticleTest.class);
		return suite;
	}

}
