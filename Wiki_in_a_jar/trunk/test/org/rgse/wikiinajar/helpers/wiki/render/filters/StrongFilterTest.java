/*
 * Wiki_in_a_jar
 * Copyright (C) 2007 rico_g AT users DOT sourceforge DOT net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * s published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *  
 */
package org.rgse.wikiinajar.helpers.wiki.render.filters;

import junit.framework.TestCase;

/**
 * @author rico_g AT users DOT sourceforge DOT net
 * 
 */
public class StrongFilterTest extends TestCase {

	/**
	 * Test method for
	 * {@link org.rgse.wikiinajar.helpers.wiki.render.filters.StrongFilter#filter(java.lang.String)}.
	 */
	public void teststrongFilter() {
		StrongFilter fixture = new StrongFilter();
		assertEquals("<i>strong</i>", fixture.filter("''strong''"));
		assertEquals("<i>strong's word</i>", fixture
				.filter("''strong's word''"));
		assertEquals("<i>strong</i> weak <i>strong again</i>", fixture
				.filter("''strong'' weak ''strong again''"));
	}
	public void testStrongerFilter() {
		StrongerFilter fixture = new StrongerFilter();
		assertEquals("<b>strong</b>", fixture.filter("'''strong'''"));
		assertEquals("<b>strong's word</b>", fixture
				.filter("'''strong's word'''"));
		assertEquals("<b>strong</b> weak <b>strong again</b>", fixture
				.filter("'''strong''' weak '''strong again'''"));
	}

	public void testStrongestFilter() {
		StrongestFilter fixture = new StrongestFilter();
		assertEquals("<i><b>strong</b></i>", fixture.filter("''''strong''''"));
		assertEquals("<i><b>strong's word</b></i>", fixture
				.filter("''''strong's word''''"));
		assertEquals("<i><b>strong</b></i> weak <i><b>strong again</b></i>", fixture
				.filter("''''strong'''' weak ''''strong again''''"));
	}

}
