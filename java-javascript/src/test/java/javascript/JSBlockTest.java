package javascript;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.nicerobot.javascript.JSBlock;

public class JSBlockTest {

	@BeforeClass
	public static void setUpBeforeClass () throws Exception {}

	@AfterClass
	public static void tearDownAfterClass () throws Exception {}

	final JSBlock p = new JSBlock().put("a", "b", "c");

	@Before
	public void setUp () throws Exception {}

	@After
	public void tearDown () throws Exception {}

	@Test
	public void testJSBlock () {
		System.out.println(this.p.toString());
	}

}
