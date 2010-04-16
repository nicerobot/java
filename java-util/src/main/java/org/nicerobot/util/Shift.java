package org.nicerobot.util;

import java.util.Arrays;

public class Shift {

  public static <T> T[] left (final T... i) {
    if (1 >= i.length) {
      return i;
    }
    final T t = i[0];
    int x = 0;
    for (; x < i.length - 1; x++) {
      i[x] = i[x + 1];
    }
    i[x] = t;
    return i;
  }

  /**
   * @param args
   */
  public static void main (final String[] args) {
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
