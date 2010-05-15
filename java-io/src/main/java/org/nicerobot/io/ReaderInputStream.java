package org.nicerobot.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * @author rnix
 * 
 */
public class ReaderInputStream extends InputStream {

  private class Copier implements Runnable {
    public void run () {
      final char[] buffer = new char[8192];
      try {
        while (true) {
          int n;
          synchronized (ReaderInputStream.this) {
            n = ReaderInputStream.this.reader.read(buffer);
          }
          if (n == -1) {
            break;
          }
          synchronized (ReaderInputStream.this) {
            ReaderInputStream.this.writer.write(buffer, 0, n);
            ReaderInputStream.this.writer.flush();
          }
        }
      } catch (final IOException e) {
        e.printStackTrace();
      } finally {
        ReaderInputStream.close(ReaderInputStream.this.reader);
        ReaderInputStream.close(ReaderInputStream.this.writer);
      }
    }
  }

  /**
   * @param cl
   */
  private static void close (final Closeable cl) {
    try {
      cl.close();
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  private final Reader reader;

  private final Writer writer;

  private final PipedInputStream pipe;

  /**
   * @param reader
   * @throws IOException
   */
  public ReaderInputStream (@SuppressWarnings ("hiding") final Reader reader) throws IOException {
    this(reader, null);
  }

  /**
   * @param reader
   * @param encoding
   * @throws IOException
   */
  public ReaderInputStream (@SuppressWarnings ("hiding") final Reader reader, final String encoding)
      throws IOException {
    synchronized (this) {
      this.reader = reader;
      this.pipe = new PipedInputStream();
      final OutputStream outPipe = new PipedOutputStream(this.pipe);
      if (null == encoding) {
        this.writer = new OutputStreamWriter(outPipe);
      } else {
        this.writer = new OutputStreamWriter(outPipe, encoding);
      }
    }
    new Thread(new Copier()).start();
  }

  /* (non-Javadoc)
   * @see java.io.InputStream#available()
   */
  @Override
  public int available () throws IOException {
    return this.pipe.available();
  }

  /* (non-Javadoc)
   * @see java.io.InputStream#close()
   */
  @Override
  public synchronized void close () throws IOException {
    ReaderInputStream.close(this.reader);
    ReaderInputStream.close(this.writer);
    ReaderInputStream.close(this.pipe);
  }

  /* (non-Javadoc)
   * @see java.io.InputStream#read()
   */
  @Override
  public int read () throws IOException {
    return this.pipe.read();
  }

  /* (non-Javadoc)
   * @see java.io.InputStream#read(byte[])
   */
  @Override
  public int read (final byte b[]) throws IOException {
    return this.pipe.read(b);
  }

  /* (non-Javadoc)
   * @see java.io.InputStream#read(byte[], int, int)
   */
  @Override
  public int read (final byte b[], final int off, final int len) throws IOException {
    return this.pipe.read(b, off, len);
  }

  /* (non-Javadoc)
   * @see java.io.InputStream#skip(long)
   */
  @Override
  public long skip (final long n) throws IOException {
    return this.pipe.skip(n);
  }
}