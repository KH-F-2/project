package com.project101.board.report.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.project101.board.sell.db.SellBoardBean;

public class ReportBoardDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
	int result;

	public ReportBoardDAO() {

		try {

			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {

			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}

	public int boardDelete(int num) {
	      try {
	    	  	
	    	  	String sql="delete from REPORT_BOARD where RB_NO=?";
	            conn = ds.getConnection();
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, num);
	                  
	            int result=pstmt.executeUpdate();
	            
	            if(result==1) {
	               System.out.println("boardDelete 성공");
	               return result;
	            }
	         }catch(Exception e) {
	            System.out.println("ReportBoardDAO - boardDelete() 에러 :" + e);
	            e.printStackTrace();
	         }finally {
	            if(rset != null) try {rset.close();}catch(SQLException ex) {ex.printStackTrace();}
	            if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {ex.printStackTrace();}
	            if(conn != null) try {conn.close();}catch(SQLException ex) {ex.printStackTrace();}
	         }
		return 0;
	}

	public void setReadCountUpdate(int num) {
		 
	      try {
	         
	    	 conn = ds.getConnection();
	         String sql = "update REPORT_BOARD set RB_READCOUNT=RB_READCOUNT+1 where RB_NO=? ";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, num);
	         pstmt.executeUpdate();
	      }catch(Exception e) {
	    	 e.printStackTrace();
	      }finally {
	         try {
	            if(rset!=null)   
	            	rset.close();
	            if(pstmt!=null)   
	            	pstmt.close();
	            if(conn!=null)      
	            	conn.close();
	         }catch(Exception e) {
	        	 e.printStackTrace();
	         }
	      }
		
	}

	public ReportBoardBean getDetail(int num) {
		ReportBoardBean reportboard=new ReportBoardBean();
	      try {
	         conn=ds.getConnection();
	         String sql="select * from REPORT_BOARD where RB_NO=?";
	         pstmt=conn.prepareStatement(sql);
	         pstmt.setInt(1, num);
	         
	         rset=pstmt.executeQuery();
	         
	         if(rset.next()) {
	            reportboard.setRB_NO(rset.getInt("RB_NO"));
	            reportboard.setRB_WRITER(rset.getString("RB_WRITER"));
	            reportboard.setRB_TITLE(rset.getString("RB_TITLE"));
	            reportboard.setRB_CONTENT(rset.getString("RB_CONTENT"));
	            reportboard.setRB_PRICE(rset.getInt("RB_PRICE"));
	            
	            return reportboard;
	         }else {
	            return null;
	         }
	         
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         try {
	            if(rset!=null)   rset.close();
	            if(pstmt!=null)   pstmt.close();
	            if(conn!=null)      conn.close();
	         }catch(Exception e) {e.printStackTrace();}
	      }
	      
	      return null;
	}
	public int boardInsert(ReportBoardBean reportboard) {
	      int num=0;
	      String sql="";
	      try {
	         conn=ds.getConnection();
	         sql="select max(RB_NO) from REPORT_BOARD";
	         pstmt=conn.prepareStatement(sql);
	         rset=pstmt.executeQuery();
	         
	         if(rset.next())
	            num=rset.getInt(1)+1;
	         else {
	            num=1;
	         }
	         rset.close();
	         pstmt.close();
	         
	         sql="insert into REPORT_BOARD "
	               + "values(?, ?, ?, ?, ?, sysdate,0 ) ";
	         pstmt=conn.prepareStatement(sql);
	         pstmt.setInt(1, num);
	         pstmt.setString(2, reportboard.getRB_WRITER());
	         pstmt.setString(3, reportboard.getRB_TITLE());
	         pstmt.setString(4, reportboard.getRB_CONTENT());
	         pstmt.setInt(5, reportboard.getRB_PRICE());
	         result = pstmt.executeUpdate();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         try {
	            if(rset!=null)   rset.close();
	            if(pstmt!=null)   pstmt.close();
	            if(conn!=null)      conn.close();
	         }catch(Exception e) {e.printStackTrace();}
	      }
	      
	      return result;
	   }     
	public int getListCount() {
		try {
	         conn=ds.getConnection();
	         String sql="SELECT COUNT(*) FROM REPORT_BOARD ";
	         pstmt=conn.prepareStatement(sql);
	         
	         rset=pstmt.executeQuery();
	         
	         if(rset.next()) 
	            result=rset.getInt(1);
	         
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         try {
	            if(rset!=null)   rset.close();
	            if(pstmt!=null)   pstmt.close();
	            if(conn!=null)      conn.close();
	         }catch(Exception e) {e.printStackTrace();}
	      }
	      return result;
	}

	public List<ReportBoardBean> getBoardList(int page, int limit) {
		 List<ReportBoardBean> list=new ArrayList<ReportBoardBean>();
	      int startrow=(page-1)*limit+1;
	      int endrow=startrow+limit-1;
	      try {
	         conn=ds.getConnection();
	         String sql="select * from "
	               + "(select rownum rnum, RB_NO, RB_WRITER, RB_TITLE, RB_DATE, RB_READCOUNT "
	               + "from (select * from REPORT_BOARD order by RB_NO desc)) "
	               + "where rnum>=? and rnum<=? ";
	         pstmt=conn.prepareStatement(sql);
	         pstmt.setInt(1, startrow);
	         pstmt.setInt(2, endrow);
	         
	         rset=pstmt.executeQuery();
	         
	         while(rset.next()) {
	            ReportBoardBean reportboard=new ReportBoardBean();
	            reportboard.setRB_NO(rset.getInt("RB_NO"));
	            reportboard.setRB_WRITER(rset.getString("RB_WRITER"));
	            reportboard.setRB_TITLE(rset.getString("RB_TITLE"));
	            reportboard.setRB_DATE(rset.getDate("RB_DATE"));
	            reportboard.setRB_READCOUNT(rset.getInt("RB_READCOUNT"));
	            
	            list.add(reportboard);
	         }
	         	return list;
	         
	         }catch(Exception e){
	            e.printStackTrace();
	         }finally{
	            try {
	               if(pstmt!=null)   
	            	   pstmt.close();
	               if(conn!=null)      
	            	   conn.close();
	               if(rset!=null)      
	            	   rset.close();
	            }catch(Exception e) {
	            	e.printStackTrace();
	            	}
	         }
	      
	      return null;
	}

	public int boardModify(ReportBoardBean reportboard) {
		String sql = "update REPORT_BOARD "
	               + "set RB_TITLE=?, RB_PRICE=?, RB_CONTENT=? "
	               + "where RB_NO=?";
	         try {
	            conn = ds.getConnection();
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, reportboard.getRB_TITLE());
	            pstmt.setInt(2, reportboard.getRB_PRICE());
	            pstmt.setString(3, reportboard.getRB_CONTENT());
	            pstmt.setInt(4, reportboard.getRB_NO());
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
	}
	
	
}
