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

package org.rgse.wikiinajar;

import org.rgse.wikiinajar.helpers.DateUtilsTest;
import org.rgse.wikiinajar.helpers.TextUtilsTest;
import org.rgse.wikiinajar.helpers.wiki.render.WikiLinkTest;
import org.rgse.wikiinajar.models.EventTest;
import org.rgse.wikiinajar.models.MonthTest;
import org.rgse.wikiinajar.models.TagTreeTest;
import org.rgse.wikiinajar.models.WikiArticleTest;

import junit.framework.TestSuite;

/**
 * 
 * @author rico_g AT users DOT sourceforge DOT net
 *
 */
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
