package org.nicerobot.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author nicerobot
 * 
 * @param <T>
 */
public class ElementIterator<T> extends NodeIterator<Element> {

  /**
   * @param document
   * @param element
   * @return
   */
  public static final Iterable<Element> elementIterator (final Document document,
      final String element) {
    return NodeIterator.<Element> nodeIterator(document.getDocumentElement(), element,
                                               org.w3c.dom.Node.ELEMENT_NODE);
  }

  /**
   * @param document
   * @param element
   * @return
   */
  public static final Iterable<Element> elementIterator (final Element document,
      final String element) {
    return NodeIterator.<Element> nodeIterator(document, element, org.w3c.dom.Node.ELEMENT_NODE);
  }

  /**
   * @param nodeList
   * @return
   */
  public static final Iterable<Element> elementIterator (final NodeList nodeList) {
    return NodeIterator.<Element> nodeIterator(nodeList, Node.ELEMENT_NODE);
  }

  /**
   * @param nodes_
   */
  public ElementIterator (final NodeList nodes_) {
    super(nodes_, Node.ELEMENT_NODE);
  }
}
