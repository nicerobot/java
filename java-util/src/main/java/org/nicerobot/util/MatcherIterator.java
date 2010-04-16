/*
 * Copyright 2010 Nice Robot Corporation
 * http://nicerobot.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 *
 */
/**
 * 
 */

package org.nicerobot.util;

import java.util.Iterator;
import java.util.regex.Matcher;

/**
 * @author nicerobot
 * 
 */
public class MatcherIterator implements Iterator<String>, Iterable<String> {

  private final Matcher e;

  /**
   * @param e
   */
  public MatcherIterator (final Matcher matcher) {
    this.e = matcher;
  }

  /* (non-Javadoc)
   * @see java.util.Iterator#hasNext()
   */
  public boolean hasNext () {
    return MatcherIterator.this.e.find();
  }

  /* (non-Javadoc)
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<String> iterator () {
    return this;
  }

  /* (non-Javadoc)
   * @see java.util.Iterator#next()
   */
  public String next () {
    return MatcherIterator.this.e.group();
  }

  /**
   * @param group
   * @return
   */
  public String next (final int group) {
    return MatcherIterator.this.e.group(group);
  }

  /* (non-Javadoc)
   * @see java.util.Iterator#remove()
   */
  public void remove () {
    throw new UnsupportedOperationException();
  }

}
