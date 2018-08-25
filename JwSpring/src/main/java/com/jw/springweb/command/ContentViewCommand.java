package com.jw.springweb.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.jw.springweb.dao.IDao;
import com.jw.springweb.dto.boardDTO;

public class ContentViewCommand implements Command {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int inpbId = Integer.parseInt((String) request.getParameter("bId"));
		IDao dao = sqlSession.getMapper(IDao.class);
		boardDTO dto =dao.contentView(inpbId);
		model.addAttribute("dto", dto);
		
	}

}
