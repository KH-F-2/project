package com.project101.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;
	
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = 
			(DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex) {
			System.out.println("DB  : " + ex);
			return;
		}
	}
	
	
	public int isId(String id, String pass) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Member m) {
		
		try {
			con = ds.getConnection();
			
			String sql = "insert into member12 values(?,?,?,?,?,?,?,?,? ) ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPass());
			pstmt.setString(3,  m.getName());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getTel());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getSchool());
			pstmt.setString(8, m.getMajor());
			pstmt.setString(9, m.getCollege());
			
			result = pstmt.executeUpdate();
			
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			result = -1;
			System.out.println( "insert 에러" );
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null)
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			if(con != null)
				try {
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}

	public int idcheck(String id) {
	      int result = 1;
	      try {
	         con=ds.getConnection();
	         pstmt = con.prepareStatement("select * from member12 where id = ?");
	         pstmt.setString(1, id);
	         ResultSet rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	            result = -1;
	         }
	         
	      } catch(Exception e) {
	         e.printStackTrace();
	      } finally{
	          if(rs!=null)
	             try{
	                rs.close(); 
	             } catch(SQLException ex){ex.printStackTrace();}
	          if(pstmt!=null)
	             try{
	                pstmt.close(); 
	             } catch(SQLException ex){ex.printStackTrace();}
	          if(con!=null)
	             try{
	                con.close();
	             } catch(SQLException ex){ex.printStackTrace();}
	          }
	      
	      return result;
	   }
	
}
