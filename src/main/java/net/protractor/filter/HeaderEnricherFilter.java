package net.protractor.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("headerEnricherFilter")
public class HeaderEnricherFilter implements HandlerInterceptor {

	private String clientUrl = "http://localhost:8888";


	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


		String authHeader = request.getHeader("Auth");
		if (authHeader != null) {
			response.setHeader("Auth",authHeader);
		}

		response.setHeader("Access-Control-Allow-Origin", clientUrl);
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Auth, Accept, x-requested-with, content-type");
		//response.setHeader("Access-Control-Allow-Headers", "Auth, Origin, X-Requested-With, Content-Type, Accept");

		return true;
	}


	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


	}
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

	public void setClientBaseUrl(String clientBaseUrl) {
		this.clientUrl = clientBaseUrl;
	}
}
