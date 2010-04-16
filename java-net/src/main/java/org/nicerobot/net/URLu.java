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

package org.nicerobot.net;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author nicerobot
 * 
 */
public final class URLu {

	/**
	 * @param surl_
	 * @param functionName_
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL appendName (final String surl_, final String... add_)
			throws MalformedURLException {
		return URLu.appendName(new URL(surl_), add_);
	}

	/**
	 * @param node
	 * @param lookupPrefix
	 * @param functionName
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL appendName (final URL source_, final String... add_)
			throws MalformedURLException {
		return URLu.join(source_, add_);
	}

	/**
	 * @param source_
	 * @param functionName_
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL appendPath (final String source_, final String... add_)
			throws MalformedURLException {
		return URLu.appendPath(new URL(source_), add_);
	}

	/**
	 * @param node
	 * @param lookupPrefix
	 * @param functionName
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL appendPath (final URL source_, final String... add_)
			throws MalformedURLException {
		return URLu.join(source_, '/', add_);
	}

	/**
	 * @param source_
	 * @param functionName_
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL appendType (final String source_, final String ext_)
			throws MalformedURLException {
		return URLu.appendType(new URL(source_), ext_);
	}

	/**
	 * @param source_
	 * @param functionName_
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL appendType (final URL source_, final String ext_) throws MalformedURLException {
		return URLu.appendName(source_, ".", ext_);
	}

	/**
	 * @param source_
	 * @param string_
	 * @param add_
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL join (final URL source_, final char delimiter, final String... values_)
			throws MalformedURLException {
		final String protocol = source_.getProtocol();
		final String host = source_.getAuthority();
		final int port = source_.getPort();
		final String path = source_.getPath();
		final StringBuilder file = new StringBuilder(path);
		boolean first = path.endsWith("/");
		for (final String v : values_) {
			if (!first && 0 != delimiter) {
				file.append(delimiter);
			} else {
				first = false;
			}
			file.append(v);
		}
		final String query = source_.getQuery();
		if (null != query) {
			file.append("?").append(query);
		}
		final URL result = new URL(protocol, host, port, file.toString());
		return result;
	}

	/**
	 * @param source_
	 * @param string_
	 * @param add_
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL join (final URL source_, final String... values_) throws MalformedURLException {
		return URLu.join(source_, (char) 0, values_);
	}

}
