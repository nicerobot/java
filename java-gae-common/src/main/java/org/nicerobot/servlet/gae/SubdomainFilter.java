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

package org.nicerobot.servlet.gae;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author nicerobot
 * 
 */
public class SubdomainFilter implements Filter {
	private static Integer count = 1;
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
		if (this.filterConfig == null) {
			return;
		}
		SubdomainFilter.count =
				(Integer) this.filterConfig.getServletContext().getAttribute("hitCounter");
		if (null == SubdomainFilter.count) {
			SubdomainFilter.count = 1;
		} else {
			SubdomainFilter.count++;
		}
		this.filterConfig.getServletContext().setAttribute("hitCounter", SubdomainFilter.count);

		chain.doFilter(request, response);
		response.getWriter().print("\n" + SubdomainFilter.count);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init (final FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
