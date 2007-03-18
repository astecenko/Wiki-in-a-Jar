/*
 * XRays Web Framework
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

package net.sf.wikiinajar.xrays;

import junit.framework.TestCase;

/**
 * @author rico_g AT users DOT sourceforge DOT net
 *
 */
public class ControllerResponseTest extends TestCase {

	/**
	 * Test method for {@link net.sf.wikiinajar.xrays.ControllerResponse#getMimeType(java.lang.String)}.
	 */
	public void testGetMimeType() {
		assertEquals("text/css", ControllerResponse.getMimeType("/a/file/style.css"));
	}

}
