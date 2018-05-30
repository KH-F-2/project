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

	public int imageInsert(ImageBean image) {
		try {
			conn = ds.getConnection();

			String sql = "insert into IMAGE " + "values(?, 'SELL_BOARD', ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, image.getBOARD_NO());
			pstmt.setString(2, image.getIMAGE_URL());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<ImageBean> getImage(int num) {
		List<ImageBean> imageBeanList = new ArrayList<ImageBean>();
		try {
			conn = ds.getConnection();
			
			String sql = "select * from IMAGE where BOARD_NO=? and BOARD_NAME='SELL_BOARD'";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				ImageBean image = new ImageBean();
				image.setBOARD_NO(rset.getInt("BOARD_NO"));
				image.setBOARD_NAME(rset.getString("BOARD_NAME"));
				image.setIMAGE_URL(rset.getString("IMAGE_URL"));
				imageBeanList.add(image);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return imageBeanList;
	}

	public int imageDelete(int board_no) {
		try {
			conn = ds.getConnection();

			String sql = "delete from IMAGE where BOARD_NO=? and BOARD_NAME='SELL_BOARD'";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board_no);

			result = pstmt.executeUpdate();
			
			if (result == 0) {
				System.out.println("imageDelete() fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
