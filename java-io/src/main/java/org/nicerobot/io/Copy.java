package org.nicerobot.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author nicerobot
 * 
 */
public final class Copy {

  /**
   * @param source
   * @param destination
   * @throws IOException
   */
  public static void file (final File source, final File destination) throws IOException {
    if (!destination.exists()) {
      destination.createNewFile();
    }

    FileChannel s = null;
    FileChannel d = null;
    try {
      s = new FileInputStream(source).getChannel();
      d = new FileOutputStream(destination).getChannel();
      d.transferFrom(s, 0, s.size());
    } finally {
      if (s != null) {
        s.close();
      }
      if (d != null) {
        d.close();
      }
    }
  }

  private Copy () {}
}