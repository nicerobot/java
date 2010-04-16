package org.nicerobot.xml.io;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

/**
 * This class does not handle every type of DOM node, and it doesn't deal with all the details of
 * XML like DTDs, character encodings and preserved and ignored whitespace. However, it does output
 * basic well-formed XML that can be parsed by a non-validating parser.
 */
public class XMLNodeWriter implements Closeable, Flushable {

  private final Writer out; // the stream to send output to

  /** Initialize the output stream */
  public XMLNodeWriter (final OutputStream out) {
    this(new BufferedWriter(new OutputStreamWriter(out)));
  }

  /** Initialize the output stream */
  public XMLNodeWriter (final Writer out) {
    this.out = out;
  }

  /**
   * Close the output stream.
   * 
   * @throws IOException
   */
  public void close () throws IOException {
    this.out.close();
  }

  // This method replaces reserved characters with entities.
  String escape (final String s) {
    final StringBuilder sb = new StringBuilder();
    final int len = s.length();
    for (int i = 0; i < len; i++) {
      final char c = s.charAt(i);
      switch (c) {
        default:
          sb.append(c);
          break;
        case '<':
          sb.append("&lt;");
          break;
        case '>':
          sb.append("&gt;");
          break;
        case '&':
          sb.append("&amp;");
          break;
        case '"':
          sb.append("&quot;");
          break;
        case '\'':
          sb.append("&apos;");
          break;
      }
    }
    return sb.toString();
  }

  @Override
  public void flush () throws IOException {
    this.out.flush();
  }

  /**
   * Output a DOM Node (such as a Document) to the output stream
   * 
   * @throws IOException
   */
  public XMLNodeWriter write (final Node node) throws IOException {
    try {
      return this.write(node, "");
    } finally {
      this.flush();
    }
  }

  /**
   * Output the specified DOM Node object, printing it using the specified indentation string
   * 
   * @throws IOException
   */
  public XMLNodeWriter write (final Node node, final String indent) throws IOException {
    // The output depends on the type of the node
    switch (node.getNodeType()) {
      case Node.DOCUMENT_NODE: { // If its a Document node
        final Document doc = (Document) node;
        this.out.write(new StringBuilder().append(indent).append("<?xml version='1.0'?>\n")
                                          .toString()); // Output header
        Node child = doc.getFirstChild(); // Get the first node
        while (child != null) { // Loop 'till no more nodes
          this.write(child, indent); // Output node
          child = child.getNextSibling(); // Get next node
        }
        this.flush();
        break;
      }
      case Node.DOCUMENT_TYPE_NODE: { // It is a <!DOCTYPE> tag
        final DocumentType doctype = (DocumentType) node;
        // Note that the DOM Level 1 does not give us information about
        // the the public or system ids of the doctype, so we can't output
        // a complete <!DOCTYPE> tag here. We can do better with Level 2.
        this.out.write(new StringBuilder().append("<!DOCTYPE ").append(doctype.getName())
                                          .append(">\n").toString());
        break;
      }
      case Node.ELEMENT_NODE: { // Most nodes are Elements
        final Element elt = (Element) node;
        {
          final StringBuilder sb = new StringBuilder();
          sb.append(indent).append("<").append(elt.getTagName()); // Begin start tag
          final NamedNodeMap attrs = elt.getAttributes(); // Get attributes
          for (int i = 0; i < attrs.getLength(); i++) { // Loop through them
            final Node a = attrs.item(i);
            sb.append(" ").append(a.getNodeName()).append("='"); // Print attr. name
            sb.append(this.escape(a.getNodeValue())).append("'"); // Print attr. value
          }
          sb.append(">\n"); // Finish start tag
          this.out.write(sb.toString());
        }

        final String newindent = indent + "    "; // Increase indent
        Node child = elt.getFirstChild(); // Get child
        while (child != null) { // Loop
          this.write(child, newindent); // Output child
          child = child.getNextSibling(); // Get next child
        }

        this.out.write(new StringBuilder().append(indent).append("</").append(elt.getTagName())
                                          .append(">\n").toString()); // Output end tag
        break;
      }
      case Node.TEXT_NODE: { // Plain text node
        {
          final StringBuilder sb = new StringBuilder();
          final Text textNode = (Text) node;
          final String text = textNode.getData().trim(); // Strip off space
          if (text != null && text.length() > 0) {
            sb.append(indent).append(this.escape(text)).append("\n"); // print text
          }
          this.out.write(sb.toString()); // print text
        }
        break;
      }
      case Node.PROCESSING_INSTRUCTION_NODE: { // Handle PI nodes
        final ProcessingInstruction pi = (ProcessingInstruction) node;
        this.out.write(new StringBuilder().append(indent).append("<?").append(pi.getTarget())
                                          .append(" ").append(pi.getData()).append("?>\n")
                                          .toString()); // print text
        break;
      }
      case Node.ENTITY_REFERENCE_NODE: { // Handle entities
        this.out.write(new StringBuilder().append(indent).append("&").append(node.getNodeName())
                                          .append(";\n").toString());
        break;
      }
      case Node.CDATA_SECTION_NODE: { // Output CDATA sections
        final CDATASection cdata = (CDATASection) node;
        // Careful! Don't put a CDATA section in the program itself!
        this.out.write(new StringBuilder().append(indent).append("<").append("![CDATA[")
                                          .append(cdata.getData()).append("]]").append(">\n")
                                          .toString());
        break;
      }
      case Node.COMMENT_NODE: { // Comments
        final Comment c = (Comment) node;
        this.out.write(new StringBuilder().append(indent).append("<!--").append(c.getData())
                                          .append("-->\n").toString());
        break;
      }
      default: // Hopefully, this won't happen too much!
        System.err.println("Ignoring node: " + node.getClass().getName());
        break;
    }
    return this;
  }

}
