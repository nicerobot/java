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

package org.nicerobot.servlet;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author nicerobot
 * 
 */
public class HostnameDispatchTest {

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
	 * Test method for
	 * {@link org.nicerobot.servlet.HostnameDispatch#findServletName(java.lang.String)}.
	 */
	@Test
	public void testFindServlet () {
		System.out.format("%s\n", HostnameDispatch.findServletNames("127.0.0.1"));
		System.out.format("%s\n", HostnameDispatch.findServletNames("localhost"));
		System.out.format("%s\n", HostnameDispatch.findServletNames("some.local"));
		System.out.format("%s\n", HostnameDispatch.findServletNames("nicerobot.appspot.com"));
		System.out.format("%s\n", HostnameDispatch.findServletNames("name1.name2.name3.nicerobot.org"));
		System.out.format("%s\n", HostnameDispatch.findServletNames("name1.name2.nicerobot.org"));
		System.out.format("%s\n", HostnameDispatch.findServletNames("name1.nicerobot.org"));
		System.out.format("%s\n", HostnameDispatch.findServletNames("sub3.sub2.sub1.nicerobot.org"));
		System.out.format("%s\n", HostnameDispatch.findServletNames("sub2.sub1.nicerobot.org"));
		System.out.format("%s\n", HostnameDispatch.findServletNames("sub1.nicerobot.org"));
	}

	/**
	 * Test method for
	 * {@link org.nicerobot.servlet.HostnameDispatch#findServletName(java.lang.String)}.
	 */
	@Test
	public void testFindServletNames () {
		Assert.assertNull(HostnameDispatch.findServlet("127.0.0.1"));
		Assert.assertNull(HostnameDispatch.findServlet("localhost"));
		Assert.assertNull(HostnameDispatch.findServlet("some.local"));
		Assert.assertEquals("com.appspot.nicerobot.servlet.Main",
												HostnameDispatch.findServlet("nicerobot.appspot.com"));
		Assert.assertEquals("org.nicerobot.name3.name2.name1.servlet.Main",
												HostnameDispatch.findServlet("name1.name2.name3.nicerobot.org"));
		Assert.assertEquals("org.nicerobot.servlet.Main",
												HostnameDispatch.findServlet("name1.name2.nicerobot.org"));
		Assert.assertEquals("org.nicerobot.servlet.Main",
												HostnameDispatch.findServlet("name1.nicerobot.org"));
		Assert.assertEquals("org.nicerobot.name3.servlet.Main",
												HostnameDispatch.findServlet("name1.name3.nicerobot.org"));
		Assert.assertEquals("org.nicerobot.sub1.servlet.Main",
												HostnameDispatch.findServlet("sub3.sub2.sub1.nicerobot.org"));
		Assert.assertEquals("org.nicerobot.sub1.servlet.Main",
												HostnameDispatch.findServlet("sub2.sub1.nicerobot.org"));
		Assert.assertEquals("org.nicerobot.sub1.servlet.Main",
												HostnameDispatch.findServlet("sub1.nicerobot.org"));
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
