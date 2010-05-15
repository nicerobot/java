package org.nicerobot.xml.soap;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;

import org.junit.After;
import org.junit.Before;

public class SOAPTestBase {
  private static MessageFactory messageFactory;
  static {
    try {
      SOAPTestBase.messageFactory = MessageFactory.newInstance();
    } catch (final SOAPException e) {
      e.printStackTrace();
    }
  }

  public static final MessageFactory getMessageFactory () {
    return SOAPTestBase.messageFactory;
  }

  private SOAPMessage message = null;

  private final String inst = String.valueOf(System.currentTimeMillis());

  private final MessageContext messageContext = null;

  public static final String POLLING = "http://www.w3.org/2005/08/ws-polling";
  public static final String ADDRESSING_2005 = "http://www.w3.org/2005/08/ws-polling";
  public static final String ADDRESSING_2004 = "http://schemas.xmlsoap.org/ws/2004/08/addressing";

  public SOAPMessage createMessage () throws SOAPException {
    return SOAPTestBase.messageFactory.createMessage();
  }

  public String getInst () {
    return this.inst;
  }

  public SOAPMessage getMessage () {
    return this.message;
  }

  public MessageContext getMessageContext () {
    return this.messageContext;
  }

  @Before
  public void setUp () throws Exception {
    this.message = this.createMessage();
    final SOAPEnvelope envelope = this.message.getSOAPPart().getEnvelope();
    final SOAPHeader head = this.message.getSOAPHeader();
    final SOAPBody body = this.message.getSOAPBody();
    for (int i = 0; i < 10; i++) {
      head.addChildElement("headerchild" + i).addTextNode("text" + i);
      body.addChildElement("bodychild" + i).addTextNode("text" + i);
    }
    head.addChildElement(envelope.createName("ReplyTo", "wsa", SOAPTestBase.ADDRESSING_2004));
    head.addChildElement(envelope.createName("MessageID", "wsa", SOAPTestBase.ADDRESSING_2004));
    body.addChildElement(envelope.createName("StatusRequested", "polling", SOAPTestBase.POLLING));
  }

  @After
  public void tearDown () throws Exception {}

}
