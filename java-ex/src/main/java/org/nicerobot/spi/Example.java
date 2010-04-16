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

package org.nicerobot.spi;

import java.security.NoSuchAlgorithmException;
import java.security.PolicySpi;

import org.nicerobot.io.Sync;
import org.nicerobot.util.Iteratorable;

import sun.misc.Service;

/**
 * @author nicerobot
 * 
 */
public class Example implements ExampleSpi {
	static {
		Sync.out.stackTrace(new Throwable("Static class initialization"));
	}

	public Example () {
		Sync.out.stackTrace(new Throwable("Example constructor"));
	}

	@SuppressWarnings ("unchecked")
	public static void main (final String[] args) throws NoSuchAlgorithmException {
		for (final ExampleSpi s : new Iteratorable<ExampleSpi>(Service.providers(ExampleSpi.class))) {
			Sync.out.format("Service %s\n", s);
		}
		for (final PolicySpi s : new Iteratorable<PolicySpi>(Service.providers(PolicySpi.class))) {
			Sync.out.format("Service %s\n", s);
		}
	}
}
