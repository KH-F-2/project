package com.project101.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.project101.bean.Epil;

public class InterestDAO {

	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
	int result;

	public InterestDAO() {

		try {

			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {

			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}
	
	public int boardInsert(String id, int content_num, String board_name) {
		int num = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "select max(num) from interest";
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				num = rset.getInt(1) + 1;
			} else {
				num = 1;
			}	
			
			rset.close();
			pstmt.close();
			
			String insert_sql = "insert into interest values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(insert_sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, id);
			pstmt.setInt(3, content_num);
			pstmt.setString(4, board_name);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("epInsert 오류 : " + e);
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int countInterest(String id) {
		int num = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "select * from interest where id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				num++;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("epInsert 오류 : " + e);
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return num;
	}

	public JSONArray getBoardlist(String id) {
		JSONArray jsonArr = new JSONArray();
		
		
		try {
			conn = ds.getConnection();
			
			
			
			StringBuffer sb = new StringBuffer();
			sb.append("select rownum rnum, NUM, WRITER, TITLE, content, price, READCOUNT, DDATE, category, hashtag, lat, lng, IMAGE_URL, BOARD_NAME, id from "
					+ "(select NUM, WRITER, TITLE, content, price, READCOUNT, DDATE, category, hashtag, lat, lng, IMAGE_URL, BOARD_NAME , id from "
					+ "(select SB_NO NUM, SB_WRITER WRITER, SB_TITLE TITLE, SB_CONTENT content, sb_price price, SB_READCOUNT READCOUNT, SB_DATE DDATE"
					+ ", sb_category category, sb_hashtag hashtag, sb_lat lat, sb_lng lng, IMAGE_URL, BOARD_NAME, id from "
					+ "(select * from SELL_BOARD inner join IMAGE on SELL_BOARD.SB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'SELL_BOARD')"
					+ ", (select num in_num, content_num in_content_num, id from interest where board_name='SELL_BOARD') where sb_no=in_num) "
					+ "UNION ALL "
					+ "(select PB_NO NUM, PB_WRITER WRITER, PB_TITLE TITLE, pb_content content, pb_price price, PB_READCOUNT READCOUNT, PB_DATE DDATE"
					+ ", pb_category category, pb_hashtag hashtag, pb_lat lat, pb_lng lng, IMAGE_URL, BOARD_NAME, id from "
					+ "(select * from PURCHASE_BOARD inner join IMAGE on PURCHASE_BOARD.PB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'PURCHASE_BOARD')"
					+ ", (select num in_num, content_num in_content_num, id from interest where board_name='PURCHASE_BOARD') where pb_no=in_num)"
					+ ") where  id=? order by ddate");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("num", rset.getInt("NUM"));
				jsonObj.put("writer", rset.getString("WRITER"));
				jsonObj.put("title", rset.getString("TITLE"));
				jsonObj.put("content", rset.getString("CONTENT"));
				jsonObj.put("price", rset.getInt("PRICE"));
				jsonObj.put("readcount", rset.getInt("READCOUNT"));
				jsonObj.put("date", rset.getDate("DDATE").toString());
				jsonObj.put("category", rset.getInt("category"));
				jsonObj.put("hashtag", rset.getString("hashtag"));
				jsonObj.put("lat", rset.getDouble("lat"));
				jsonObj.put("lng", rset.getDouble("lng"));
				jsonObj.put("image_url", rset.getString("IMAGE_URL").split(" ")[0]);
				jsonObj.put("board_name", rset.getString("BOARD_NAME"));
	            
				jsonArr.add(jsonObj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rset != null)
					rset.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return jsonArr;
	}

	public int deleteInterest(int content_num, String board_name) {
		int result = 0; 
		
		try {
			String sql = "delete from interest where content_num=? and board_name=?";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, content_num);
			pstmt.setString(2, board_name);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("SellBoardDAO - boardDelete() 에러 :" + e);
			e.printStackTrace();
		} finally {
			if (rset != null)
				try {
					rset.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		
		return result;
	}
	
	
}
