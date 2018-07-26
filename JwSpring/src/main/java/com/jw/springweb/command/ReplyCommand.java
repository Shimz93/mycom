package com.jw.springweb.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.jw.springweb.dao.IDao;

public class ReplyCommand implements Command {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int oribId = Integer.parseInt(request.getParameter("oribId"));
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		System.out.println(oribId);
		System.out.println(bName);
		System.out.println(bTitle);
		System.out.println(bContent);
		IDao dao = new IDao();
		dao.reply(oribId,bName,bTitle,bContent);
		
	}

}
