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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestException;
import java.util.Collection;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author nicerobot
 * 
 */
@RunWith (Parameterized.class)
public class DigestedSourceTest {

	static {
		System.setProperty("java.util.logging.config.file", "target/test-classes/logging.properties");
	}

	private final File file;

	public DigestedSourceTest (final File file) {
		this.file = file;
	}

	@Before
	public void setUp () throws Exception {}

	@After
	public void tearDown () throws Exception {}

	@Test
	public void testFile () throws DigestException, IOException {
		if (Pattern.matches(".*/fail-.*", this.file.toString())) {
			try {
				new DigestedSource(this.file);
				Assert.fail("Should throw exception: " + this.file);
			} catch (final Exception e) {
				return;
			}
		} else {
			new DigestedSource(this.file);
		}

	}

	@Parameters
	public static Collection<Object[]> data () throws FileNotFoundException {
		return FileTester.data("t[xs]t$", "tests");
	}

	@BeforeClass
	public static void setUpBeforeClass () throws Exception {}

	@AfterClass
	public static void tearDownAfterClass () throws Exception {}
}
