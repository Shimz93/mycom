package com.jw.springweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.jw.springweb.dto.boardDTO;
import com.jw.springweb.dto.loginDTO;

public class IDao {

	DataSource dataSource;
	
	public IDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
	
	public loginDTO login(String email, String password) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		loginDTO dto =null;
		
		try {
			conn = dataSource.getConnection();
			pstmt =conn.prepareStatement("select * from member where email=? AND password=?");
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			String dbEmail=rs.getString("email");
			String dbPassword =rs.getString("password");
			dto = new loginDTO(dbEmail, dbPassword);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
		return dto;
	}

	public ArrayList<boardDTO> list(){
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<boardDTO> dtos = new ArrayList<boardDTO>();
		boardDTO dto = null;
		
		try {
			conn= dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from mvc_board");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle= rs.getString("bTitle");
				String bContent= rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				dto = new boardDTO(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				System.out.println(dto);
				dtos.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
			conn.close();
			rs.close();
			pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	public void write(String bName, String bTitle, String bContent) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int write=0;
		try {
			conn = dataSource.getConnection();
			pstmt= conn.prepareStatement("insert into MVC_BOARD values(MVC_BOARD_SEQ.NEXTVAL,?,?,?,Sysdate,0,0,0,0)");
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			write=pstmt.executeUpdate();
			
			if (write!=0) {
				System.out.println("쿼리실행_게시글정보등록 완료");
			}else {System.out.println("게시글등록실패");}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		public void modify(int bId,String bName, String bTitle, String bContent) {
			Connection conn=null;
			PreparedStatement pstmt=null;
			int write=0;
			try {
				conn = dataSource.getConnection();
				pstmt= conn.prepareStatement("update MVC_BOARD set bName=?, bTitle=?, bContent=? where bId=?");
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				pstmt.setInt(4, bId);
				write=pstmt.executeUpdate();
				
				if (write!=0) {
					System.out.println("쿼리실행_게시글정보수정 완료");
				}else {System.out.println("게시글수정실패");}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}
	
	public boardDTO contentView(int inpbId) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		boardDTO dto= null;
		try {
			conn = dataSource.getConnection();
			pstmt =conn.prepareStatement("select * from mvc_board where bId=?");
			pstmt.setInt(1, inpbId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle= rs.getString("bTitle");
				String bContent= rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				dto = new boardDTO(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				System.out.println(dto);
			}
			
		} catch (SQLException e) {
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	public void delete(int id) {
		Connection conn=null;
		PreparedStatement pstmt =null;
		
		try {
			conn = dataSource.getConnection();
			System.out.println(id);
			pstmt =conn.prepareStatement("delete from mvc_board where bId=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
