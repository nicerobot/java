package org.nicerobot.io;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;

/**
 * Output (Writer) is used as input (Reader) for next process. Kinda like Piped(Reader/Writer) but
 * without the threading issues.
 * 
 * @author nicerobot
 * 
 */
public abstract class PipedStringBuffer implements PipeableStringBuffer {

  /*
   * (non-Javadoc)
   * 
   * @see org.nicerobot.io.Chainable#process(java.io.Reader, java.io.StringWriter)
   */
  public abstract StringWriter process (final Reader reader, final StringWriter writer)
      throws IOException;

  /**
   * Chain-output pattern.
   * 
   * @param in
   * @return
   * @throws IOException
   */
  public StringWriter process (final StringWriter in) throws IOException {
    if (null == in) {
      throw new NullPointerException("StringWriter must not be null");
    }
    in.flush();
    final StringWriter writer = new StringWriter(2048);
    final Reader reader = new StringBufferReader(in);
    this.process(reader, writer);
    return writer;
  }

}
