package com.jw.springweb.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.jw.springweb.dao.IDao;
import com.jw.springweb.dto.boardDTO;

public class ContentViewCommand implements Command {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int inpbId = Integer.parseInt((String) request.getParameter("bId"));
		IDao dao = new IDao();
		boardDTO dto =dao.contentView(inpbId);
		model.addAttribute("dto", dto);
		
	}

}
