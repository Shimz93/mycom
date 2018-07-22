package com.jw.springweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jw.springweb.command.Command;
import com.jw.springweb.command.LoginCommand;

@Controller
public class LoginController {

	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		Command command = new LoginCommand();
		command.execute(model);
		return "home";
	}
	
}
