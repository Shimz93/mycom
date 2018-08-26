package com.jw.springweb.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jw.springweb.dao.IDao;
import com.jw.springweb.dto.boardDTO;

@Controller
public class BController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(HttpServletRequest request) {
		String email=request.getParameter("inputEmail");
		String password=request.getParameter("inputPassword");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		try {
			if (dao.login(email, password)!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("id", email);
				return "home";
			}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return "login";
		
		}
	
	@RequestMapping("/list")
	public String list(Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		model.addAttribute("list", dao.list());
	return "board";	
	}
	
	@RequestMapping("/write")
	public String write() {
		return "write";
	}
	@RequestMapping("/reply")
	public String reply(Model model, HttpServletRequest request) {
		IDao dao = sqlSession.getMapper(IDao.class);
		int bId = Integer.parseInt(request.getParameter("bId"));
		model.addAttribute("dto",dao.replyInfo(bId));
		return "reply";
	}
	
	@RequestMapping(value="/writeOk", method=RequestMethod.POST)
	public String writeOk(Model model, HttpServletRequest request) {
		IDao dao = sqlSession.getMapper(IDao.class);
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		dao.write(bName, bTitle, bContent);
		return "redirect:list";
	}

	@RequestMapping(value="/replyOk", method=RequestMethod.POST)
	public String replyOk(Model model, HttpServletRequest request) {
		IDao dao = sqlSession.getMapper(IDao.class);
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		int bGroup= Integer.parseInt(request.getParameter("oribGroup"));
		int bStep= Integer.parseInt(request.getParameter("oribStep"));
		int bIndent= Integer.parseInt(request.getParameter("oribIndent"));
		dao.reply(bName,bTitle,bContent,bGroup,bStep,bIndent);
		
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
		IDao dao = sqlSession.getMapper(IDao.class);
		int bId =Integer.parseInt(request.getParameter("bId"));
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		dao.modify( bName, bTitle, bContent,bId);
		return "redirect:list";
	}
	
	@RequestMapping("/contentView")
	public String contentView(Model model,HttpServletRequest request) {
		IDao dao = sqlSession.getMapper(IDao.class);
		int inpbId = Integer.parseInt(request.getParameter("bId"));
		dao.upHit(inpbId);
		model.addAttribute("dto",dao.contentView(inpbId));
		return "contentView";
	}
	
	@RequestMapping("/delete")
	public String delete(Model model, HttpServletRequest request) {
		IDao dao = sqlSession.getMapper(IDao.class);
		int bId=Integer.parseInt(request.getParameter("bId"));
		dao.delete(bId);
		return "redirect:list";
	}
}
