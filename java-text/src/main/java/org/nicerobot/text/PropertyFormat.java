package org.nicerobot.text;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

/**
 * Construct Strings containing embedded System Property values. e.g. "{user.home}/tmp"
 * 
 * @author nicerobot
 * 
 */
@SuppressWarnings ("serial")
public class PropertyFormat extends Format {

  private String pattern = null;

  /**
   * @param pattern
   */
  public PropertyFormat (final String pattern) {
    if (null == pattern) {
      throw new NullPointerException("pattern must not be null");
    }
    this.pattern = pattern;
  }

  /* (non-Javadoc)
   * @see java.text.Format#format(java.lang.Object, java.lang.StringBuffer, java.text.FieldPosition)
   */
  @Override
  public StringBuffer format (final Object obj, final StringBuffer toAppendTo,
      final FieldPosition pos) {
    return null;
  }

  /* (non-Javadoc)
   * @see java.text.Format#parseObject(java.lang.String, java.text.ParsePosition)
   */
  @Override
  public Object parseObject (final String obj, final ParsePosition pos) {
    return null;
  }

}
