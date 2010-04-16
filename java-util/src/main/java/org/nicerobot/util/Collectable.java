package org.nicerobot.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * @author nicerobot
 * 
 */
public class Collectable {

  /**
   * @param <T>
   * @param i
   * @param a
   * @return
   */
  public static <T> T[] toArray (final Iterable<T> i, final T[] a) {
    return toList(i).toArray(a);
  }

  /**
   * @param <T>
   * @param i
   * @param a
   * @return
   */
  public static <T> List<T> toList (final Iterable<T> i) {
    final List<T> t = new ArrayList<T>();
    for (final T e : i) {
      t.add(e);
    }
    return t;
  }

  /**
   * @param <T>
   * @param i
   * @param a
   * @return
   */
  public static List<String> toList (final Matcher m) {
    return Collectable.<String> toList(new MatcherIterator(m));
  }

  /**
   * @param <T>
   * @param i
   * @param a
   * @return
   */
  public static <T> Set<T> toSet (final Iterable<T> i) {
    final Set<T> t = new HashSet<T>();
    for (final T e : i) {
      t.add(e);
    }
    return t;
  }

}
