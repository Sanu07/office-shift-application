package com.test.shiftapplication.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignOnFilter implements Filter {
	private ArrayList<String> urlList;

	/**
	 * Default constructor.
	 */
	public SignOnFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getServletPath();
		boolean allowedRequest = false;

		if (url.contains("loginUser") || url.contains("resources") || url.contains("logout") || url.equals("/")
				|| url.contains("register-login-form.jsp") || url.contains(".css") || url.contains(".js")
				|| url.contains(".jpg") || url.contains(".png") || url.contains("font-awesome")) {
			allowedRequest = true;
		}

		if (!allowedRequest) {
			HttpSession session = req.getSession(false);
			if (null == session || null == session.getAttribute("user-profile")) {
				res.sendRedirect("/");
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		// urlList.add("/loginUser");
		// urlList.add("resources");
	}

}
