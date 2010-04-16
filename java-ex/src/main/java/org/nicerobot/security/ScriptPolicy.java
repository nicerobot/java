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
package org.nicerobot.security;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.Policy;

import org.nicerobot.io.Sync;
import org.nicerobot.util.Eterator;

/**
 * @author nicerobot
 * 
 */
public class ScriptPolicy extends Policy {

	/**
	 * 
	 */
	public ScriptPolicy () {}

	public static void main (final String[] args) throws NoSuchAlgorithmException, IOException {
		ScriptPolicy.class.getClassLoader();
		/*!* /
		Policy.getInstance("ScriptPolicySpi", null);
		final SecurityManager sm = new SecurityManager();
		System.setSecurityManager(sm);
		HttpURLConnection.setFollowRedirects(true);
		System.out.format("%s\n", new File("/"));
		/*!*/
		{
			for (final URL u : new Eterator<URL>(ClassLoader.getSystemResources("ScriptPolicy.class"))) {
				Sync.out.format("%s\n", u);
			}
		}
	}
}