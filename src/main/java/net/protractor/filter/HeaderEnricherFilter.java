package net.protractor.filter;

import com.sun.org.apache.xpath.internal.operations.Bool;
import net.protractor.controller.PostsController;
import net.protractor.model.Fault;
import net.protractor.model.Token;
import net.protractor.model.User;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.UUID;

@Component("headerEnricherFilter")
public class HeaderEnricherFilter implements HandlerInterceptor {

	ObjectMapper objectMapper = new ObjectMapper();

	private String clientUrl = "http://localhost:8888";

	private static ArrayList<Token> activeTokens = new ArrayList<Token>();

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		response.setHeader("Access-Control-Allow-Origin", clientUrl);
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Auth, Accept, x-requested-with, content-type");

		String authHeader = request.getHeader("Auth");
		if (authHeader != null) {
			response.setHeader("Auth", authHeader);
		} else if (request.getPathInfo().equals("/login") || request.getMethod().equals("OPTIONS")) {
			//do nothing -- postHandle will set the token
		} else {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType("application/json;charset=UTF-8");
			objectMapper.writer().writeValue(response.getWriter(), new Fault("E101", "Unauthorized access"));
			return false;
		}

		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (request.getPathInfo().equals("/login")) {
			if (validateUser(request.getParameter("email"), request.getParameter("password"))) {

				Token token = generateToken(request.getParameter("email"));
				activeTokens.add(token);
				response.setHeader("Auth", token.getToken());
				response.setContentType("application/json;charset=UTF-8");
				objectMapper.writer().writeValue(response.getWriter(), token);

			} else {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.setContentType("application/json;charset=UTF-8");
				Fault fault = new Fault("E101", "Unauthorized access");
				objectMapper.writer().writeValue(response.getWriter(), fault);
			}
		}

	}

	private Boolean validateUser(String username, String password) {

		if (username.equals("alex@endava.com") && password.equals("password") ) {
			return true;
		}

		for (User user : PostsController.users) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password))
				return true;
		}

		return false;
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

	public void setClientBaseUrl(String clientBaseUrl) {
		this.clientUrl = clientBaseUrl;
	}

	private Token generateToken(String username) {
		String tokenString = UUID.randomUUID().toString().replaceAll("-", "");
		return new Token(tokenString, username);
	}

}
