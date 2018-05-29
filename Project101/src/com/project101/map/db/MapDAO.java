package com.project101.map.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			ds = 
			(DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex) {
			System.out.println("DB  : " + ex);
			return;
		}
	}

	public JSONArray getMarkers(double startLat, double startLng, double endLat, double endLng) {
		JSONArray array = new JSONArray();
		try {
			conn = ds.getConnection();
			String sql = "select * from sell_board where SB_LAT between ? and ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, startLat);
			pstmt.setDouble(2, endLat);
//			pstmt.setDouble(3, startLng);
//			pstmt.setDouble(4, endLng);

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
				
				array.add(obj);
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

		return array;
	}
	
}
