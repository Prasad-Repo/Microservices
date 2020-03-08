package com.microservices.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ZuulLoggingFilter extends ZuulFilter{

	private Logger logger = LoggerFactory.getLogger(this.getClass());


	
	//	This method to say if you would want to execute the filter or not.

	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		HttpServletRequest request = 
				RequestContext.getCurrentContext().getRequest();
		logger.info("request -> {} request uri -> {}", 
				request, request.getRequestURI());
		return null;
	}

	/*
	 * Should filter be happening before the request is executed or after the
	 * request has executed. Or do you want to filter only error requests that have
	 * caused an exception to happen in this specific example.
	 * 
	 */
	
	/*
	 * @Override public String filterType() { return "post"; }
	 */
	
	/*
	 * @Override public String filterType() { return "error"; }
	 */
	
	@Override
	public String filterType() {
		return "pre";
	}

	

	
	
	@Override
	public int filterOrder() {
		return 1;
	}
}
