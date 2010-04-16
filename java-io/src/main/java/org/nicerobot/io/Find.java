package org.nicerobot.io;

/**
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * @author nicerobot
 * 
 */
public final class Find {

	/**
	 * 
	 */
	private Find () {}

	/**
	 * @param class_
	 * @param fileName_
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File file (final Class<?> class_, final String fileName_)
			throws FileNotFoundException {
		final File file = new File(fileName_);
		if (file.canRead()) {
			return file;
		}
		final String fn = Find.fileName(class_, fileName_);
		if (null == fn) {
			return null;
		}
		return new File(fn);
	}

	/**
	 * @param fileName_
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File file (final String fileName_) throws FileNotFoundException {
		return Find.file(Find.class, fileName_);
	}

	/**
	 * @param class_
	 * @param fileName_
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String fileName (Class<?> class_, final String fileName_)
			throws FileNotFoundException {
		final File file = new File(fileName_);
		if (file.canRead()) {
			return file.getAbsolutePath();
		}
		if (null == class_) {
			class_ = Find.class;
		}
		final String[] paths = { "/", "" };
		for (final String f : paths) {
			final URL source = class_.getResource(f + fileName_);
			if (null != source) {
				return source.getFile();
			}
		}

		throw new FileNotFoundException(fileName_);
	}

	/**
	 * @param fileName_
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String fileName (final String fileName_) throws FileNotFoundException {
		return Find.fileName(Find.class, fileName_);
	}

}
