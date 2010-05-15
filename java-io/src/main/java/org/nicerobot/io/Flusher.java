package org.nicerobot.io;

import java.io.Flushable;

/**
 * @author nicerobot
 * 
 */
public class Flusher {

  /**
   * @param flushables
   */
  public static void flush (final Flushable... flushables) {
    for (final Flushable c : flushables) {
      try {
        if (c != null) {
          c.flush();
        }
      } catch (final Exception ex) {}
    }
  }
}
