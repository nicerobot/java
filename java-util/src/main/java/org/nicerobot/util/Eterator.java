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

import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author nicerobot
 * 
 */
public class Eterator<T> implements Iterator<T>, Iterable<T> {

  /**
	 * 
	 */
  private final Enumeration<T> e;

  /**
   * @param e
   */
  public Eterator (final Enumeration<T> e) {
    this.e = e;
  }

  /* (non-Javadoc)
   * @see java.util.Iterator#hasNext()
   */
  public boolean hasNext () {
    return this.e.hasMoreElements();
  }

  /* (non-Javadoc)
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<T> iterator () {
    return this;
  }

  /* (non-Javadoc)
   * @see java.util.Iterator#next()
   */
  public T next () {
    return this.e.nextElement();
  }

  /* (non-Javadoc)
   * @see java.util.Iterator#remove()
   */
  public void remove () {
    throw new UnsupportedOperationException();
  }

}
