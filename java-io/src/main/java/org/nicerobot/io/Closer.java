package org.nicerobot.io;

import java.io.Closeable;

/**
 * @author nicerobot
 * 
 */
public class Closer {

  /**
   * @param closeables
   */
  public static void close (final Closeable... closeables) {
    for (final Closeable c : closeables) {
      try {
        if (c != null) {
          c.close();
        }
      } catch (final Exception ex) {}
    }
  }
}
