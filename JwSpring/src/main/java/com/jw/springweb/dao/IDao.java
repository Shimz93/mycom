package com.jw.springweb.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.jw.springweb.dto.boardDTO;
import com.jw.springweb.dto.loginDTO;
import com.jw.springweb.util.Constant;

public class IDao {

	JdbcTemplate template=null;
	
	public IDao() {
	 template=Constant.template;
	}
	
	public loginDTO login(final String email, final String password) {
		String query = "select * from member where email='"+email+"' AND password='"+password+"'";
		return template.queryForObject(query, new BeanPropertyRowMapper<loginDTO>(loginDTO.class));
	}

	public ArrayList<boardDTO> list(){
		String query = "select * from mvc_board order by bgroup desc, bStep asc ";
		return (ArrayList<boardDTO>) template.query(query, new BeanPropertyRowMapper<boardDTO>(boardDTO.class));
	}
	
	public void reply(final int oribId, final String bName, final String bTitle, final String bContent) {
		String query1 ="select * from mvc_board where bId="+oribId;
		String query2 ="insert into MVC_BOARD values(MVC_BOARD_SEQ.NEXTVAL,?,?,?,Sysdate,0,?,?,?)";
		final boardDTO dto =template.queryForObject(query1, new BeanPropertyRowMapper<boardDTO>(boardDTO.class));
		template.update(query2, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				pstmt.setInt(4, dto.getbGroup());
				pstmt.setInt(5, dto.getbStep()+1);
				pstmt.setInt(6, dto.getbIndent()+1);			}
		});
	}
	
	public void write(final String bName,final String bTitle, final String bContent) {
		String query = "insert into MVC_BOARD values(MVC_BOARD_SEQ.NEXTVAL,?,?,?,Sysdate,0,mvc_board_seq.currval,0,0)";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
				
			}
		});
	}
		public void modify(final int bId,final String bName,final String bTitle, final String bContent) {
			String query = "update MVC_BOARD set bName=?, bTitle=?, bContent=? where bId=?";
			template.update(query, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					pstmt.setString(1, bName);
					pstmt.setString(2, bTitle);
					pstmt.setString(3, bContent);
					pstmt.setInt(4, bId);
				}
			});
	}
	
	public boardDTO contentView(int inpbId) {
		upHit(inpbId);
		String query = "select * from mvc_board where bId="+inpbId;
		return template.queryForObject(query, new BeanPropertyRowMapper<boardDTO>(boardDTO.class));
	}
	private void upHit(int id) {
		String query= "update mvc_board set bHit=bHit+1 where bId="+id;
		template.update(query);
	}
	
	public void delete(int id) {
		String query = "delete from mvc_board where bId="+id;
		template.update(query);
		
	}
}
