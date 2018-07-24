package com.jw.springweb.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.jw.springweb.dao.IDao;

public class DeleteCommand implements Command {

	@Override
	public void execute(Model model) {
		Map< String, Object> map = model.asMap();
		HttpServletRequest request =(HttpServletRequest) map.get("request");
		int bId=Integer.parseInt(request.getParameter("bId"));
		IDao dao = new IDao();
		dao.delete(bId);
	}
	

}
