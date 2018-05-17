package com.project101.board.purchase.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PurchaseBoardDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
	int result;

	public PurchaseBoardDAO() {

		try {

			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {

			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}
	//수정
	public boolean isBuyWriter(int num, String id) {
		String buy_sql = "select * from PURCHASE_BOARD where PB_NO=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(buy_sql);
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			rset.next();
			if(id.equals(rset.getString("PB_WRITER"))) {
				return true;				
			}
		}catch (Exception e) {
			System.out.println("isBuyWriter 에러 : " + e);
		}finally {
			if(rset!=null) {
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
		}
		return false;	
	}
	//수정
	public boolean buyModify(PurchaseBoardBean buydata) {
		String sql = 
				"update purchase_board set PB_TITLE =?, "
			+	"PB_CONTENT = ? "
			+	"where PB_NO = ? ";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buydata.getTitle());
			pstmt.setString(2, buydata.getContent());
			pstmt.setInt(3, buydata.getNum());
			pstmt.executeUpdate();
		
		return true;
	}catch (Exception e) {
		System.out.println("buyModify 에러 : " + e);
	}finally {
		if(rset!=null) {
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
	}
	return false;
	}
	//디테일
	//조회수 PB_READCOUNT 추가
	public void setReadCountUpdate(int num) {
		String sql = "update purchase_board "
				+	"set PB_READCOUNT = PB_READCOUNT+1 "
				+	"where PB_NO = ?";
		try {
			conn = ds.getConnection();			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeQuery();
		}catch(Exception e) {
			System.out.println("setReadCountUpdate() 에러 : " + e);
		}finally {
			if(rset!=null) {
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
		}		
	}
	public PurchaseBoardBean getDetail(int num) {
		PurchaseBoardBean buydata = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(
					"select * from purchase_board where PB_NO = ?");
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				buydata = new PurchaseBoardBean();
				buydata.setNum(rset.getInt("PB_NUM"));
				buydata.setWriter(rset.getString("PB_WRITER"));
				buydata.setTitle(rset.getString("PB_TITLE"));
				buydata.setContent(rset.getString("PB_CONTENT"));
				buydata.setFile(rset.getString("PB_FILE"));	
				buydata.setPb_readcount(rset.getInt("PB_READCOUN"));
				buydata.setDate(rset.getInt("PB_DATE"));
			}
			return buydata;
		}catch (Exception e ) {
			System.out.println("getDetailAction 에러 :" + e);
		}finally {
			if(rset!=null) {
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
		}		
		return null;
	}
}
