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

package org.nicerobot.util;

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
public class HexTest {

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
	 * Test method for {@link org.nicerobot.util.Hex#valueOf(byte[])}.
	 */
	@Test
	public void testValueOf0 () {
		final byte[] b = new byte[] {};
		Assert.assertEquals("", Hex.valueOf(b));
	}

	/**
	 * Test method for {@link org.nicerobot.util.Hex#valueOf(byte[])}.
	 */
	@Test
	public void testValueOf00 () {
		final byte[] b = new byte[] { -128, 0x00, 0x00, 127 };
		Assert.assertEquals("8000007F", Hex.valueOf(b));
	}

	/**
	 * Test method for {@link org.nicerobot.util.Hex#valueOf(byte[])}.
	 */
	@Test
	public void testValueOf0xf () {
		final byte[] b = new byte[] { 0x7f };
		Assert.assertEquals("7F", Hex.valueOf(b));
	}

	/**
	 * Test method for {@link org.nicerobot.util.Hex#valueOf(byte[])}.
	 */
	@Test
	public void testValueOf15 () {
		final byte[] b = new byte[] { 15 };
		Assert.assertEquals("0F", Hex.valueOf(b));
	}

	/**
	 * Test method for {@link org.nicerobot.util.Hex#valueOf(byte[])}.
	 */
	@Test
	public void testValueOf16 () {
		final byte[] b = new byte[] { 16 };
		Assert.assertEquals("10", Hex.valueOf(b));
	}

	/**
	 * Test method for {@link org.nicerobot.util.Hex#valueOf(byte[])}.
	 */
	@Test
	public void testValueOf7f () {
		final byte[] b = new byte[] { 0x00, 127, -128, 0x00 };
		Assert.assertEquals("007F8000", Hex.valueOf(b));
	}

	/**
	 * Test method for {@link org.nicerobot.util.Hex#valueOf(byte[])}.
	 */
	@Test
	public void testValueOfMinus1 () {
		final byte[] b = new byte[] { 0, -1, 1, -1, 1, 0 };
		Assert.assertEquals("00FF01FF0100", Hex.valueOf(b));
	}

	/**
	 * Test method for {@link org.nicerobot.util.Hex#valueOf(byte[])}.
	 */
	@Test
	public void testValueOfNull () {
		final byte[] b = null;
		Assert.assertEquals(null, Hex.valueOf(b));
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
