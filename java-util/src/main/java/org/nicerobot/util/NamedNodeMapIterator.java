package org.nicerobot.util;

import java.util.Iterator;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NamedNodeMapIterator<T> implements Iterator<T>, Iterable<T> {

  public static final <T> Iterable<T> nodeIterator (final NamedNodeMap map) {
    return new NamedNodeMapIterator<T>(map);
  }

  public static final <T> Iterable<T> nodeIterator (final Node node) {
    return NamedNodeMapIterator.<T> nodeIterator(node.getAttributes());
  }

  private final NamedNodeMap map;

  private final int length;

  private int index = 0;

  /**
   * @param map
   */
  public NamedNodeMapIterator (@SuppressWarnings ("hiding") final NamedNodeMap map) {
    this.map = map;
    this.length = map.getLength();
  }

  @Override
  public boolean hasNext () {
    return this.index < this.length;
  }

  /* (non-Javadoc)
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<T> iterator () {
    return this;
  }

  @SuppressWarnings ("unchecked")
  @Override
  public T next () {
    return (T) this.map.item(this.index++);
  }

  @Override
  public void remove () {
    throw new UnsupportedOperationException();
  }
}
