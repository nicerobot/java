package org.nicerobot.util;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CollectableTest {

  @Before
  public void setUp () throws Exception {}

  @After
  public void tearDown () throws Exception {}

  @Test
  public void testToArray () {
    String[] i = new String[0];
    i = Collectable.<String> toArray(new MatcherIterator(Pattern.compile("(.)").matcher("123")), i);
    System.out.println(Arrays.asList(i));
  }
}
