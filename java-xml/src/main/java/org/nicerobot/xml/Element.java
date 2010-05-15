package org.nicerobot.xml;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * TODO would be nice to be able to parse XML and JSONML into this structure easily.
 * 
 * @author nicerobot
 * 
 */
@SuppressWarnings ("serial")
public class Element extends ArrayList<Node> implements Node {

  public static class Text implements Node {
    public final String value;

    public Text (final String value_) {
      this.value = value_;
    }
  }

  public static void main (final String[] args) {
    /*
     * <root name="value" message="value">
     *   <random>
     *     <any what="oh"/>
     *   </random>
     * </root>
     */
    final Element xml = new Element("root", new TreeMap<String, String>() {
      {
        this.put("name", "value");
        this.put("message", "value");
      }
    }) {
      {
        this.add(new Element("random") {
          {
            this.add(new Element("any", new TreeMap<String, String>() {
              {
                this.put("what", "oh");
              }
            }));
          }
        });
      }
    };
    System.out.println(xml);
  }

  public final String name;

  @SuppressWarnings ("unused")
  private TreeMap<String, String> attrs;

  public Element (final String name_) {
    this.name = name_;
  }

  public Element (final String name_, final TreeMap<String, String> attrs_) {
    this(name_);
    this.attrs = attrs_;
  }
}

interface Node {

}
