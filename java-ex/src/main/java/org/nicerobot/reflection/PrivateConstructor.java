package org.nicerobot.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PrivateConstructor {

  /**
   * @param args
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   * @throws InstantiationException
   * @throws IllegalArgumentException
   */
  public static void main (final String[] args) throws IllegalArgumentException,
      InstantiationException, IllegalAccessException, InvocationTargetException {
    final Constructor<?> cons = Math.class.getDeclaredConstructors()[0];
    // Change the accessible property of the constructor.
    cons.setAccessible(true);
    final Math math = (Math) cons.newInstance((Object[]) null);
    // You are in !
    System.out.println(math.hashCode());
    System.out.println(((Math) cons.newInstance((Object[]) null)).hashCode());
  }
}
