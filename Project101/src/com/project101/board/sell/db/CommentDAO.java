package com.project101.board.sell.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CommentDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
	int result;

	public CommentDAO() {
		try {

			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {

			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}

	public List<CommentBean> getCommentList(int num) {
		List<CommentBean> commentlist=new ArrayList<CommentBean>();
		try {
			conn=ds.getConnection();
			String sql="select * from BOARD_COMMENT where BOARD_NO=? and BOARD_NAME='SELL_BOARD'";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				CommentBean comment=new CommentBean();
				comment.setCOMMENT_NO(rset.getInt("COMMENT_NO"));
				comment.setBOARD_NO(num);
				comment.setBOARD_NAME(rset.getString("BOARD_NAME"));
				comment.setCOMMENT_WRITER(rset.getString("COMMENT_WRITER"));
				comment.setCOMMENT_DATE(rset.getString("COMMENT_DATE"));
				comment.setCOMMENT_CONTENT(rset.getString("COMMENT_CONTENT"));
				commentlist.add(comment);
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
		
		return commentlist;
	}
	
	public int commentInsert(CommentBean comment) {
		String sql="";
		int num=0;
		try {
			conn=ds.getConnection();
			
			sql="select max(COMMENT_NO) from BOARD_COMMENT";
			pstmt=conn.prepareStatement(sql);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				num=rset.getInt(1)+1;
			}
			else {
				num=1;
			}
			
			rset.close();
			pstmt.close();
			
			sql="insert into BOARD_COMMENT "
					+ "values(?, ?, ?, ?, sysdate, ?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, comment.getBOARD_NO());
			pstmt.setString(3, "SELL_BOARD");
			pstmt.setString(4, comment.getCOMMENT_WRITER());
			pstmt.setString(5, comment.getCOMMENT_CONTENT());
			
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
		
		return num;
	}
	
}
