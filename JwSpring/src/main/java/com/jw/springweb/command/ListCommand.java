package com.jw.springweb.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.jw.springweb.dao.IDao;
import com.jw.springweb.dto.boardDTO;

public class ListCommand implements Command {

	@Override
	public void execute(Model model) {
		IDao dao = new IDao();
		ArrayList<boardDTO> dtos =dao.list();
		model.addAttribute("list", dtos);
	}

}
