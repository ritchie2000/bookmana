package com.edu;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 */
public class CharacterEncodingFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 处理请求乱码
		request.setCharacterEncoding("utf-8");
		
		// 处理响应乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
