package net.protractor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = { RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.GET })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String search(@RequestParam("username") String username) {
		return authToken();
	}

	private String authToken() {
		String token = UUID.randomUUID().toString().replaceAll("-", "");
		return "{\"token\" : \"" + token + "\"}";
	}
}
