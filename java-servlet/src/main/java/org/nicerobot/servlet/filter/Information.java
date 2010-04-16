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

package org.nicerobot.servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.nicerobot.util.Eterator;

/**
 * @author nicerobot
 * 
 */
public class Information implements Filter {

	private FilterConfig filterConfig;

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy () {
		this.filterConfig = null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@SuppressWarnings ("unchecked")
	@Override
	public void doFilter (final ServletRequest req_, final ServletResponse resp_,
			final FilterChain chain_) throws IOException, ServletException {
		final PrintWriter out = resp_.getWriter();
		out.format("<pre>\n");
		out.format("FilterName %s\n", this.filterConfig.getFilterName());
		out.format("</pre>\n");
		out.format("<h1>Init Parameters</h1><pre>\n");
		try {
			for (final String s : new Eterator<String>(this.filterConfig.getInitParameterNames())) {
				out.format("%s=%s\n", s, this.filterConfig.getInitParameter(s));
			}
		} catch (final Exception e) {}
		out.format("</pre>");
		org.nicerobot.servlet.Information.print(req_, resp_);
		chain_.doFilter(req_, resp_);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init (final FilterConfig config_) throws ServletException {
		this.filterConfig = config_;
	}

}
