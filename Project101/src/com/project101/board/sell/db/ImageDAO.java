package com.project101.board.sell.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ImageDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
	int result;

	public ImageDAO() {
		try {

			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {

			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}
	
	/*public List<CommentBean> getBoardList(int no) {
		List<CommentBean> list=new ArrayList<CommentBean>();
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
	}*/
	
	public int imageInsert(ImageBean image) {
		String sql="";
		try {
			conn=ds.getConnection();
			
			sql="insert into IMAGE "
					+ "values(?, 'SELL_BOARD', ?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, image.getBOARD_NO());
			pstmt.setString(2, image.getIMAGE_URL());
			
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
	}
	
	public List<ImageBean> getImage(int num) {
		List<ImageBean> imagelist=new ArrayList<ImageBean>();
		try {
			conn=ds.getConnection();
			String sql="select * from IMAGE where BOARD_NO=? and BOARD_NAME='SELL_BOARD'";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				ImageBean image=new ImageBean();
				image.setBOARD_NO(rset.getInt("BOARD_NO"));
				image.setBOARD_NAME(rset.getString("BOARD_NAME"));
				image.setIMAGE_URL(rset.getString("IMAGE_URL"));
				imagelist.add(image);
				System.out.println("getImage rsnext ");
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
		
		return imagelist;
	}
}