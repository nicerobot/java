package org.nicerobot.util;

import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class AttrMapIterator extends NamedNodeMapIterator<Attr> {

  /**
   * @param map
   */
  private AttrMapIterator (final NamedNodeMap map) {
    super(map);
  }

  public static final Iterable<Attr> attrIterator (final Node node) {
    return new AttrMapIterator(node.getAttributes());
  }

}
