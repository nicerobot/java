package org.nicerobot.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Useful for JUnit Parameterized tests.
 * 
 * @author nicerobot
 * 
 */
public final class FileTester {

	/**
	 * 
	 */
	private static String directory = "";

	/**
	 * Every file in the root directory.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Collection<Object[]> data () throws FileNotFoundException {
		return FileTester.data(".");
	}

	/**
	 * @param class_
	 * @param filter_
	 * @param directory_
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Collection<Object[]> data (Class<?> class_, FileFilter filter_, String directory_)
			throws FileNotFoundException {
		if (null == class_) {
			class_ = Class.class;
		}
		if (null == directory_) {
			directory_ = FileTester.directory;
		}
		if (null == filter_) {
			filter_ = FileTester.makeFilter();
		}
		final File testdir = Find.file(class_, directory_);
		final Collection<Object[]> data = new ArrayList<Object[]>();
		for (final File f : testdir.listFiles(filter_)) {
			data.add(new Object[] { f });
		}

		return data;
	}

	/**
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Collection<Object[]> data (final Class<?> class_, final Pattern pattern_,
			final String directory_) throws FileNotFoundException {
		return FileTester.data(class_, FileTester.makeFilter(pattern_), directory_);
	}

	/**
	 * @param class_
	 * @param string_
	 * @param string2_
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Collection<Object[]> data (final Class<?> class_, final String contains_,
			final String directory_) throws FileNotFoundException {
		return FileTester.data(class_, Pattern.compile(contains_), directory_);
	}

	/**
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Collection<Object[]> data (final Pattern pattern_) throws FileNotFoundException {
		return FileTester.data(pattern_, FileTester.directory);
	}

	/**
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Collection<Object[]> data (final Pattern pattern_, final String directory_)
			throws FileNotFoundException {
		return FileTester.data(Class.class, pattern_, directory_);
	}

	/**
	 * @param contains_
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Collection<Object[]> data (final String contains_) throws FileNotFoundException {
		return FileTester.data(Pattern.compile(contains_));
	}

	/**
	 * @param class_
	 * @param contains_
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Collection<Object[]> data (final String contains_, final String directory_)
			throws FileNotFoundException {
		return FileTester.data(FileTester.class, contains_, directory_);
	}

	/**
	 * @param pattern_
	 * @return
	 */
	public static FileFilter makeFilter () {
		return FileTester.makeFilter(".");
	}

	/**
	 * @param pattern_
	 * @return
	 */
	public static FileFilter makeFilter (final Pattern pattern_) {
		return new FileFilter() {

			@Override
			public boolean accept (final File file_) {
				return file_.isFile() && !file_.isDirectory() && file_.canRead()
						&& pattern_.matcher(file_.getAbsolutePath()).find();
			}
		};
	}

	/**
	 * @param pattern_
	 * @return
	 */
	public static FileFilter makeFilter (final String pattern_) {
		return FileTester.makeFilter(Pattern.compile(pattern_));
	}

}
