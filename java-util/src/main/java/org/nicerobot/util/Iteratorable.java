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

/**
 * Seems silly but Iterators aren't Iterable and static methods that return Iterators aren't
 * friendly to Iterable loop-syntax. For example, Service.providers().
 * 
 * @author nicerobot
 * 
 * @param <T>
 */
public class Iteratorable<T> implements Iterable<T> {

	private final Iterator<T> i;

	/**
	 * @param i
	 */
	public Iteratorable (final Iterator<T> i) {
		this.i = i;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator () {
		return this.i;
	}
}
