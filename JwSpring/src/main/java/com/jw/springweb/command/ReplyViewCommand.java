package com.jw.springweb.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.jw.springweb.dao.IDao;
import com.jw.springweb.dto.boardDTO;

public class ReplyViewCommand implements Command{


	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		int bId = Integer.parseInt(request.getParameter("bId"));
		IDao dao = sqlSession.getMapper(IDao.class);
		boardDTO dto =dao.replyInfo(bId);
		model.addAttribute("dto",dto);
	}

}
