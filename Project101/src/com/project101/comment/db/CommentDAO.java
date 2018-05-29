package com.project101.comment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public int getSeq() {
		result = 1;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select comment_seq.nextval from dual");
			rset = pstmt.executeQuery();

		} catch (Exception e) {
			System.out.println("댓글 시퀸스오류 : " + e);
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

			}

		}
		return result;

	}

	public boolean CommentInsert(CommentBean cmtdata) {

		int num = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select max(CMT_NO) from COMMENTS");
			rset = pstmt.executeQuery();

			if (rset.next()) {
				num = rset.getInt(1) + 1; // 최대값보다 1만큼 큰 값 지정
			} else {
				num = 1;
			}
			pstmt.close();

			pstmt = conn.prepareStatement("insert into COMMENTS "
					+ "(CMT_NO, CMT_SUBJECT_NO, CMT_WRITER, CMT_DATE, CMT_CONTENT, CMT_RE_REF, CMT_RE_LEV, CMT_SEQ) "
					+ "values(?,?,?,sysdate,?,?,?,?)");

			pstmt.setInt(1, num);
			pstmt.setInt(2, cmtdata.getCMT_SUBJECT_NO());
			pstmt.setString(3, cmtdata.getCMT_WRITER());
			pstmt.setString(4, cmtdata.getCMT_CONTENT());
			pstmt.setInt(5, cmtdata.getCMT_NO());
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);

			result = pstmt.executeUpdate();

			if (result == 0) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			System.out.println("CommentInsert에러 : " + e);
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

			}

		}

		return true;
	}

	public List<CommentBean> getCmtList(int num, int page, int limit) {
		List<CommentBean> list = new ArrayList<CommentBean>();

		int startrow = (page - 1) * limit + 1; // 읽기 시작할 row 번호 (1,11,21,31,...)
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호(10,20,30,40,...)
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(
			"select * from " 
			+ "(select rownum rnum, CMT_NO, CMT_SUBJECT_NO, CMT_WRITER, CMT_DATE, CMT_CONTENT, CMT_RE_REF, CMT_RE_LEV, CMT_SEQ "
			+ "FROM (SELECT * FROM COMMENTS where CMT_SUBJECT_NO = ? order by CMT_NO asc )) "
			+ "where rnum>= ? and rnum<= ? "
			);
			pstmt.setInt(1, num);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				CommentBean cmtList = new CommentBean();
				cmtList.setCMT_NO(rset.getInt("CMT_NO"));
				cmtList.setCMT_SUBJECT_NO(rset.getInt("CMT_SUBJECT_NO"));
				cmtList.setCMT_WRITER(rset.getString("CMT_WRITER"));
				cmtList.setCMT_DATE(rset.getDate("CMT_DATE"));
				cmtList.setCMT_CONTENT(rset.getString("CMT_CONTENT"));
				cmtList.setCMT_RE_REF(rset.getInt("CMT_RE_REF"));
				cmtList.setCMT_RE_LEV(rset.getInt("CMT_RE_LEV"));
				cmtList.setCMT_SEQ(rset.getInt("CMT_SEQ"));
				list.add(cmtList);
				
			}
			
			return list;

		} catch (Exception e) {
			System.out.println("getCmtList 에러" + e);
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public CommentBean getDetail(int num) {
		
		CommentBean cmtdata = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select * from COMMENTS where CMT_NO = ?");
			
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				cmtdata = new CommentBean();
				cmtdata.setCMT_NO(rset.getInt(1));
				cmtdata.setCMT_SUBJECT_NO(rset.getInt(2));
				cmtdata.setCMT_WRITER(rset.getString(3));
				cmtdata.setCMT_DATE(rset.getDate(4));
				cmtdata.setCMT_CONTENT(rset.getString(5));
				cmtdata.setCMT_RE_REF(rset.getInt(6));
				cmtdata.setCMT_RE_LEV(rset.getInt(7));
				cmtdata.setCMT_SEQ(rset.getInt(8));
				
			}
			return cmtdata;
			
		}catch(Exception e) {
			System.out.println("reply getDetailAction 에러 :" + e);
		}finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
		
	}

	public int getListCount(int num) {
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from COMMENTS where CMT_SUBJECT_NO = ?");

			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				x = rset.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getListCount() 에러 : " + e);
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return x;
	}

}
