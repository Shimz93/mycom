package com.jw.springweb.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.jw.springweb.dao.IDao;
import com.jw.springweb.dto.loginDTO;

public class LoginCommand implements Command {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String email=(String) request.getAttribute("inputEmail");
		String password =(String)request.getAttribute("inputPassword");
		IDao dao = new IDao();
		loginDTO dto=dao.login(email,password);
		if (dto==null) {
			System.out.println("로그인 정보 없음");
		}else {
		HttpSession session = request.getSession();
		session.setAttribute("id", dto);
			System.out.println("정보 세션에 올라감");
		}
	}

}
