package com.jw.springweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
			rs.next();
			String dbEmail=rs.getString("email");
			String dbPassword =rs.getString("password");
			dto = new loginDTO(dbEmail, dbPassword);
			
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

}
