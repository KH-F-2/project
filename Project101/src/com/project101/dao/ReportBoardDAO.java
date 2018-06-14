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

	public ReportBoardBean getDetail(int num) {
		ReportBoardBean reportboard = new ReportBoardBean();
		try {
			con = ds.getConnection();
			String sql = "select * from REPORT_BOARD where RB_NO=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				reportboard.setRB_NO(rs.getInt(1));
				reportboard.setRB_RP_ID(rs.getString(2));
				reportboard.setRB_RP_NO(rs.getInt(3));
				reportboard.setRB_RP_BOARD_NAME(rs.getString(4));
				reportboard.setRB_WRITER(rs.getString(5));
				reportboard.setRB_TITLE(rs.getString(6));
				reportboard.setRB_CONTENT(rs.getString(7));
				reportboard.setRB_DATE(rs.getDate(8));
				
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
			String sql = "select * from " + "(select rownum rnum, rb_no, rb_rp_id, rb_rp_no, rb_rp_board_name, rb_writer, rb_title, rb_content, rb_date "
					+ "from (select * from REPORT_BOARD order by RB_NO desc)) " + "where rnum>=? and rnum<=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReportBoardBean reportboard = new ReportBoardBean();
				reportboard.setRB_NO(rs.getInt("rb_no"));
				reportboard.setRB_RP_ID(rs.getString("rb_rp_id"));
				reportboard.setRB_RP_NO(rs.getInt("rb_rp_no"));
				reportboard.setRB_RP_BOARD_NAME(rs.getString("rb_rp_board_name"));
				reportboard.setRB_WRITER(rs.getString("rb_writer"));
				reportboard.setRB_TITLE(rs.getString("rb_title"));
				reportboard.setRB_CONTENT(rs.getString("rb_content"));
				reportboard.setRB_DATE(rs.getDate("rb_date"));

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
		String sql = "update REPORT_BOARD set RB_TITLE=?, RB_CONTENT=? where RB_NO=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reportboard.getRB_TITLE());
			pstmt.setString(2, reportboard.getRB_CONTENT());
			pstmt.setInt(3, reportboard.getRB_NO());
			
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

	public int boardInsert(String rb_rp_id, int rb_rp_no, String rb_rp_board_name, String rb_writer, String rb_title, String rb_content) {
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

			sql = "insert into REPORT_BOARD " + "values(?, ?, ?, ?, ?, ?, ?, sysdate) ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, rb_rp_id);
			pstmt.setInt(3, rb_rp_no);
			pstmt.setString(4, rb_rp_board_name);
			pstmt.setString(5, rb_writer);
			pstmt.setString(6, rb_title);
			pstmt.setString(7, rb_content);
			
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

}
