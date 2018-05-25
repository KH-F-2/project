package com.project101.board.purchase.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PurchaseBoardDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
	int result;

	public PurchaseBoardDAO() {

		try {

			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {

			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}

	// 작성
	public boolean isBuyWriter(int num, String id) {  
		String buy_sql = "select * from PURCHASE_BOARD where PB_NO=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(buy_sql);
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			rset.next();
			if (id.equals(rset.getString("PB_WRITER"))) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("isBuyWriter 에러 : " + e);
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
		return false;
	}

	// 수정
	public boolean buyModify(PurchaseBoardBean buydata) {
		String sql = "update purchase_board set PB_TITLE =?, " + "PB_CONTENT = ? " + "where PB_NO = ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buydata.getTitle());
			pstmt.setString(2, buydata.getContent());
			pstmt.setInt(3, buydata.getNum());
			pstmt.executeUpdate();

			return true;
		} catch (Exception e) {
			System.out.println("buyModify 에러 : " + e);
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
		return false;
	}

	// 조회수 readcount 추가
	public void setReadCountUpdate(int num) {
		String sql = "update purchase_board " + "set pb_readcount = pb_readcount+1 " + "where PB_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeQuery();
		} catch (Exception e) {
			System.out.println("setReadCountUpdate() 에러 : " + e);
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
	}
	// 상세보기
	public PurchaseBoardBean getDetail(int num) {
		PurchaseBoardBean buydata = null;
		String sql = "select * from purchase_board where PB_NO=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				buydata = new PurchaseBoardBean();
				buydata.setNum(rset.getInt("PB_NO"));
				buydata.setWriter(rset.getString("PB_WRITER"));
				buydata.setTitle(rset.getString("PB_TITLE"));
				buydata.setContent(rset.getString("PB_CONTENT"));
				buydata.setFile(rset.getString("PB_FILE"));
				buydata.setreadcount(rset.getInt("PB_READCOUNT"));
				buydata.setDate(rset.getDate("PB_DATE"));
				
			}
			return buydata;
		} catch (Exception e) {
			System.out.println("getDetailAction 에러 :" + e);
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

	// 글 갯수 구하기
	public int getListCount() {
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from PURCHASE_BOARD");
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

	// 글 목록 보기
	public List<PurchaseBoardBean> getBuyList(HashMap<String, Object> listOpt , int page, int limit, String search, String searchotp) {
		// page:페이지
		// limit : 페이지 당 목록의 수
		// board_re_ref desc, board_re_seq asc에 의해 정렬한 것을
		// 조건절에 맞는 rnum의 범위만큼 가져오는 쿼리문 입니다.

		List<PurchaseBoardBean> list = new ArrayList<PurchaseBoardBean>();
		// 한 페이지당 10개씩 목록인 경우 1page,2page,3page,...
		String opt = (String)listOpt.get("opt"); //검색옵션
		System.out.println("opt="+opt);
		String condition = (String)listOpt.get("condition");  //검색내용
		System.out.println("condition="+condition);
		int startrow = (page - 1) * limit + 1; // 읽기 시작할 row 번호 (1,11,21,31,...)
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호(10,20,30,40,...)
		System.out.println("search="+search);
		try {
			conn = ds.getConnection();
			
			if(opt==null) {
				pstmt = conn.prepareStatement("select * from " + "(select rownum rnum,pb_no ,pb_writer, "
					+ "pb_title, pb_content, pb_file, " + "pb_date,pb_readcount from " 
					+ "(select * from PURCHASE_BOARD order by PB_NO desc))"
					+ "where rnum>=? and rnum<=?");
				pstmt.setInt(1, startrow);
				pstmt.setInt(2, endrow);
					System.out.println("노검색");
			} else if(opt.equals("0")) {
				pstmt = conn.prepareStatement("select * from " + "(select rownum rnum,pb_no ,pb_writer, "
					+ "pb_title, pb_content, pb_file, " + "pb_date,pb_readcount from " 
					+ "(select * from PURCHASE_BOARD where PB_WRITER like ? order by PB_NO desc))"
					+ "where rnum>=? and rnum<=?");
				pstmt.setString(1, "%"+condition+"%");
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
					System.out.println("작성자 검색");
			} else if(opt.equals("1")) {
				pstmt = conn.prepareStatement("select * from " + "(select rownum rnum,pb_no ,pb_writer, "
						+ "pb_title, pb_content, pb_file, " + "pb_date,pb_readcount from " 
						+ "(select * from PURCHASE_BOARD where PB_TITLE like ? order by PB_NO desc))"
						+ "where rnum>=? and rnum<=?");
				pstmt.setString(1, "%"+condition+"%");
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
				System.out.println("제목 검색");
			} else if(opt.equals("2")) {
				pstmt = conn.prepareStatement("select * from " + "(select rownum rnum,pb_no ,pb_writer, "
						+ "pb_title, pb_content, pb_file, " + "pb_date,pb_readcount from " 
						+ "(select * from PURCHASE_BOARD where PB_CONTENT like ? order by PB_NO desc))"
						+ "where rnum>=? and rnum<=?");
				pstmt.setString(1, "%"+condition+"%");
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
				System.out.println("내용 검색");
				}
			
			
			
			rset = pstmt.executeQuery();

			
			// DB에서 가져온 데이터를 VO객체에 담습니다.
			while (rset.next()) {
				PurchaseBoardBean buyList = new PurchaseBoardBean();
				buyList.setNum(rset.getInt("pb_no"));
				buyList.setWriter(rset.getString("pb_writer"));
				buyList.setTitle(rset.getString("pb_title"));
				buyList.setContent(rset.getString("pb_content"));
				buyList.setFile(rset.getString("pb_file"));
				buyList.setreadcount(rset.getInt("pb_readcount"));
				buyList.setDate(rset.getDate("pb_date"));
				list.add(buyList);// 값을 담은 객체를 리스트에 저장합니다.
			}
			return list;// 값을 담은 객체를 저장한 리스트를 호출한 곳으로
		} catch (Exception ex) {
			System.out.println("getBoardList() 에러 : " + ex);
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

	//글 작성
	public boolean PurchaseInsert(PurchaseBoardBean boarddata) {
		int num = 0;
		String sql = "";
		int result = 0;
		try {
			conn = ds.getConnection();
			// board 테이블의 board_num 필드의 최대값을 구해와서 글을 등록할 때 글 번호를 순차적으로 지정하기 위함입니다.
			String max_sql = "select max(PB_NO) from PURCHASE_BOARD";
			pstmt = conn.prepareStatement(max_sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				num = rset.getInt(1) + 1; // 최대값보다 1만큼 큰 값 지정
			} else {
				num = 1;
			} // 처음 데이터를 등록하는 경우입니다.

			sql = "INSERT INTO PURCHASE_BOARD " + "(PB_NO, PB_WRITER, PB_PASSWORD, PB_TITLE, PB_CONTENT, "
					+ "PB_FILE, PB_DATE, PB_READCOUNT) " + "values(?,?,?,?,?,?,sysdate,?)";

			// 새로운 글을 등록하는 부분입니다.

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, boarddata.getId());
			pstmt.setString(3, boarddata.getPassword());
			pstmt.setString(4, boarddata.getTitle());
			pstmt.setString(5, boarddata.getContent());
			pstmt.setString(6, boarddata.getFile());
			pstmt.setInt(7, 0);

			result = pstmt.executeUpdate();

			if (result == 0) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			System.out.print("PurchaseInsert 에러 " + e);
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

		return false;

	}

	//삭제
	public boolean buyDelete(PurchaseBoardBean buydata) {

		String sql = "DELETE FROM PURCHASE_BOARD WHERE PB_NO = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, buydata.getNum());

			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("삭제 에러 " + e);
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
		return false;
	}

	//검색 결과 값 숫자 세기
	public int getValueCount(HashMap<String, Object> listOpt, String search, String searchotp) {
		int x = 0;
		String opt = (String)listOpt.get("opt"); //검색옵션
		
		
		try {
			conn = ds.getConnection();
			String condition = (String)listOpt.get("condition");  //검색내용
			if(opt==null) {
				pstmt = conn.prepareStatement("select count(*) from " + "(select rownum rnum,pb_no ,pb_writer, "
					+ "pb_title, pb_content, pb_file, " + "pb_date,pb_readcount from " 
					+ "(select * from PURCHASE_BOARD order by PB_NO desc))");
			} else if(opt.equals("0")) {
				pstmt = conn.prepareStatement("select count(*) from " + "(select rownum rnum,pb_no ,pb_writer, "
					+ "pb_title, pb_content, pb_file, " + "pb_date,pb_readcount from " 
					+ "(select * from PURCHASE_BOARD where PB_WRITER like ? order by PB_NO desc))");
				pstmt.setString(1, "%"+condition+"%");
			} else if(opt.equals("1")) {
				pstmt = conn.prepareStatement("select count(*) from " + "(select rownum rnum,pb_no ,pb_writer, "
						+ "pb_title, pb_content, pb_file, " + "pb_date,pb_readcount from " 
						+ "(select * from PURCHASE_BOARD where PB_TITLE like ? order by PB_NO desc))");
				pstmt.setString(1, "%"+condition+"%");
			} else if(opt.equals("2")) {
				pstmt = conn.prepareStatement("select count(*) from " + "(select rownum rnum,pb_no ,pb_writer, "
						+ "pb_title, pb_content, pb_file, " + "pb_date,pb_readcount from " 
						+ "(select * from PURCHASE_BOARD where PB_CONTENT like ? order by PB_NO desc))");
				pstmt.setString(1, "%"+condition+"%");
				}
			
			
			rset = pstmt.executeQuery();

			if (rset.next()) {
				x = rset.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getValueCount() 에러 : " + e);
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
