package net.protractor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PingController {

	private static final String PING_RESPONSE = "alive";

	@RequestMapping(value = "/ping", method = { RequestMethod.GET, RequestMethod.HEAD })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String ping() {
		return PING_RESPONSE;
	}

}