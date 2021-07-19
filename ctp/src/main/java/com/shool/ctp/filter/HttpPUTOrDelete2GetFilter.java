package com.shool.ctp.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 请求转发时，如果请求方法是PUT|DELETE，就将请求方法强制的转成GET
 * @author Administrator
 *
 */
public class HttpPUTOrDelete2GetFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) arg0;
		String method = req.getMethod().toUpperCase();
		if("PUT".equals(method) || "DELETE".equals(method)) {
			method = "GET";
			HttpServletRequestWrapper wrapper = new HttpMethodRequestWrapper(req, method);
			chain.doFilter(wrapper, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	
	
	private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {

		private final String method;

		public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
			super(request);
			this.method = method.toUpperCase(Locale.ENGLISH);
		}
		
		@Override
		public String getMethod() {
			return this.method;
		}
	}
}
