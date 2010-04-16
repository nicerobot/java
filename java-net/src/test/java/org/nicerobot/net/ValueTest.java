package org.nicerobot.net;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValueTest {

	@Before
	public void setUp () throws Exception {}

	@After
	public void tearDown () throws Exception {}

	@Test
	public void testNull () {
		Assert.assertEquals("", new Value<Value<Value<Object>>>().toString());
	}

	@Test
	public void testObject () {
		final Value<Object> v = new Value<Object>(new Object());
		Assert.assertTrue(v.toString().startsWith(Object.class.getName()));
	}

	@Test
	public void testOverride () {
		Assert.assertEquals("DDD", new Value<Object>(new Object() {
			@Override
			public String toString () {
				return "DDD";
			}
		}).toString());
	}

	@Test
	public void testToString () {
		Assert.assertEquals("SDS", new Value<String>("SDS").toString());
	}

	@BeforeClass
	public static void setUpBeforeClass () throws Exception {}

	@AfterClass
	public static void tearDownAfterClass () throws Exception {}

}
