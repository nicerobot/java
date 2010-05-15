package org.nicerobot.util;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShiftTest {

  @Before
  public void setUp () throws Exception {}

  @After
  public void tearDown () throws Exception {}

  @SuppressWarnings ("boxing")
  @Test
  public void testLeft () {
    final Integer[] i = { 1, 2, 3, 4, 5 };
    System.out.format("%s\n", Arrays.toString(i));
    System.out.format("%s\n", Arrays.toString(Shift.<Integer> left(i)));
    System.out.format("%s\n", Arrays.toString(Shift.left(new String(""), new Object())));

    final int x = 1;
    final int y = 2;
    Integer[] yx = { x, y };
    System.out.format("%s\n", Arrays.toString(Shift.left(yx)));

    yx = Shift.left(x, y);
    System.out.format("%s\n", Arrays.toString(yx));
  }

}
