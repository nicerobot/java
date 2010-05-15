package org.nicerobot.xml.soap;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * @author rnix
 * 
 */
public class SOAPMessageReader extends PipedOutputStream implements Runnable {

  /**
   * @param message
   * @return
   * @throws IOException
   * @throws SOAPException
   */
  public static final Reader getReader (final SOAPMessage message) throws IOException,
      SOAPException {
    final SOAPMessageReader reader = new SOAPMessageReader(message);
    new Thread(reader).start();
    return new InputStreamReader(new PipedInputStream(reader));
  }

  private final SOAPMessage message;

  private SOAPMessageReader (final SOAPMessage message) {
    this.message = message;
  }

  @Override
  public void close () throws IOException {
    super.close();
  }

  public void run () {
    try {
      this.message.writeTo(this);
      this.close();
    } catch (final SOAPException e) {
      e.printStackTrace();
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }
}
