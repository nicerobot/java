package org.nicerobot.patterns.concurrency;

/**
 * This was simply an attempt to test what it'd be like if all object instances with mutable state
 * were implemented as threads with their values only available through a Future. I quickly realized
 * it solves nothing.
 * 
 * @author nicerobot
 * 
 */
class A extends Continuous {
  private int value = new Double(Math.random() * 10000).intValue();

  public synchronized int getValue () {
    return this.value;
  }

  @Override
  public void run () {
    while (!done) {
      try {
        sleep(100);
        this.value = new Double(Math.random() * 10000).intValue();
        synchronized (System.out) {
          System.out.format(super.toString(), this.value);
        }
      } catch (final InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public synchronized void setValue (final int value) {
    this.value = value;
  }

}

class B extends A {}

class C extends A {
  private int more = new Double(Math.random() * 10000).intValue();

  public synchronized int getMore () {
    return this.more;
  }

  @Override
  public void run () {
    while (!done) {
      try {
        sleep(100);
        synchronized (this) {
          this.setMore(new Double(Math.random() * 10000).intValue());
          this.setValue(this.more);
        }
        synchronized (System.out) {
          System.out.format(super.toString(), this.more);
        }
      } catch (final InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public synchronized void setMore (final int more) {
    this.more = more;
  }
}

/**
 * A mutable object in which every instance runs in its own Thread.
 * 
 * @author nicerobot
 * 
 */
public class Continuous extends Thread {

  protected static boolean done = false;

  public static void main (final String[] args) {
    System.out.println("\u001b[2J");
    new Main();
  }

  public Continuous () {
    this.start();
  }

  @Override
  public void run () {

  }

  @Override
  public String toString () {
    return String.format("\u001b[%d;0H%s %d %%d\u001b[K\u001b[0;3H", SerialNum.get() + 1,
                         this.getClass().getName(), SerialNum.get());
  }
}

class Main extends Continuous {
  @Override
  public void run () {

    final A x[] = { new A(), new A(), new B(), new B(), new C(), new C() };

    while (true) {
      final String command = System.console().readLine("\u001b[0;0H> \u001b[K");
      if ((command.length() > 0)) {
        if ("quit".startsWith(command.toLowerCase()) || "exit".startsWith(command.toLowerCase())) {
          done = true;
          return;
        }
      }

      int l = 10;
      for (final A c : x) {
        System.out.format("\u001b[%d;0H%d = %5d\u001b[K", l++, l - 10, c.getValue());
      }

    }
  }

}

class SerialNum {
  // The next serial number to be assigned
  private static int nextSerialNum = 1;

  private static ThreadLocal<Integer> serialNum = new ThreadLocal<Integer>() {
    @Override
    protected synchronized Integer initialValue () {
      return new Integer(nextSerialNum++);
    }
  };

  public static int get () {
    return serialNum.get().intValue();
  }
}