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

package org.nicerobot.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author nicerobot
 * 
 */
public final class Slurp {

	/**
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String data (final File file) throws IOException {
		if (null == file) {
			return null;
		}
		return Slurp.data(new FileInputStream(file));
	}

	/**
	 * This wraps the InputStream in a BufferedReader.
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String data (final InputStream is) throws IOException {
		if (null == is) {
			return null;
		}
		final BufferedReader in = new BufferedReader(new InputStreamReader(is));
		final StringBuilder sb = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
			sb.append(line).append("\n");
		}
		in.close();
		return sb.toString();
	}

	/**
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String data (final String fileName) throws IOException {
		if (null == fileName) {
			return null;
		}
		return Slurp.data(new File(fileName));
	}

	/**
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String data (final URL url) throws IOException {
		if (null == url) {
			return null;
		}
		return null;
	}
}
