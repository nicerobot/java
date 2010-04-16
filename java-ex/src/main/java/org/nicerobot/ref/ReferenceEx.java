package org.nicerobot.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Examples of how Soft, Weak and Phantom references are reclaimed by the GC.
 * 
 * @author nicerobot
 * 
 */
public class ReferenceEx extends Thread {

  private static class Data {
    private final String name;

    public Data (final String name) {
      this.name = name;
    }

    @Override
    public String toString () {
      return this.name;
    }
  }

  private static class FatSoftData extends Data {
    private final Reference<Set<Integer>> data =
        new SoftReference<Set<Integer>>(new HashSet<Integer>(), softDataQueue);

    public FatSoftData (final String name) {
      super(name);
      final Set<Integer> s = this.data.get();
      for (int i = 0; i < 32768; i++) {
        try {
          s.add(i);
        } catch (final OutOfMemoryError e) {
          e.printStackTrace();
          break;
        }
      }
    }
  }

  private static class MapThread extends Thread {
    @Override
    public void run () {
      boolean done = false;
      while (!done) {
        try {

          synchronized (map) {

            map.wait(5000);
            done = map.isEmpty();

            System.out.format("\n%s\n------------------", new Date());

            for (final Entry<Reference<Data>, Data> me : map.entrySet()) {
              final Reference<Data> reference = me.getKey();

              // Check that a reference to an object was created
              System.out.format("\nReference: %-20s Data: %-20s Enqueued: %b", reference.get(),
                                map.get(reference), reference.isEnqueued());
            }
            System.out.format("\n");
          }
        } catch (final InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private static final ReferenceQueue<Set<Integer>> softDataQueue =
      new ReferenceQueue<Set<Integer>>();

  private static final ReferenceQueue<Data> softQueue = new ReferenceQueue<Data>();
  private static final ReferenceQueue<Data> weakQueue = new ReferenceQueue<Data>();
  private static final ReferenceQueue<Data> phantomQueue = new ReferenceQueue<Data>();
  private static final HashMap<Reference<Data>, Data> map = new HashMap<Reference<Data>, Data>();

  private static void add () {
    map.put(new SoftReference<Data>(new Data("Soft Reference"), softQueue),
            new FatSoftData("Soft Data"));
    map.put(new WeakReference<Data>(new Data("Weak Reference"), weakQueue),
            new FatSoftData("Weak Data"));
    map.put(new PhantomReference<Data>(new Data("Phantom Reference"), phantomQueue),
            new FatSoftData("Phantom Data"));
  }

  public static void main (final String[] args) {
    new ReferenceEx().start();
  }

  public static void remove () {
    Reference<? extends Data> ref = null;
    while (null != (ref = softQueue.poll())) {
      System.out.format("Removing: %s\n", ref);
      map.remove(ref);
    }

    while (null != (ref = weakQueue.poll())) {
      System.out.format("Removing: %s\n", ref);
      map.remove(ref);
    }

    while (null != (ref = phantomQueue.poll())) {
      System.out.format("Removing: %s\n", ref);
      map.remove(ref);
    }
  }

  @Override
  public void run () {

    add();

    final Thread t = new MapThread();
    t.start();

    while (true) {
      final String command = System.console().readLine("\n");
      synchronized (map) {
        map.notify();
        if ((command.length() > 0)) {
          if ("quit".startsWith(command.toLowerCase()) || "exit".startsWith(command.toLowerCase())) {
            map.clear();
            return;
          } else if ("add".startsWith(command.toLowerCase())) {
            add();
          } else if ("remove".startsWith(command.toLowerCase())) {
            remove();
          } else if ("gc".startsWith(command.toLowerCase())) {
            System.gc(); // Ask for GC
          }
        }
      }
    }
  }
}