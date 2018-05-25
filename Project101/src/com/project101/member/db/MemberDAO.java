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

	public int isId(String id, String password) {

		try {
			System.out.println("여기1");
			conn = ds.getConnection();
			System.out.println("getConnection");
			System.out.println("여기2");
			pstmt = conn.prepareStatement("select id, password from member where id=?");
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			System.out.println("여기3");
			if (rset.next()) {
				System.out.println("여기4");
				if (rset.getString("password").equals(password)) {
					System.out.println("여기5");
					result = 1; // 아이디와 비밀번호가 일치하는 경우
				} else {
					System.out.println("여기6");
					result = 0; // 비밀번호가 일치하지 않는 경우
				}
			} else {
				System.out.println("여기7");
				result = -1; // 아이디가 없는 경우
			}
		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return result;
	}
	
	//마이페이지
	public Member member_info(String id) {
		Member m = new Member();
		try {
			conn = ds.getConnection();
			
			String sql = "select * from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m.setId(rset.getString("id"));
				m.setPassword(rset.getString(2));
				m.setName(rset.getString(3));
				m.setAge(rset.getInt(4));
				m.setGender(rset.getString(5));
				m.setEmail(rset.getString(6));
				m.setPhonenum(rset.getInt(7));
				m.setSchool(rset.getString(8));
				m.setDepartment(rset.getString(9));
				m.setStudentid(rset.getString(10));
				m.setAddress(rset.getString(11));
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("member_info 오류 :" + e);
		}finally {			
			}if(rset!=null) {
				try {
					rset.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		return m;
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
