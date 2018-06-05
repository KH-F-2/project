package com.project101.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.project101.bean.Member;
import com.project101.bean.SellBoardBean;

public class MemberDAO {

	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
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
			conn = ds.getConnection();
			System.out.println("getConnection");
			pstmt = conn.prepareStatement("select id, password from member12 where id=?");
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				if (rset.getString("password").equals(password)) {
					result = 1; // 아이디와 비밀번호가 일치하는 경우
				} else {
					result = 0; // 비밀번호가 일치하지 않는 경우
				}
			} else {
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
	public Member member_info(String email) {
		Member m = new Member();
		try {
			conn = ds.getConnection();
			
			String sql = "select * from member where email = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m.setEmail(rset.getString("email"));
				m.setPassword(rset.getString(2));
				m.setNickname(rset.getString(3));
				m.setPhone(rset.getString(4));
				m.setPost(rset.getString(5));
				m.setAddress(rset.getString(6));
				m.setDetailaddress(rset.getString(7));
			
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
	
	public int idcheck(String email) {
	      int result = 1;
	      try {
	         conn=ds.getConnection();
	         pstmt = conn.prepareStatement("select * from member where email = ?");
	         pstmt.setString(1, email);
	         ResultSet rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	            result = -1;
	         }
	         
	      } catch(Exception e) {
	         e.printStackTrace();
	      } finally{
	          if(rset!=null)
	             try{
	                rset.close(); 
	             } catch(SQLException ex){ex.printStackTrace();}
	          if(pstmt!=null)
	             try{
	                pstmt.close(); 
	             } catch(SQLException ex){ex.printStackTrace();}
	          if(conn!=null)
	             try{
	                conn.close();
	             } catch(SQLException ex){ex.printStackTrace();}
	          }
	      
	      return result;
	   }

	public int insert(Member m) {
		try {
			conn = ds.getConnection();
			
			String sql = "insert into member values(?,?,?,?,?,?,?,? ) ";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, m.getEmail() + "@" + m.getDomain());
			pstmt.setString(2, m.getEmailcheck());
			pstmt.setString(3, m.getPassword());
			pstmt.setString(4,  m.getNickname());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getPost());
			pstmt.setString(7, m.getAddress());
			pstmt.setString(8, m.getDetailaddress());
			
			
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
			if(conn != null)
				try {
					conn.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}
	public List<SellBoardBean> getBoardList(int page, int limit, String SB_WRITER) {
		List<SellBoardBean> list=new ArrayList<SellBoardBean>();
		int startrow=(page-1)*limit+1;
		int endrow=startrow+limit-1;
		try {
			conn=ds.getConnection();
			String sql="select * from "
					+ "(select rownum rnum, SB_NO, SB_WRITER, SB_TITLE, "
					+ "SB_READCOUNT, SB_DATE from "
					+ "(select * from SELL_BOARD order by SB_NO desc)) "
					+ " where rnum>=? and rnum<=? and SB_WRITER = ? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			pstmt.setString(3, SB_WRITER);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				SellBoardBean boardBean=new SellBoardBean();
				boardBean.setSB_NO(rset.getInt("SB_NO"));
				boardBean.setSB_WRITER(rset.getString("SB_WRITER"));
				boardBean.setSB_TITLE(rset.getString("SB_TITLE"));
				boardBean.setSB_DATE(rset.getDate("SB_DATE"));
				boardBean.setSB_READCOUNT(rset.getInt("SB_READCOUNT"));
				list.add(boardBean);
			}
			return list;
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					if(pstmt!=null)	pstmt.close();
					if(conn!=null)		conn.close();
					if(rset!=null)		rset.close();
				}catch(Exception e) {e.printStackTrace();}
			}
		
		return list;
	}	// getBoardList() ----------
	
	public SellBoardBean getDetail(int num) {
		SellBoardBean sellboard=new SellBoardBean();
		try {
			conn=ds.getConnection();
			String sql="select * from SELL_BOARD where SB_NO=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				sellboard.setSB_NO(rset.getInt("SB_NO"));
				sellboard.setSB_WRITER(rset.getString("SB_WRITER"));
				sellboard.setSB_PDATE(rset.getDate("SB_PDATE"));
				sellboard.setSB_MDATE(rset.getDate("SB_MDATE"));
				sellboard.setSB_TITLE(rset.getString("SB_TITLE"));
				sellboard.setSB_CONTENT(rset.getString("SB_CONTENT"));
				sellboard.setSB_PRICE(rset.getInt("SB_PRICE"));
				sellboard.setSB_DATE(rset.getDate("SB_DATE"));
				sellboard.setSB_READCOUNT(rset.getInt("SB_READCOUNT"));
				return sellboard;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset!=null)	rset.close();
				if(pstmt!=null)	pstmt.close();
				if(conn!=null)		conn.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		
		return null;
	}	// getDetail() -----------
	
	public String findID(String nickname, String phone) {
		  String result = null;
		  
	      try {
	         conn = ds.getConnection();
	         pstmt = conn.prepareStatement("select * from member where nickname = ? and phone = ? ");
	         pstmt.setString(1, nickname);
	         pstmt.setString(2, phone);
	         
	         ResultSet rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	            result = rs.getString("EMAIL");
	         }
	         
	      } catch(Exception e) {
	         e.printStackTrace();
	      } finally{
	          if(rset!=null)
	             try{
	                rset.close(); 
	             } catch(SQLException ex){ex.printStackTrace();}
	          if(pstmt!=null)
	             try{
	                pstmt.close(); 
	             } catch(SQLException ex){ex.printStackTrace();}
	          if(conn!=null)
	             try{
	                conn.close();
	             } catch(SQLException ex){ex.printStackTrace();}
	          }
	      
	      return result;
	}
	
	public String findPW(String email, String nickname, String phone) {
		String result = null;
		try {
	         conn = ds.getConnection();
	         pstmt = conn.prepareStatement("select * from member12 where email = ? and nickname = ? and phone = ? ");
	         pstmt.setString(1, email);
	         pstmt.setString(2, nickname);
	         pstmt.setString(3, phone );
	         
	         ResultSet rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	            result = rs.getString("PASSWORD");
	         }
	         
	      } catch(Exception e) {
	         e.printStackTrace();
	      } finally{
	          if(rset!=null)
	             try{
	                rset.close(); 
	             } catch(SQLException ex){ex.printStackTrace();}
	          if(pstmt!=null)
	             try{
	                pstmt.close(); 
	             } catch(SQLException ex){ex.printStackTrace();}
	          if(conn!=null)
	             try{
	                conn.close();
	             } catch(SQLException ex){ex.printStackTrace();}
	          }
	      
	      return result;
	}
	
	public int update(Member m) {
		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = "update member set password= ? , nickname= ? , phone= ? , post= ? , address = ? , subaddress = ?  where email= ?  ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  m.getNickname());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getPost());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getDetailaddress());
			pstmt.setString(6, m.getEmail());
			
			result = pstmt.executeUpdate();
		}catch(Exception e){
		
			e.printStackTrace();
		}finally{
			if(pstmt != null)
				try{
					pstmt.close();
				}catch(SQLException ex){
					ex.printStackTrace();
				}
			if(conn != null)
				try{
					conn.close();
				}catch(SQLException ex){ex.printStackTrace();}
		}
		return result;
		
	}
}
