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


public class CommentDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
	int result;
	final String SELLBOARD = "sb";
	final String PURCHASEBOARD = "pb";
	final String EPILOGUEBOARD = "eb";
	final String REPORTBOARD = "rb";

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

	public int commentInsert(CommentBean commentBean, String BOARD_NAME) {
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

			pstmt = conn.prepareStatement("insert into COMMENTS values("
					+ "?, ?, ?, ?, sysdate, ?, ?, ?, ?)");

			pstmt.setInt(1, num);
			pstmt.setInt(2, commentBean.getCMT_SUBJECT_NO());
			pstmt.setString(3, commentBean.getCMT_WRITER());
			pstmt.setString(4, commentBean.getCMT_CONTENT());
			pstmt.setInt(5, num);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setString(8, BOARD_NAME);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("commentInsert에러 : " + e);
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

	public List<CommentBean> getCmtList(int num, int page, int limit, String BOARD_NAME) {
		List<CommentBean> list = new ArrayList<CommentBean>();

		int startrow = (page - 1) * limit + 1; // 읽기 시작할 row 번호 (1,11,21,31,...)
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호(10,20,30,40,...)
		
		try {
			conn = ds.getConnection();
			
			String sql = "select * from " 
						+ "(select rownum rnum, CMT_NO, CMT_SUBJECT_NO, CMT_WRITER, CMT_CONTENT, "
						+ "TO_CHAR(CMT_DATE, 'YYYY-MM-DD HH24:MI:SS') as CMT_DATE, CMT_RE_REF, CMT_RE_LEV, "
						+ "CMT_RE_SEQ, CMT_BOARD_NAME "
						+ "FROM (SELECT * FROM COMMENTS "
						+ "where CMT_SUBJECT_NO = ? AND CMT_BOARD_NAME = ? "
						+ "order by CMT_RE_REF desc, CMT_RE_SEQ asc, CMT_NO desc)) "
						+ "where rnum >= ? and rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, BOARD_NAME);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				CommentBean commentBean = new CommentBean();
				commentBean.setCMT_NO(rset.getInt("CMT_NO"));
				commentBean.setCMT_SUBJECT_NO(rset.getInt("CMT_SUBJECT_NO"));
				commentBean.setCMT_WRITER(rset.getString("CMT_WRITER"));
				commentBean.setCMT_CONTENT(rset.getString("CMT_CONTENT"));
				commentBean.setCMT_DATE(rset.getString("CMT_DATE"));
				commentBean.setCMT_RE_REF(rset.getInt("CMT_RE_REF"));
				commentBean.setCMT_RE_LEV(rset.getInt("CMT_RE_LEV"));
				commentBean.setCMT_RE_SEQ(rset.getInt("CMT_RE_SEQ"));
				commentBean.setCMT_BOARD_NAME(rset.getString("CMT_BOARD_NAME"));
				list.add(commentBean);
				
			}
			
			return list;

		} catch (Exception e) {
			e.printStackTrace();
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

	public CommentBean getCommentDetail(int num, String board_name) {
		CommentBean commentBean = new CommentBean();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select * from COMMENTS where CMT_NO = ? "
					+ "and CMT_BOARD_NAME = ?");
			
			pstmt.setInt(1, num);
			pstmt.setString(2, board_name);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				commentBean = new CommentBean();
				commentBean.setCMT_NO(rset.getInt(1));
				commentBean.setCMT_SUBJECT_NO(rset.getInt(2));
				commentBean.setCMT_WRITER(rset.getString(3));
				commentBean.setCMT_CONTENT(rset.getString(4));
				commentBean.setCMT_DATE(rset.getString(5));
				commentBean.setCMT_RE_REF(rset.getInt(6));
				commentBean.setCMT_RE_LEV(rset.getInt(7));
				commentBean.setCMT_RE_SEQ(rset.getInt(8));
				commentBean.setCMT_BOARD_NAME(rset.getString(9));
			}
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
		return commentBean;
	}

	public int getCommentListCount(int num) {
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

	public int commentDelete(int num, String board_name) {
		try {
			conn = ds.getConnection();
			
			String sql = "delete from COMMENTS where CMT_NO=? and CMT_BOARD_NAME=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, board_name);

			int result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("commentDelete 성공");
			} else {
				System.out.println("commentDelete 실패");
			}
		} catch (Exception e) {
			System.out.println("CommentDAO - commentDelete() 에러 :" + e);
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
	
	
	
	public int commentReply(CommentBean commentBean,String board_name) {
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

			pstmt = conn.prepareStatement("insert into COMMENTS values("
					+ "?, ?, ?, ?, sysdate, ?, ?, ?, ?)");

			
			pstmt.setInt(1, num);
			pstmt.setInt(2, commentBean.getCMT_SUBJECT_NO());
			pstmt.setString(3, commentBean.getCMT_WRITER());
			pstmt.setString(4, commentBean.getCMT_CONTENT());
			pstmt.setInt(5, num);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setString(8, BOARD_NAME);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_NAME());
			pstmt.setString(3, board.getBOARD_PASS());
			pstmt.setString(4, board.getBOARD_SUBJECT());
			pstmt.setString(5, board.getBOARD_CONTENT());
			pstmt.setString(6, board.getBOARD_FILE());
			pstmt.setInt(7, board.getBOARD_RE_REF());
			pstmt.setInt(8, board.getBOARD_RE_LEV()+1);
			pstmt.setInt(9, board.getBOARD_RE_SEQ()+1);

			result = pstmt.executeUpdate();
			
			
		int num=0;
		String sql="";
		try {
			conn=ds.getConnection();
			
			sql="select max(board_num) from board";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next())
				num=rs.getInt(1)+1;
			else
				num=1;
			
			rs.close();
			pstmt.close();
			
			sql="update board "
				     + "set BOARD_RE_SEQ=BOARD_RE_SEQ + 1 "
				     + "where BOARD_RE_REF = ? "
				     + "and BOARD_RE_SEQ > ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBOARD_RE_REF());
			pstmt.setInt(2, board.getBOARD_RE_SEQ());
			result=pstmt.executeUpdate();
			
			System.out.println(result+"개 게시글 수정 완료");
			
			pstmt.close();
			
			sql="insert into board "
				      + "(BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,"
				      + " BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF,"
				      + " BOARD_RE_LEV, BOARD_RE_SEQ,"
				      + " BOARD_READCOUNT,BOARD_DATE) "
				      + "values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_NAME());
			pstmt.setString(3, board.getBOARD_PASS());
			pstmt.setString(4, board.getBOARD_SUBJECT());
			pstmt.setString(5, board.getBOARD_CONTENT());
			pstmt.setString(6, board.getBOARD_FILE());
			pstmt.setInt(7, board.getBOARD_RE_REF());
			pstmt.setInt(8, board.getBOARD_RE_LEV()+1);
			pstmt.setInt(9, board.getBOARD_RE_SEQ()+1);
			pstmt.setInt(10, 0);
			
			pstmt.executeUpdate();
			
			return num;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)	rs.close();
				if(pstmt!=null)	pstmt.close();
				if(conn!=null)		conn.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		
		return 0;
	}
	
}
