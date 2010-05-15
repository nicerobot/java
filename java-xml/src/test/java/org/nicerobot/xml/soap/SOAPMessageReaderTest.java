package org.nicerobot.xml.soap;

import java.io.IOException;
import java.io.Reader;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.nicerobot.xml.soap.SOAPMessageReader;

public class SOAPMessageReaderTest {

  @BeforeClass
  public static void setUpBeforeClass () throws Exception {}

  @AfterClass
  public static void tearDownAfterClass () throws Exception {}

  @Before
  public void setUp () throws Exception {}

  @After
  public void tearDown () throws Exception {}

  @Test
  public void testGetReader () throws SOAPException, IOException {
    SOAPMessage message = null;
    final MessageFactory messageFactory = MessageFactory.newInstance();
    message = messageFactory.createMessage();
    for (int i = 0; i < 10000; i++) {
      message.getSOAPHeader().addTextNode("HEADER");
      message.getSOAPBody().addChildElement("header" + i).addTextNode("" + i);
      message.createAttachmentPart().setContentType("text/plain");
      message.getSOAPBody().addTextNode("BODY");
      message.getSOAPBody().addChildElement("body" + i).addTextNode("" + i);
      System.out.println(i);
    }
    final Reader r = SOAPMessageReader.getReader(message);
    int c = 0;
    int i = 1;
    while (-1 != (c = r.read())) {
      if (0 == i++ % 1024) {
        System.out.format("\n------- %dK -------\n", (i / 1024));
      }
      System.out.print((char) c);
    }
  }
}
