package com.jw.springweb.command;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.jw.springweb.dao.IDao;
import com.jw.springweb.dto.boardDTO;

public class ListCommand implements Command {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void execute(Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		ArrayList<boardDTO> dtos =dao.list();
		model.addAttribute("list", dtos);
	}

}
