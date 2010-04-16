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

public class JSObjectTest {

	@BeforeClass
	public static void setUpBeforeClass () throws Exception {}

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
	public void testJSObjectAnonymous () {
		this.o.put(new JSAnonymous(), new JSFunction("named", this.p, "body();"));
	}

	@Test
	public void testJSObjectArray () {
		this.o.put(this.a, this.p).put(this.p, this.a);
	}

	@Test
	public void testJSObjectBoolean () {
		this.o.put(true, false).put(false, true);
	}

	@Test
	public void testJSObjectFunction () {
		this.o.put("anonymous", new JSAnonymous()).put(new JSFunction("key"), new JSAnonymous());
	}

	@Test
	public void testJSObjectNumber () {
		this.o.put(1, 2);
	}

	@Test
	public void testJSObjectObject () {
		this.o.put("object", new JSObject().put("key", "value"));
	}

	@Test
	public void testJSObjectString () {
		this.o.put("string", "b");
	}

}
