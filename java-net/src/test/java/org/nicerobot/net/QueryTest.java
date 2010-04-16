package org.nicerobot.net;

import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class QueryTest {

	@Before
	public void setUp () throws Exception {}

	@After
	public void tearDown () throws Exception {}

	@Test
	public void testEncode () {
	// TODO and to propagate to Values and Value
	}

	@Test
	public void testToString () {
		System.out.println(new Query());
	}

	@Test
	public void testToString1 () throws MalformedURLException {
		final String url = "http://user:pass@anywhere.test/somewhere?a=1&b=1&and&x=1&x=2&and&x&c=3";
		final String e = "a=1&b=1&and[]=&and[]=&x[]=1&x[]=2&x[]=&c=3";
		Assert.assertEquals(e, new Query(new URL(url)).toString());
	}

	@Test
	public void testToString2 () {
		final String e = "abcdefg)(!@#=&*(*#=";
		Assert.assertEquals(e, new Query("abcdefg)(!@#&*(*#").toString());
	}

	@Test
	public void testToString3 () {
		final String e = "a=1&b=1&and[]=&and[]=&x[]=1&x[]=2&x[]=&c=3";
		Assert.assertEquals(e, new Query("a=1&b=1&and&x=1&x=2&and&x&c=3").toString());
	}

	@Test
	public void testToString4 () {
		final String e = "a=1&b=1&and[]=&and[]=&x[]=1&x[]=2&x[]=&c=3";
		Assert.assertEquals(e, new Query("?a=1&b=1&and&x=1&x=2&and&x&c=3").toString());
	}

	@BeforeClass
	public static void setUpBeforeClass () throws Exception {}

	@AfterClass
	public static void tearDownAfterClass () throws Exception {}

}
