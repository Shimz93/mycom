package com.jw.springweb.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.jw.springweb.dao.IDao;

public class ReplyCommand implements Command {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		int bGroup= Integer.parseInt(request.getParameter("oribGroup"));
		int bStep= Integer.parseInt(request.getParameter("oribStep"));
		int bIndent= Integer.parseInt(request.getParameter("oribIndent"));
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.reply(bName,bTitle,bContent,bGroup,bStep,bIndent);
		
	}

}
