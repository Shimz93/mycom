package com.jw.springweb.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.jw.springweb.dao.IDao;

public class modifyCommand implements Command {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request= (HttpServletRequest) map.get("request");
		IDao dao = sqlSession.getMapper(IDao.class);
		int bId =Integer.parseInt(request.getParameter("bId"));
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		dao.modify(bId, bName, bTitle, bContent);
	}

}
