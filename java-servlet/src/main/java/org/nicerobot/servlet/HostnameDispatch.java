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

package org.nicerobot.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author nicerobot
 * 
 */
@SuppressWarnings ("serial")
public class HostnameDispatch extends HttpServlet {

	/**
	 * 
	 */
	private static Pattern IPPattern = Pattern.compile("((?:[0-9]{1,3}\\.){3}[0-9]{1,3})");

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service (final HttpServletRequest req_, final HttpServletResponse resp_)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dispatch = HostnameDispatch.findServlet(req_.getServerName());
		if (null == dispatch) {
			dispatch = "Main";
		}
		final RequestDispatcher dispatcher = getServletContext().getNamedDispatcher(dispatch);
		if (null != dispatcher) {
			dispatcher.forward(req_, resp_);
		} else {
			resp_.getWriter().print(req_.getServerName());
		}
	}

	/**
	 * @param serverName
	 * @return
	 */
	public static String findServlet (final String serverName) {
		if ("localhost".equalsIgnoreCase(serverName)
				|| HostnameDispatch.IPPattern.matcher(serverName).matches()) {
			return null;
		}
		Class<?> clz = null;
		for (String name : HostnameDispatch.findServletNames(serverName)) {
			name += ".servlet.Main";
			try {
				if (null != (clz = Class.forName(name))) {
					if (clz.isAssignableFrom(HostnameDispatch.class)
							|| HostnameDispatch.class.isAssignableFrom(clz)) {
						continue;
					}
					final Object servlet = clz.newInstance();
					if (servlet instanceof HttpServlet) {
						return name;
					}
				}
			} catch (final ClassNotFoundException e) {
				//
			} catch (final InstantiationException e) {
				//
			} catch (final IllegalAccessException e) {
				//
			}
		}
		return null;
	}

	/**
	 * @param serverName
	 * @return
	 */
	public static List<String> findServletNames (final String serverName) {
		final List<String> parts = new ArrayList<String>(Arrays.asList(serverName.split("[.]")));
		if (1 == parts.size()) {
			return parts;
		}
		Collections.reverse(parts);
		final List<String> names = new ArrayList<String>(1);
		names.add(String.format("%s.%s", parts.get(0), parts.get(1)));
		if (2 >= parts.size()) {
			return names;
		}
		parts.remove(0);
		parts.remove(0);
		int i = 0;
		for (final String name : parts) {
			names.add(String.format("%s.%s", names.get(i++), name));
		}
		Collections.reverse(names);
		return names;
	}
}
