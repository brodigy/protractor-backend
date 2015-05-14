package net.protractor.controller;

import net.protractor.model.Token;
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
	public void search(@RequestParam("username") String username) {

	}

}
