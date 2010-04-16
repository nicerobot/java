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
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nicerobot.util.Eterator;

/**
 * @author nicerobot
 * 
 */
@SuppressWarnings ("serial")
public class Information extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings ("unchecked")
	@Override
	public void doGet (final HttpServletRequest req_, final HttpServletResponse resp_)
			throws ServletException, IOException {
		final ServletContext ctx = getServletContext();
		final PrintWriter out = resp_.getWriter();
		out.format("<pre>\n");
		out.format("ServletName %s\n", getServletName());
		out.format("MajorVersion %s\n", ctx.getMajorVersion());
		out.format("MinorVersion %s\n", ctx.getMinorVersion());
		out.format("AUTH_TYPE %s\n", req_.getAuthType());
		out.format("DOCUMENT_ROOT %s\n", ctx.getRealPath("/"));
		out.format("PATH_INFO %s\n", req_.getPathInfo());
		out.format("PATH_TRANSLATED %s\n", req_.getPathTranslated());
		out.format("QUERY_STRING %s\n", req_.getQueryString());
		out.format("REMOTE_USER %s\n", req_.getRemoteUser());
		out.format("REQUEST_METHOD %s\n", req_.getMethod());
		out.format("SCRIPT_NAME %s\n", req_.getServletPath());
		out.format("SERVER_SOFTWARE %s\n", ctx.getServerInfo());
		out.format("</pre>");
		out.format("<h1>Headers</h1><pre>\n");
		try {
			for (final String s : new Eterator<String>(req_.getHeaderNames())) {
				out.format("%s=%s\n", s, req_.getHeader(s));
			}
		} catch (final Exception e) {}
		out.format("<h1>Cookies</h1><pre>\n");
		try {
			final Cookie[] cookies = req_.getCookies();
			if (0 != cookies.length) {
				for (final Cookie s : cookies) {
					out.format("%s\n", s);
				}
			}
		} catch (final Exception e) {}
		out.format("</pre>");
		out.format("<h1>Init Parameters</h1><pre>\n");
		try {
			for (final String s : new Eterator<String>(getInitParameterNames())) {
				out.format("%s=%s\n", s, getInitParameter(s));
			}
		} catch (final Exception e) {}
		out.format("</pre>");
		Information.print(req_, resp_);
	}

	@SuppressWarnings ("unchecked")
	public static void print (final ServletRequest req_, final ServletResponse resp_)
			throws IOException {
		final PrintWriter out = resp_.getWriter();
		out.format("<pre>\n");
		out.format("CONTENT_LENGTH %s\n", req_.getContentLength());
		out.format("CONTENT_TYPE %s\n", req_.getContentType());
		out.format("REMOTE_ADDR %s\n", req_.getRemoteAddr());
		out.format("REMOTE_HOST %s\n", req_.getRemoteHost());
		out.format("SERVER_NAME %s\n", req_.getServerName());
		out.format("SERVER_PORT %d\n", req_.getServerPort());
		out.format("SERVER_PROTOCOL %s\n", req_.getProtocol());
		out.format("</pre>");
		out.format("<h1>Parameters</h1><pre>\n");
		try {
			for (final String s : new Eterator<String>(req_.getParameterNames())) {
				out.format("%s=%s\n", s, req_.getParameter(s));
			}
		} catch (final Exception e) {

		}
		out.format("</pre>");
		out.format("<h1>Attributes</h1><pre>\n");
		try {
			for (final String s : new Eterator<String>(req_.getAttributeNames())) {
				out.format("%s=%s\n", s, req_.getAttribute(s));
			}
		} catch (final Exception e) {}
		out.format("</pre>");
	}
}
