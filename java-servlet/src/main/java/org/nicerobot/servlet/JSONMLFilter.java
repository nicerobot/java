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

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author nicerobot
 * 
 */
class CharResponseWrapper extends HttpServletResponseWrapper {

	/**
	 * 
	 */
	private final CharArrayWriter output;

	/**
	 * @param response
	 */
	public CharResponseWrapper (final HttpServletResponse response) {
		super(response);
		this.output = new CharArrayWriter();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponseWrapper#getWriter()
	 */
	@Override
	public PrintWriter getWriter () {
		return new PrintWriter(this.output);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString () {
		return this.output.toString();
	}
}

/**
 * @author nicerobot
 * 
 */
public class JSONMLFilter implements Filter {

	/**
	 * 
	 */
	private FilterConfig filterConfig = null;

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy () {
		this.filterConfig = null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter (final ServletRequest request, final ServletResponse response,
			final FilterChain chain) throws IOException, ServletException {
		String contentType;
		String styleSheet;
		final String type = request.getParameter("type");
		if (type == null || type.equals("")) {
			contentType = "text/html";
			styleSheet = "/xml/html.xsl";
		} else {
			if (type.equals("xml")) {
				contentType = "text/plain";
				styleSheet = "/xml/xml.xsl";
			} else {
				contentType = "text/html";
				styleSheet = "/xml/html.xsl";
			}
		}
		response.setContentType(contentType);
		final String stylePath = this.filterConfig.getServletContext().getRealPath(styleSheet);

		final Source styleSource = new StreamSource(stylePath);

		final PrintWriter out = response.getWriter();
		final CharResponseWrapper responseWrapper =
				new CharResponseWrapper((HttpServletResponse) response);
		chain.doFilter(request, responseWrapper);

		// Get response from servlet
		final StringReader sr = new StringReader(responseWrapper.toString());
		final Source xmlSource = new StreamSource(sr);

		try {
			final TransformerFactory transformerFactory = TransformerFactory.newInstance();
			final Transformer transformer = transformerFactory.newTransformer(styleSource);
			final CharArrayWriter caw = new CharArrayWriter();
			final StreamResult result = new StreamResult(caw);
			transformer.transform(xmlSource, result);
			response.setContentLength(caw.toString().length());
			out.write(caw.toString());
		} catch (final Exception ex) {
			out.println(ex.toString());
			out.write(responseWrapper.toString());
		}

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init (final FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
