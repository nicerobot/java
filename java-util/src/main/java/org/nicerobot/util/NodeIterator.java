package org.nicerobot.util;

import java.util.Iterator;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * @author nicerobot
 * 
 * @param <T>
 */
public class NodeIterator<T> implements Iterator<T>, Iterable<T> {

  /**
   * @param nodeList
   * @return
   */
  public static final Iterable<Attr> attributeIterator (@SuppressWarnings ("unused") final Node node) {
    return null; // TODO NodeIterator.<Attr> nodeIterator(node.getAttributes(),
    // Node.ATTRIBUTE_NODE);
  }

  /**
   * @param <T>
   * @param document
   * @param element
   * @param nodeType
   * @return
   */
  public static final <T> Iterable<T> nodeIterator (final Element document, final String element,
      final short nodeType) {
    return NodeIterator.<T> nodeIterator(document.getElementsByTagName(element), nodeType);
  }

  /**
   * @param <T>
   * @param services
   * @param nodeType
   * @return
   */
  public static final <T> Iterable<T> nodeIterator (final NodeList services, final short nodeType) {
    return new NodeIterator<T>(services, nodeType);
  }

  /**
   * @param nodeList
   * @return
   */
  public static final Iterable<Text> textIterator (final NodeList nodeList) {
    return NodeIterator.<Text> nodeIterator(nodeList, Node.TEXT_NODE);
  }

  /**
   * 
   */
  private final NodeList nodes;

  /**
   * 
   */
  private final int type;

  /**
   * 
   */
  private final int length;

  /**
   * 
   */
  private int index = 0;

  /**
   * 
   */
  private Node node;

  /**
   * @param nodes_
   */
  public NodeIterator (final NodeList nodes_, final int type_) {
    this.nodes = nodes_;
    this.length = nodes_.getLength();
    this.type = type_;
    this.move();
  }

  /* (non-Javadoc)
   * @see java.util.Iterator#hasNext()
   */
  @Override
  public boolean hasNext () {
    return null != this.node;
  }

  /* (non-Javadoc)
   * @see java.lang.Iterable#iterator()
   */
  /* (non-Javadoc)
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<T> iterator () {
    return this;
  }

  /**
   * 
   */
  private void move () {
    for (this.node = this.nodes.item(this.index++); (null != this.node)
        && (this.type != this.node.getNodeType()) && (this.index < this.length);) {
      this.node = this.nodes.item(this.index++);
    }
    if ((null != this.node) && (this.type != this.node.getNodeType())) {
      this.index = this.length;
      this.node = null;
    }
  }

  /* (non-Javadoc)
   * @see java.util.Iterator#next()
   */
  @SuppressWarnings ("unchecked")
  @Override
  public T next () {
    final Node result = this.node;
    this.move();
    return (T) result;
  }

  /* (non-Javadoc)
   * @see java.util.Iterator#remove()
   */
  @Override
  public void remove () {
    throw new UnsupportedOperationException();
  }
}
