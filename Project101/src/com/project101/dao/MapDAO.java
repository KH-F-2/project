package com.project101.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MapDAO {

	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
	int result;
	
	public MapDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB  : " + ex);
			return;
		}
	}

	public JSONArray getMarkers(double startLat, double startLng, double endLat, double endLng) {
		JSONArray array = new JSONArray();

		try {
			conn = ds.getConnection();
			String sql = "select sb_no, sb_writer, sb_date, sb_title, sb_content, sb_price, sb_date, sb_readcount, sb_lat, sb_lng"
					+ ", sqrt(power((? - sb_lat), 2) + power((? - sb_lng), 2)) as distance"
					+ " from (select * from sell_board where SB_LAT between ? and ?) where SB_LNG between ? and ?"
					+ " order by distance";

			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, (endLat + startLat) / 2);
			pstmt.setDouble(2, (endLng + startLng) / 2);
			pstmt.setDouble(3, startLat);
			pstmt.setDouble(4, endLat);
			pstmt.setDouble(5, startLng);
			pstmt.setDouble(6, endLng);
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				JSONObject obj = new JSONObject();
				
				obj.put("SB_NO", rset.getInt("SB_NO"));
				obj.put("SB_WRITER", rset.getString("SB_WRITER"));
				obj.put("sb_TITLE", rset.getString("SB_TITLE"));
				obj.put("SB_CONTENT", rset.getString("SB_CONTENT"));
				obj.put("SB_PRICE", rset.getInt("SB_PRICE"));
				obj.put("lat", rset.getDouble("SB_LAT"));
				obj.put("lng", rset.getDouble("SB_LNG"));
				obj.put("distance", rset.getDouble("distance"));
				
				array.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (rset != null) {
					rset.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return array;
	}
	
}
