package org.nicerobot.util;


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

}
