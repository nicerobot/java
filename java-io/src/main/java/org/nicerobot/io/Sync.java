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

import java.io.PrintStream;

/**
 * @author nicerobot
 * 
 */
public final class Sync {

	/**
	 * @author nicerobot
	 * 
	 */
	public static final class err {

		private err () {}

		/**
		 * @param format_
		 * @param args_
		 */
		public static void format (final String format_, final Object... args_) {
			Sync.format(System.err, format_, args_);
		}

		/**
		 * @param e_
		 */
		public static void stackTrace (final Throwable e_) {
			Sync.stackTrace(System.err, e_);
		}

	}

	/**
	 * @author nicerobot
	 * 
	 */
	public static final class out {

		private out () {}

		/**
		 * @param format_
		 * @param args_
		 */
		public static void format (final String format_, final Object... args_) {
			Sync.format(System.out, format_, args_);
		}

		/**
		 * @param e_
		 */
		public static void stackTrace (final Throwable e_) {
			Sync.stackTrace(System.out, e_);
		}

	}

	private Sync () {}

	/**
	 * @param out_
	 * @param format_
	 * @param args_
	 */
	public static void format (final PrintStream out_, final String format_, final Object... args_) {
		out_.format(format_, args_);
	}

	/**
	 * @param out_
	 * @param args_
	 */
	public static void print (final PrintStream out_, final Object... args_) {
		synchronized (out_) {
			out_.print(args_);
		}
	}

	/**
	 * @param out_
	 * @param e_
	 */
	public static void stackTrace (final PrintStream out_, final Throwable e_) {
		synchronized (out_) {
			e_.printStackTrace();
		}

	}

}
