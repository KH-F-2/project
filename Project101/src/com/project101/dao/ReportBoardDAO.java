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

import com.project101.bean.ReportBoardBean;

public class ReportBoardDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
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

			String sql = "delete from REPORT_BOARD where RB_NO=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);

			int result = pstmt.executeUpdate();

			if (result == 1) {

				System.out.println("boardDelete 성공");
				return result;
			}

		} catch (Exception e) {

			System.out.println("ReportBoardDAO - boardDelete() 에러 :" + e);
			e.printStackTrace();
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return 0;
	}

	public void setReadCountUpdate(int num) {

		try {

			con = ds.getConnection();
			String sql = "update REPORT_BOARD set RB_READCOUNT=RB_READCOUNT+1 where RB_NO=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public ReportBoardBean getDetail(int num) {
		ReportBoardBean reportboard = new ReportBoardBean();
		try {
			con = ds.getConnection();
			String sql = "select * from REPORT_BOARD where RB_NO=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				reportboard.setRB_NO(rs.getInt("RB_NO"));
				reportboard.setRB_WRITER(rs.getString("RB_WRITER"));
				reportboard.setRB_TITLE(rs.getString("RB_TITLE"));
				reportboard.setRB_CONTENT(rs.getString("RB_CONTENT"));
				reportboard.setRB_PRICE(rs.getInt("RB_PRICE"));

				return reportboard;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public int boardInsert(ReportBoardBean reportboard) {
		int num = 0;
		String sql = "";
		try {
			con = ds.getConnection();
			sql = "select max(RB_NO) from REPORT_BOARD";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next())
				num = rs.getInt(1) + 1;
			else {
				num = 1;
			}
			rs.close();
			pstmt.close();

			sql = "insert into REPORT_BOARD " + "values(?, ?, ?, ?, ?, sysdate,0 ) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, reportboard.getRB_WRITER());
			pstmt.setString(3, reportboard.getRB_TITLE());
			pstmt.setString(4, reportboard.getRB_CONTENT());
			pstmt.setInt(5, reportboard.getRB_PRICE());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int getListCount() {
		try {
			con = ds.getConnection();
			String sql = "SELECT COUNT(*) FROM REPORT_BOARD ";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next())
				result = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<ReportBoardBean> getBoardList(int page, int limit) {
		List<ReportBoardBean> list = new ArrayList<ReportBoardBean>();
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try {
			con = ds.getConnection();
			String sql = "select * from " + "(select rownum rnum, RB_NO, RB_WRITER, RB_TITLE, RB_DATE, RB_READCOUNT "
					+ "from (select * from REPORT_BOARD order by RB_NO desc)) " + "where rnum>=? and rnum<=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReportBoardBean reportboard = new ReportBoardBean();
				reportboard.setRB_NO(rs.getInt("RB_NO"));
				reportboard.setRB_WRITER(rs.getString("RB_WRITER"));
				reportboard.setRB_TITLE(rs.getString("RB_TITLE"));
				reportboard.setRB_DATE(rs.getDate("RB_DATE"));
				reportboard.setRB_READCOUNT(rs.getInt("RB_READCOUNT"));

				list.add(reportboard);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public int boardModify(ReportBoardBean reportboard) {
		String sql = "update REPORT_BOARD " + "set RB_TITLE=?, RB_PRICE=?, RB_CONTENT=? " + "where RB_NO=?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reportboard.getRB_TITLE());
			pstmt.setInt(2, reportboard.getRB_PRICE());
			pstmt.setString(3, reportboard.getRB_CONTENT());
			pstmt.setInt(4, reportboard.getRB_NO());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("boardmodify() 에러 :" + e);
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}

}
