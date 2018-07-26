package com.jw.springweb.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jw.springweb.command.Command;
import com.jw.springweb.command.ContentViewCommand;
import com.jw.springweb.command.DeleteCommand;
import com.jw.springweb.command.ListCommand;
import com.jw.springweb.command.ReplyCommand;
import com.jw.springweb.command.WriteCommand;
import com.jw.springweb.command.modifyCommand;
import com.jw.springweb.dto.boardDTO;

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
	@RequestMapping("/reply")
	public String reply(Model model, HttpServletRequest request) {
		int originbId = Integer.parseInt(request.getParameter("bId"));
//		boardDTO dto = new boardDTO(originbId);
		
//		model.addAttribute("oriDto", dto);
		model.addAttribute("oriDto", originbId);
		return "reply";
	}
	
	@RequestMapping(value="/writeOk", method=RequestMethod.POST)
	public String writeOk(Model model, HttpServletRequest request) {
		Command command = new WriteCommand();
		model.addAttribute("request", request);
		command.execute(model);
		return "redirect:list";
	}

	@RequestMapping(value="/replyOk", method=RequestMethod.POST)
	public String replyOk(Model model, HttpServletRequest request) {
		Command command = new ReplyCommand();
		model.addAttribute("request", request);
		command.execute(model);
		return "redirect:list";
	}
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(Model model, HttpServletRequest request) {
		int bId = Integer.parseInt(request.getParameter("bId"));
		String bName= request.getParameter("bName");
		String bTitle= request.getParameter("bTitle");
		String bContent= request.getParameter("bContent");
		boardDTO dto = new boardDTO(bId, bName, bTitle, bContent);
		model.addAttribute("dto",dto);
		return "modifyView";
	}
	@RequestMapping(value="/modifyOk", method=RequestMethod.POST)
	public String modifyOk(Model model, HttpServletRequest request) {
		Command command = new modifyCommand();
		model.addAttribute("request", request);
		command.execute(model);
		return "redirect:list";
	}
	
	@RequestMapping("/contentView")
	public String contentView(Model model,HttpServletRequest request) {
		Command command = new ContentViewCommand();
		model.addAttribute("request", request);
		command.execute(model);
		return "contentView";
	}
	
	@RequestMapping("/delete")
	public String delete(Model model, HttpServletRequest request) {
		Command command = new DeleteCommand();
		model.addAttribute("request",request);
		command.execute(model);
		return "redirect:list";
	}
}
