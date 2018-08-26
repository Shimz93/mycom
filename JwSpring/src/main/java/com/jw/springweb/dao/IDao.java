package com.jw.springweb.dao;

import java.util.ArrayList;

import com.jw.springweb.dto.boardDTO;
import com.jw.springweb.dto.loginDTO;

public interface IDao {
	
	//https://okky.kr/article/369669?note=1168185참고
	public loginDTO login(String email,String password);
	public ArrayList<boardDTO> list();
	public boardDTO replyInfo(int bId);
	public void reply(String bName,String bTitle, String bContent, int bGroup, int bStep, int bIndent);
	public void write( String bName, String bTitle, String bContent);	
	public void modify(String bName, String bTitle, String bContent, int bId);;
	public boardDTO contentView(int inpbId);
	void upHit(int id);
	public void delete(int id);
}
