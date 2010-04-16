package org.nicerobot.net;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValuesTest {

	@Before
	public void setUp () throws Exception {}

	@After
	public void tearDown () throws Exception {}

	@Test
	public void testJoin () {
		final Values v = new Values();
		v.add("one");
		v.add("two");
		v.add("three");
		v.add("one");
		final String e = "name[]=one,name[]=two,name[]=three,name[]=one";
		Assert.assertEquals(e, "name[]=" + v.join(",name[]="));
	}

	@Test
	public void testToString () {
		final Values v = new Values();
		v.add("one");
		v.add("two");
		v.add("three");
		v.add("one");
		Assert.assertEquals("one,two,three,one", v.toString());
	}

	@BeforeClass
	public static void setUpBeforeClass () throws Exception {}

	@AfterClass
	public static void tearDownAfterClass () throws Exception {}

}
