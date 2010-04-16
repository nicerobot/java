package org.nicerobot.java.language;

import java.util.HashSet;
import java.util.Set;

/**
 * @author nicerobot
 * 
 */
@SuppressWarnings ("serial")
public class Inner {

  /**
   * @author nicerobot
   * 
   */
  public interface Any {
    public String get ();
  }

  /**
   * @author nicerobot
   * 
   */
  @SuppressWarnings ("unused")
  private class ClassInner implements Any {
    private final String t;

    public ClassInner (final String s) {
      this.t = s;
      System.out.format("inside: %s%n", s);
    }

    @Override
    public String get () {
      return this.t;
    }

    @Override
    public String toString () {
      return "Inner [t=" + this.t + "]";
    }
  }

  /**
   * @author nicerobot
   * 
   */
  @SuppressWarnings ("unused")
  private static class ClassStaticInner implements Any {
    private final String t;

    public ClassStaticInner (final String s) {
      this.t = s;
      System.out.format("inside: %s%n", s);
    }

    @Override
    public String get () {
      return this.t;
    }

    @Override
    public String toString () {
      return "Inner [t=" + this.t + "]";
    }
  }

  /**
   * @param args
   */
  public static void main (String[] args) {
    if (0 == args.length) {
      args = new String[] { "one", "two", "three" };
    }
    for (final String s : args) {
      final Any any = new Inner().methodClass(s);
      System.out.format("outside: %s%n", any);
    }
    final ThreadLocal<String> here = new ThreadLocal<String>() {
      {
        this.set("main");
      }
    };
    new Thread() {
      {
        System.out.format("thread's ThreadLocal .init: %s%n", here.get());
      }

      @Override
      public void run () {
        System.out.format("thread's ThreadLocal .run: %s%n", here.get());
      }
    }.start();

  }

  /**
   * Define and initialize s using an anonymous inner initializer.
   */
  private final Set<String> s = new HashSet<String>(3) {
    {
      this.add("one");
      this.add("two");
      this.add("three");
    }
  };

  /**
   * 
   */
  public Inner () {}

  /**
   * @param x
   * @return
   */
  private Any methodClass (final String x) {
    class MethodInner implements Any {
      private final String t;
      protected Set<String> s = null;

      public MethodInner (final String s) {
        this.t = s;
        System.out.format("inside: %s : %s%n", s, x);
      }

      @Override
      public String get () {
        return this.t + ":" + this.s;
      }

      @Override
      public String toString () {
        return "Inner [t=" + this.t + "," + this.s + "]";
      }
    };
    final Set<String> dr = new HashSet<String>() {
      {
        this.add(String.valueOf(Math.random()));
        this.add(String.valueOf(Math.random()));
        this.add(String.valueOf(Math.random()));
      }
    };
    final MethodInner inner = new MethodInner(x) {
      {
        this.s = dr;
      }
    };
    return inner;
  }
}
