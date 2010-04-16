/**
 * 
 */
package javascript;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.nicerobot.javascript.JSAnonymous;
import org.nicerobot.javascript.JSArray;
import org.nicerobot.javascript.JSFunction;
import org.nicerobot.javascript.JSObject;

/**
 * @author nicerobot
 * 
 */
public class JSArrayTest {

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

	final JSArray<String> p = new JSArray<String>().put("a", "b", "c");

	final JSArray<Object> a = new JSArray<Object>();

	final JSObject o = new JSObject();

	@Before
	public void setUp () throws Exception {}

	@After
	public void tearDown () throws Exception {
		System.out.println("\n");
		System.out.println(this.p);
		System.out.println(this.a);
		System.out.println(this.o);
	}

	@Test
	public void testJSArrayAnonymous () {
		this.a.put(new JSAnonymous(this.p, "body();"), new JSFunction("test", "body();"));
	}

	@Test
	public void testJSArrayArray () {
		this.a.put(this.p, this.o, this.p);
	}

	@Test
	public void testJSArrayBoolean () {
		this.a.put(true, false, null, false, true);
	}

	@Test
	public void testJSArrayFunction () {
		this.a.put(new JSAnonymous(), new JSFunction("test", this.p, "body();"));
	}

	@Test
	public void testJSArrayNumber () {
		this.a.put(1, 2, 3, 0, -1, -2, -3);
	}

	@Test
	public void testJSArrayObject () {
		this.a.put(this.o, this.o, this.o);
	}

	@Test
	public void testJSArrayString () {
		this.a.put("a", "b", "c");
	}

}
