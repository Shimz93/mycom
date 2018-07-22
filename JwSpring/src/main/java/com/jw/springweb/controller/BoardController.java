package com.jw.springweb.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jw.springweb.command.Command;
import com.jw.springweb.command.ListCommand;
import com.jw.springweb.command.WriteCommand;

@Controller
public class BoardController {

	@RequestMapping("/list")
	public String list(Model model) {
	Command command = new ListCommand();
	command.execute(model);
	return "board";	
	}
	
	@RequestMapping("/write")
	public String write(Model model) {
		return "write";
	}
	
	@RequestMapping(value="/writeOk", method=RequestMethod.POST)
	public String writeOk(Model model, HttpServletRequest request) {
		Command command = new WriteCommand();
		model.addAttribute("request", request);
		command.execute(model);
		return "redirect:list";
	}
}
