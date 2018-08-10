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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.jw.springweb.dto.boardDTO;
import com.jw.springweb.dto.loginDTO;
import com.jw.springweb.util.Constant;

public class IDao {

//	DataSource dataSource;
	JdbcTemplate template=null;
	
	public IDao() {
//		try {
//			Context context = new InitialContext();
//			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
	 template=Constant.template;
	}
	
	public loginDTO login(final String email, final String password) {
		String query = "select * from member where email='"+email+"' AND password='"+password+"'";
		return template.queryForObject(query, new BeanPropertyRowMapper<loginDTO>(loginDTO.class));
//		Connection conn=null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		loginDTO dto =null;
//		
//		try {
//			conn = dataSource.getConnection();
//			pstmt =conn.prepareStatement("select * from member where email=? AND password=?");
//			pstmt.setString(1, email);
//			pstmt.setString(2, password);
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//			String dbEmail=rs.getString("email");
//			String dbPassword =rs.getString("password");
//			dto = new loginDTO(dbEmail, dbPassword);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			try {
//				rs.close();
//				pstmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//			}
//		}
//		return dto;
	}

	public ArrayList<boardDTO> list(){
		String query = "select * from mvc_board order by bgroup desc, bStep asc ";
		return (ArrayList<boardDTO>) template.query(query, new BeanPropertyRowMapper<boardDTO>(boardDTO.class));
		
//		Connection conn=null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		ArrayList<boardDTO> dtos = new ArrayList<boardDTO>();
//		boardDTO dto = null;
//		
//		try {
//			conn= dataSource.getConnection();
//			pstmt = conn.prepareStatement("select * from mvc_board order by bgroup desc, bStep asc ");
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				int bId = rs.getInt("bId");
//				String bName = rs.getString("bName");
//				String bTitle= rs.getString("bTitle");
//				String bContent= rs.getString("bContent");
//				Timestamp bDate = rs.getTimestamp("bDate");
//				int bHit = rs.getInt("bHit");
//				int bGroup = rs.getInt("bGroup");
//				int bStep = rs.getInt("bStep");
//				int bIndent = rs.getInt("bIndent");
//				dto = new boardDTO(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
//				System.out.println(dto);
//				dtos.add(dto);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//			conn.close();
//			rs.close();
//			pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return dtos;
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
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		try {
//			conn= dataSource.getConnection();
//			pstmt = conn.prepareStatement("select * from mvc_board where bId=?");
//			pstmt.setInt(1, oribId);
//			rs=pstmt.executeQuery();
//			
//			rs.next();
//			int oriGroup = rs.getInt("bGroup");
//			int oriStep= rs.getInt("bStep");
//			int oriIndent = rs.getInt("bIndent");
//			pstmt.close();
//			rs.close();
//			
//			pstmt = conn.prepareStatement("insert into MVC_BOARD values(MVC_BOARD_SEQ.NEXTVAL,?,?,?,Sysdate,0,?,?,?)");
//			pstmt.setString(1, bName);
//			pstmt.setString(2, bTitle);
//			pstmt.setString(3, bContent);
//			pstmt.setInt(4, oriGroup);
//			pstmt.setInt(5, oriStep+1);
//			pstmt.setInt(6, oriIndent+1);
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			try {
//				pstmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
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
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		int write=0;
//		try {
//			conn = dataSource.getConnection();
//			pstmt= conn.prepareStatement("insert into MVC_BOARD values(MVC_BOARD_SEQ.NEXTVAL,?,?,?,Sysdate,0,mvc_board_seq.currval,0,0)");
//			pstmt.setString(1, bName);
//			pstmt.setString(2, bTitle);
//			pstmt.setString(3, bContent);
//			write=pstmt.executeUpdate();
//			
//			if (write!=0) {
//				System.out.println("쿼리실행_게시글정보등록 완료");
//			}else {System.out.println("게시글등록실패");}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				pstmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
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
//			Connection conn=null;
//			PreparedStatement pstmt=null;
//			int write=0;
//			try {
//				conn = dataSource.getConnection();
//				pstmt= conn.prepareStatement("update MVC_BOARD set bName=?, bTitle=?, bContent=? where bId=?");
//				pstmt.setString(1, bName);
//				pstmt.setString(2, bTitle);
//				pstmt.setString(3, bContent);
//				pstmt.setInt(4, bId);
//				write=pstmt.executeUpdate();
//				
//				if (write!=0) {
//					System.out.println("쿼리실행_게시글정보수정 완료");
//				}else {System.out.println("게시글수정실패");}
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}finally {
//				try {
//					pstmt.close();
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
	}
	
	public boardDTO contentView(int inpbId) {
		upHit(inpbId);
		String query = "select * from mvc_board where bId="+inpbId;
		return template.queryForObject(query, new BeanPropertyRowMapper<boardDTO>(boardDTO.class));
//		Connection conn =null;
//		PreparedStatement pstmt=null;
//		ResultSet rs =null;
//		boardDTO dto= null;
//		upHit(inpbId);
//		try {
//			conn = dataSource.getConnection();
//			pstmt =conn.prepareStatement("select * from mvc_board where bId=?");
//			pstmt.setInt(1, inpbId);
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				int bId = rs.getInt("bId");
//				String bName = rs.getString("bName");
//				String bTitle= rs.getString("bTitle");
//				String bContent= rs.getString("bContent");
//				Timestamp bDate = rs.getTimestamp("bDate");
//				int bHit = rs.getInt("bHit");
//				int bGroup = rs.getInt("bGroup");
//				int bStep = rs.getInt("bStep");
//				int bIndent = rs.getInt("bIndent");
//				dto = new boardDTO(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
//				System.out.println(dto);
//				
//			}
//		} catch (SQLException e) {
//		}finally {
//			try {
//				rs.close();
//				pstmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return dto;
	}
	private void upHit(int id) {
		String query= "update mvc_board set bHit=bHit+1 where bId="+id;
		template.update(query);
//		Connection conn =null;
//		PreparedStatement pstmt = null;
//		
//		try {
//		conn = dataSource.getConnection();
//		pstmt = conn.prepareStatement("update mvc_board set bHit=bHit+1 where bId=?");
//		pstmt.setInt(1, id);
//		pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				pstmt.close();
//				conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		}
	}
	
	public void delete(int id) {
		String query = "delete from mvc_board where bId="+id;
		template.update(query);
//		Connection conn=null;
//		PreparedStatement pstmt =null;
//		
//		try {
//			conn = dataSource.getConnection();
//			System.out.println(id);
//			pstmt =conn.prepareStatement("delete from mvc_board where bId=?");
//			pstmt.setInt(1, id);
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				pstmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		
	}
}
