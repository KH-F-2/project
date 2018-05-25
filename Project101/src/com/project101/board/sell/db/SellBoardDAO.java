package com.project101.board.sell.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SellBoardDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
	int result;

	public SellBoardDAO() {

		try {

			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {

			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}	// SellBoardDAO() --------
	
	
	public int getListCount() {
		try {
			conn=ds.getConnection();
			String sql="SELECT COUNT(*) FROM SELL_BOARD";
			pstmt=conn.prepareStatement(sql);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) 
				result=rset.getInt(1);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset!=null)	rset.close();
				if(pstmt!=null)	pstmt.close();
				if(conn!=null)		conn.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		return result;
	}	// getListCount() -------
	
	public int getNextBoardNo() {
		int num=0;
		String sql="";
		try {
			conn=ds.getConnection();
			sql="select max(SB_NO) from SELL_BOARD";
			pstmt=conn.prepareStatement(sql);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				num=rset.getInt(1)+1;
			}
			else {
				num=1;
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
		return num;
	}
	
	public int boardInsert(SellBoardBean sellboard) {
		String sql="";
		try {
			conn=ds.getConnection();
			
			sql="insert into SELL_BOARD "
					+ "(SB_NO, SB_WRITER, SB_PDATE, SB_TITLE, "
					+ "SB_CONTENT, SB_PRICE, SB_DATE, SB_READCOUNT) "
					+ "values(?, ?, ?, ?, ?, ?, sysdate, ?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, sellboard.getSB_NO());
			pstmt.setString(2, sellboard.getSB_WRITER());
			pstmt.setDate(3, sellboard.getSB_PDATE());
			pstmt.setString(4, sellboard.getSB_TITLE());
			pstmt.setString(5, sellboard.getSB_CONTENT());
			pstmt.setInt(6, sellboard.getSB_PRICE());
			pstmt.setInt(7, 0);
			
			result=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset!=null)	rset.close();
				if(pstmt!=null)	pstmt.close();
				if(conn!=null)		conn.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		
		return result;
	}	 // boardInsert() ----------
	
	
	public List<SellBoardBean> getBoardList(int page, int limit) {
		List<SellBoardBean> list=new ArrayList<SellBoardBean>();
		int startrow=(page-1)*limit+1;
		int endrow=startrow+limit-1;
		try {
			conn=ds.getConnection();
			String sql="select * from "
					+ "(select rownum rnum, SB_NO, SB_WRITER, SB_TITLE, "
					+ "SB_READCOUNT, SB_DATE from "
					+ "(select * from SELL_BOARD order by SB_NO desc)) "
					+ "where rnum>=? and rnum<=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				SellBoardBean sellboard=new SellBoardBean();
				sellboard.setSB_NO(rset.getInt("SB_NO"));
				sellboard.setSB_WRITER(rset.getString("SB_WRITER"));
				sellboard.setSB_TITLE(rset.getString("SB_TITLE"));
				sellboard.setSB_DATE(rset.getDate("SB_DATE"));
				sellboard.setSB_READCOUNT(rset.getInt("SB_READCOUNT"));
				list.add(sellboard);
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
		
		return null;
	}	// getBoardList() ----------


	public void setReadCountUpdate(int num) {
		String sql="";
		try {
			conn=ds.getConnection();
			sql="update SELL_BOARD set SB_READCOUNT=SB_READCOUNT+1 where SB_NO=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset!=null)	rset.close();
				if(pstmt!=null)	pstmt.close();
				if(conn!=null)		conn.close();
			}catch(Exception e) {e.printStackTrace();}
		}
	}	// setReadCountUpdate() ---------
	
	
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
	
	
	public int boardDelete(int num) {
		String sql="delete from SELL_BOARD where SB_NO=?";
		try {
	         conn = ds.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, num);
	               
	         int result=pstmt.executeUpdate();
	         
	         if(result==1) {
	        	 System.out.println("boardDelete 성공");
	        	 return result;
	         }
	      }catch(Exception e) {
	         System.out.println("SellBoardDAO - boardDelete() 에러 :" + e);
	         e.printStackTrace();
	      }finally {
	         if(rset != null) try {rset.close();}catch(SQLException ex) {ex.printStackTrace();}
	         if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {ex.printStackTrace();}
	         if(conn != null) try {conn.close();}catch(SQLException ex) {ex.printStackTrace();}
	      }
		return 0;
	}	// boardDelete() ---------

  public int boardModify(SellBoardBean sellboard) {
		String sql = "update SELL_BOARD "
	            + "set SB_TITLE=?, SB_PRICE=?, SB_CONTENT=?, SB_PDATE=?, SB_MDATE=sysdate "
	            + "where SB_NO=?";
	      try {
	         conn = ds.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, sellboard.getSB_TITLE());
	         pstmt.setInt(2, sellboard.getSB_PRICE());
	         pstmt.setString(3, sellboard.getSB_CONTENT());
	         pstmt.setDate(4, sellboard.getSB_PDATE());
	         pstmt.setInt(5, sellboard.getSB_NO());
	               
	         result=pstmt.executeUpdate();
	      }catch(Exception e) {
	         System.out.println("boardmodify() 에러 :" + e);
	         e.printStackTrace();
	      }finally {
	         if(rset != null) try {rset.close();}catch(SQLException ex) {ex.printStackTrace();}
	         if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {ex.printStackTrace();}
	         if(conn != null) try {conn.close();}catch(SQLException ex) {ex.printStackTrace();}
	      }
	      return result;
	   } // boardModify -------------
	
	public Map<String, Object> getSearchList(int page, int limit, String content, String item) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<SellBoardBean> list=new ArrayList<SellBoardBean>();
		int startrow=(page-1)*limit+1;
		int endrow=startrow+limit-1;
		int listcount=0;
		String contentStr = "%" + content + "%";
		try {
			conn=ds.getConnection();
			StringBuffer sb=new StringBuffer();
			sb.append("select rownum rnum, SB_NO, SB_WRITER, SB_TITLE, " + 
					"SB_READCOUNT, SB_DATE from " + 
					"(select * from SELL_BOARD where ");
			if(item.equals("title")){
				sb.append("SB_TITLE LIKE ? ");
			}
			else if(item.equals("content")) {
				sb.append("SB_CONTENT LIKE ? ");
			}
			else {
				sb.append("SB_TITLE LIKE ? OR SB_CONTENT LIKE ? ");
			}
			sb.append("order by SB_NO desc)");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, contentStr);
			if(item.equals("title_content")) {
				pstmt.setString(2, contentStr);
			}
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				listcount++;
			}
			map.put("listcount", listcount);
			
			rset.close();
			pstmt.close();
			
			sb.insert(0, "select * from (");
			sb.append(") where rnum>=? and rnum<=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, contentStr);
			if(item.equals("title_content")) {
				pstmt.setString(2, contentStr);
				pstmt.setInt(3, startrow);
				pstmt.setInt(4, endrow);
			}
			else {
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
			}
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				SellBoardBean sellboard=new SellBoardBean();
				sellboard.setSB_NO(rset.getInt("SB_NO"));
				sellboard.setSB_WRITER(rset.getString("SB_WRITER"));
				sellboard.setSB_TITLE(rset.getString("SB_TITLE"));
				sellboard.setSB_DATE(rset.getDate("SB_DATE"));
				sellboard.setSB_READCOUNT(rset.getInt("SB_READCOUNT"));
				list.add(sellboard);
			}
			map.put("boardlist", list);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					if(pstmt!=null)	pstmt.close();
					if(conn!=null)		conn.close();
					if(rset!=null)		rset.close();
				}catch(Exception e) {e.printStackTrace();}
			}
		
		return map;
	}
}
