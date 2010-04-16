/*
 * Copyright 2010 Nice Robot Corporation
 * http://nicerobot.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 *
 */
/**
 * 
 */

package org.nicerobot.net;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author nicerobot
 * 
 */
public class URLuTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp () throws Exception {}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown () throws Exception {}

	/**
	 * Test method for {@link org.nicerobot.net.URLu#appendName(java.lang.String, java.lang.String[])}
	 * .
	 * 
	 * @throws MalformedURLException
	 */
	@Test
	public void testAppendNameStringStringArray () throws MalformedURLException {
		System.out.format("%s\n", URLu.appendName("http://append.name/", "XX", "YY"));
		System.out.format("%s\n", URLu.appendName("http://append.name/abc", "XX", "YY"));
		System.out.format("%s\n", URLu.appendName("http://append.name/abc.xyz", "XX", "YY"));
		System.out.format("%s\n", URLu.appendName("http://append.name/?123", "XX", "YY"));
		System.out.format("%s\n", URLu.appendName("http://append.name/abc?123", "XX", "YY"));
		System.out.format("%s\n", URLu.appendName("http://append.name/abc.xyz?123", "XX", "YY"));
	}

	/**
	 * Test method for {@link org.nicerobot.net.URLu#appendPath(java.lang.String, java.lang.String[])}
	 * .
	 * 
	 * @throws MalformedURLException
	 */
	@Test
	public void testAppendPathStringStringArray () throws MalformedURLException {
		System.out.format("%s\n", URLu.appendPath("http://append.path/", "XX", "YY"));
		System.out.format("%s\n", URLu.appendPath("http://append.path/abc", "XX", "YY"));
		System.out.format("%s\n", URLu.appendPath("http://append.path/abc.xyz", "XX", "YY"));
		System.out.format("%s\n", URLu.appendPath("http://append.path/?123", "XX", "YY"));
		System.out.format("%s\n", URLu.appendPath("http://append.path/abc?123", "XX", "YY"));
		System.out.format("%s\n", URLu.appendPath("http://append.path/abc.xyz?123", "XX", "YY"));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass () throws Exception {}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass () throws Exception {}

}
