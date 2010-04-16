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
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.DigestException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import org.nicerobot.util.Hex;

/**
 * Verifies a file or URL against its message digest.
 * 
 * @author nicerobot
 * 
 */
public class DigestedSource {

	private static final String _md = "sha1";
	private String data = null;

	public DigestedSource (final File file_) throws IOException, DigestException {
		try {
			init(file_, MessageDigest.getInstance(DigestedSource._md));
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public DigestedSource (final File file_, final MessageDigest md) throws IOException,
			DigestException {
		init(file_, md);
	}

	public DigestedSource (final File file_, final String md) throws NoSuchAlgorithmException,
			IOException, DigestException {
		this(file_, MessageDigest.getInstance(md));
	}

	public DigestedSource (final String pathname_) throws IOException, DigestException {
		try {
			init(new File(pathname_), MessageDigest.getInstance(DigestedSource._md));
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public DigestedSource (final String pathname_, final MessageDigest md_) throws IOException,
			DigestException {
		this(new File(pathname_), md_);
	}

	public DigestedSource (final String pathname_, final String md) throws NoSuchAlgorithmException,
			IOException, DigestException {
		this(pathname_, MessageDigest.getInstance(md));
	}

	public DigestedSource (final URL uri_) throws IOException, DigestException {
		try {
			init(uri_, MessageDigest.getInstance(DigestedSource._md));
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public DigestedSource (final URL uri_, final MessageDigest md) throws IOException,
			DigestException {
		init(uri_, md);
	}

	public DigestedSource (final URL uri_, final String md) throws NoSuchAlgorithmException,
			IOException, DigestException {
		this(uri_, MessageDigest.getInstance(md));
	}

	/**
	 * @return the data
	 */
	public String getData () {
		return this.data;
	}

	/**
	 * @param pathname_
	 * @param instance_
	 * @throws IOException
	 * @throws DigestException
	 * @throws SignatureException
	 * @throws DigestException
	 */
	private void init (final File file_, final MessageDigest md_) throws IOException, DigestException {
		final String mdPath = String.format("%s.%s", file_.getPath(), md_.getAlgorithm().toLowerCase());

		final File mdFile = new File(mdPath);

		try {
			init(new FileInputStream(mdFile), new FileInputStream(file_), md_);
		} catch (final DigestException e) {
			final String message = String.format("%s\n%s", file_.getAbsolutePath(), e.getMessage());
			final DigestException x = new DigestException(message);
			x.setStackTrace(e.getStackTrace());
			throw x;
		}
	}

	private void init (final InputStream mdSource, final InputStream dataSource,
			final MessageDigest md_) throws IOException, DigestException {

		final String digest = DigestedSource.digest(mdSource);

		DigestInputStream dis = null;
		try {
			dis = new DigestInputStream(dataSource, md_);
			final String data = Slurp.data(dis);

			final boolean check = true;
			if (check) {
				// Calculate Message Digests for text
				final String digested = Hex.valueOf(md_.digest());
				if (null != dis && null != digest && digested.equalsIgnoreCase(digest)) {} else {
					throw new DigestException(String.format("%s : %s", digest, digested));
				}
			}

			this.data = data;

		} finally {
			dis.close();
		}
	}

	/**
	 * @param url_
	 * @param instance_
	 * @throws IOException
	 * @throws DigestException
	 * @throws DigestException
	 */
	private void init (final URL url_, final MessageDigest md_) throws IOException, DigestException {
		final String mdPath = String.format("%s.%s", url_.toString(), md_.getAlgorithm().toLowerCase());

		final HttpURLConnection mdConn = (HttpURLConnection) new URL(mdPath).openConnection();
		mdConn.setRequestMethod("GET");
		mdConn.connect();

		final HttpURLConnection dataConn = (HttpURLConnection) url_.openConnection();
		dataConn.setRequestMethod("GET");
		dataConn.connect();

		try {
			init(mdConn.getInputStream(), dataConn.getInputStream(), md_);
		} catch (final DigestException e) {
			final String message = String.format("%s\n%s", url_.toString(), e.getMessage());
			final DigestException x = new DigestException(message);
			x.setStackTrace(e.getStackTrace());
			throw x;
		} finally {
			mdConn.disconnect();
			dataConn.disconnect();
		}
	}

	private static String digest (final InputStream is) throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(is));
		final String digest = br.readLine();
		if (null != digest) {
			return digest.split("\\s+")[0];
		}
		return null;
	}

}
