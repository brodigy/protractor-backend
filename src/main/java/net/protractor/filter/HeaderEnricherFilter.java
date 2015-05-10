package net.protractor.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("headerEnricherFilter")
public class HeaderEnricherFilter implements HandlerInterceptor {

	private String clientBaseUrl = "http://localhost:8888";


	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


		String authHeader = request.getHeader("Auth");
		if (authHeader != null) {
			response.setHeader("Auth",authHeader);
		}

		response.setHeader("Access-Control-Allow-Origin", clientBaseUrl);
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "60");
		response.setHeader("Access-Control-Allow-Headers", "Auth, Origin, X-Requested-With, Content-Type, Accept");
		response.setHeader("Content-Type", "application/json;charset=UTF-8");

		return true;
	}


	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


	}
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

	public void setClientBaseUrl(String clientBaseUrl) {
		this.clientBaseUrl = clientBaseUrl;
	}
}
