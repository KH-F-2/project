package com.project101.dao;

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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.project101.bean.PurchaseBoardBean;


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
	public boolean isWriter(int num, String id) {
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
	public int purchaseModify(PurchaseBoardBean boardBean, int num) {
		String sql = "update purchase_board set PB_TITLE =?, " + "PB_CONTENT = ?, PB_HASHTAG = ?, PB_CATEGORY = ?, PB_LAT = ?, PB_LNG = ? " + "where PB_NO = ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardBean.getPB_TITLE());
			pstmt.setString(2, boardBean.getPB_CONTENT());
			pstmt.setString(3, boardBean.getPB_HASHTAG());
			pstmt.setInt(4, boardBean.getPB_CATEGORY());
			pstmt.setDouble(5, boardBean.getPB_LAT());
			pstmt.setDouble(6, boardBean.getPB_LNG());
			pstmt.setInt(7, num);
			
			int result = pstmt.executeUpdate();

			if (result == 1) {
				return 1;
			}
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
		return result;
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
	public JSONObject getDetail(int num) {
		JSONObject obj = new JSONObject();
		String sql = "select * from purchase_board inner join CATEGORY "
				+ "ON PURCHASE_BOARD.PB_CATEGORY = CATEGORY.CATEGORY_ID where PURCHASE_BOARD.PB_NO=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
		
			rset = pstmt.executeQuery();

			if (rset.next()) {
				obj.put("NO", rset.getInt("PB_NO"));
				obj.put("WRITER", rset.getString("PB_WRITER"));
				obj.put("TITLE", rset.getString("PB_TITLE"));
				obj.put("CONTENT", rset.getString("PB_CONTENT"));
				obj.put("DATE", rset.getDate("PB_DATE"));
				obj.put("CATEGORY", rset.getString("CATEGORY_NAME"));
				obj.put("HASHTAG", rset.getString("PB_HASHTAG"));
				obj.put("LAT", rset.getDouble("PB_LAT"));
				obj.put("LNG", rset.getDouble("PB_LNG"));
				obj.put("PRICE", rset.getInt("PB_PRICE"));
				obj.put("STATE", rset.getInt("PB_STATE"));
				obj.put("READCOUNT", rset.getInt("PB_READCOUNT"));
				
			}
			
			return obj;
			
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
		int result = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from PURCHASE_BOARD");
		
			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
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
		return result;
	}

	// 글 목록 보기
	public List<PurchaseBoardBean> getBoardBeandList(HashMap<String, Object> listOption, int page, int limit, String keyword, String searchOption) {
		// page:페이지
		// limit : 페이지 당 목록의 수
		// board_re_ref desc, board_re_seq asc에 의해 정렬한 것을
		// 조건절에 맞는 rnum의 범위만큼 가져오는 쿼리문 입니다.
		List<PurchaseBoardBean> boardBeanList = new ArrayList<PurchaseBoardBean>();
		// 한 페이지당 10개씩 목록인 경우 1page,2page,3page,...
		String option = (String) listOption.get("option"); // 검색옵션
		System.out.println("option=" + option);
		String condition = (String) listOption.get("condition"); // 검색내용
		System.out.println("condition=" + condition);
		int startRow = (page - 1) * limit + 1; // 읽기 시작할 row 번호 (1,11,21,31,...)
		int endRow = startRow + limit - 1; // 읽을 마지막 row 번호(10,20,30,40,...)
		System.out.println("search=" + keyword);
		
		try {
			conn = ds.getConnection();

			if (option == null) {
				pstmt = conn.prepareStatement("select * from " + "(select rownum rnum,pb_no ,pb_writer, "
						+ "pb_title, pb_content, pb_price, " + "pb_date,pb_readcount, PB_CATEGORY,PB_HASHTAG,PB_LAT,PB_LNG,PB_STATE  from "
						+ "(select * from PURCHASE_BOARD order by PB_NO desc))" + "where rnum>=? and rnum<=?");
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				System.out.println("노검색");
			} else if (option.equals("0")) {
				pstmt = conn.prepareStatement("select * from " + "(select rownum rnum,pb_no ,pb_writer, "
						+ "pb_title, pb_content, pb_price, " + "pb_date,pb_readcount, PB_CATEGORY,PB_HASHTAG,PB_LAT,PB_LNG,PB_STATE  from "
						+ "(select * from PURCHASE_BOARD where PB_WRITER like ? order by PB_NO desc))"
						+ "where rnum>=? and rnum<=?");
				pstmt.setString(1, "%" + condition + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				System.out.println("작성자 검색");
			} else if (option.equals("1")) {
				pstmt = conn.prepareStatement("select * from " + "(select rownum rnum,pb_no ,pb_writer, "
						+ "pb_title, pb_content, pb_price, " + "pb_date,pb_readcount, PB_CATEGORY,PB_HASHTAG,PB_LAT,PB_LNG,PB_STATE  from "
						+ "(select * from PURCHASE_BOARD where PB_TITLE like ? order by PB_NO desc))"
						+ "where rnum>=? and rnum<=?");
				pstmt.setString(1, "%" + condition + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				System.out.println("제목 검색");
			} else if (option.equals("2")) {
				pstmt = conn.prepareStatement("select * from " + "(select rownum rnum,pb_no ,pb_writer, "
						+ "pb_title, pb_content, pb_price, " + "pb_date,pb_readcount, PB_CATEGORY,PB_HASHTAG,PB_LAT,PB_LNG,PB_STATE  from "
						+ "(select * from PURCHASE_BOARD where PB_CONTENT like ? order by PB_NO desc))"
						+ "where rnum>=? and rnum<=?");
				pstmt.setString(1, "%" + condition + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				System.out.println("내용 검색");
			}

			rset = pstmt.executeQuery();

			// DB에서 가져온 데이터를 VO객체에 담습니다.
			while (rset.next()) {
				PurchaseBoardBean boardBean = new PurchaseBoardBean();
				boardBean.setPB_NO(rset.getInt("PB_NO"));
				boardBean.setPB_WRITER(rset.getString("PB_WRITER"));
				boardBean.setPB_TITLE(rset.getString("PB_TITLE"));
				boardBean.setPB_CONTENT(rset.getString("PB_CONTENT"));
				boardBean.setPB_DATE(rset.getDate("PB_DATE"));
				boardBean.setPB_READCOUNT(rset.getInt("PB_READCOUNT"));
				boardBean.setPB_CATEGORY(rset.getInt("PB_CATEGORY"));
				boardBean.setPB_HASHTAG(rset.getString("PB_HASHTAG"));
				boardBean.setPB_LAT(rset.getDouble("PB_LAT"));
				boardBean.setPB_LNG(rset.getDouble("PB_LNG"));
				boardBean.setPB_PRICE(rset.getInt("PB_PRICE"));
				boardBean.setPB_STATE(rset.getInt("PB_STATE"));
				
				boardBeanList.add(boardBean);// 값을 담은 객체를 리스트에 저장합니다.
			}
			
			return boardBeanList;// 값을 담은 객체를 저장한 리스트를 호출한 곳으로
			
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

	// 글 작성
	public int purchaseInsert(PurchaseBoardBean boardBean) {
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

			sql = "INSERT INTO PURCHASE_BOARD " + "(PB_NO, PB_WRITER, PB_TITLE, PB_CONTENT, "
					+ "PB_DATE, PB_READCOUNT, PB_CATEGORY, PB_HASHTAG, PB_PRICE ,PB_STATE, PB_LAT, PB_LNG) " 
					+ "values(?,?,?,?,sysdate,?,?,?,?,?,?,?)";

			// 새로운 글을 등록하는 부분입니다.

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, boardBean.getPB_WRITER());
			pstmt.setString(3, boardBean.getPB_TITLE());
			pstmt.setString(4, boardBean.getPB_CONTENT());
			pstmt.setInt(5, 0);  //read count
			pstmt.setInt(6, boardBean.getPB_CATEGORY());
			pstmt.setString(7, boardBean.getPB_HASHTAG());
			pstmt.setInt(8, boardBean.getPB_PRICE());
			pstmt.setInt(9, 0);  
			pstmt.setDouble(10, boardBean.getPB_LAT());
			pstmt.setDouble(11, boardBean.getPB_LNG());

			result = pstmt.executeUpdate();

			if (result == 0) {
				return 0;
			} else {
				return 1;
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
		return 1;

	}

	// 삭제
	public int purchaseDelete(int boardNum) {
		String sql = "DELETE FROM PURCHASE_BOARD WHERE PB_NO = ?";
		int result = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);

			result = pstmt.executeUpdate();
			
			if (result == 1) {
				result = 1;
			}
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
		return result;
	}

	// 검색 결과 값 숫자 세기
	public int getSearchResultCount(HashMap<String, Object> optionList, String search, String searchOption) {
		int result = 0;
		String option = (String) optionList.get("option"); // 검색옵션

		try {
			conn = ds.getConnection();
			String condition = (String) optionList.get("condition"); // 검색내용
			if (option == null) {
				pstmt = conn.prepareStatement("select count(*) from " + "(select rownum rnum,pb_no ,pb_writer, "
						+ "pb_title, pb_content, pb_price,pb_state, " + "pb_date,pb_readcount from "
						+ "(select * from PURCHASE_BOARD order by PB_NO desc))");
			} else if (option.equals("0")) {
				pstmt = conn.prepareStatement("select count(*) from " + "(select rownum rnum,pb_no ,pb_writer, "
						+ "pb_title, pb_content, pb_price,pb_state, " + "pb_date,pb_readcount from "
						+ "(select * from PURCHASE_BOARD where PB_WRITER like ? order by PB_NO desc))");
				pstmt.setString(1, "%" + condition + "%");
			} else if (option.equals("1")) {
				pstmt = conn.prepareStatement("select count(*) from " + "(select rownum rnum,pb_no ,pb_writer, "
						+ "pb_title, pb_content, pb_price,pb_state, " + "pb_date,pb_readcount from "
						+ "(select * from PURCHASE_BOARD where PB_TITLE like ? order by PB_NO desc))");
				pstmt.setString(1, "%" + condition + "%");
			} else if (option.equals("2")) {
				pstmt = conn.prepareStatement("select count(*) from " + "(select rownum rnum,pb_no ,pb_writer, "
						+ "pb_title, pb_content, pb_price,pb_state, " + "pb_date,pb_readcount from "
						+ "(select * from PURCHASE_BOARD where PB_CONTENT like ? order by PB_NO desc))");
				pstmt.setString(1, "%" + condition + "%");
			}

			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
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
		return result;
	}

	public JSONArray getSearchCategory(int page, int category) {

		JSONArray array = new JSONArray();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + 10 - 1;
		try {
			conn = ds.getConnection();

			String sql = "select * from ("
					+ "select rownum rnum, NUM, WRITER, TITLE, CONTENT, PRICE, READCOUNT, CATEGORY, HASHTAG, STATE, DDATE, IMAGE_URL, BOARD_NAME from("
					+ "select NUM, WRITER, TITLE, CONTENT, PRICE, READCOUNT, CATEGORY, HASHTAG, STATE, DDATE, IMAGE_URL, BOARD_NAME from "
					+ "(select PB_NO NUM, PB_WRITER WRITER, PB_TITLE TITLE, PB_CONTENT CONTENT, PB_PRICE PRICE, PB_READCOUNT READCOUNT, "
					+ " PB_CATEGORY CATEGORY, PB_HASHTAG HASHTAG, PB_STATE STATE, TO_CHAR(PB_DATE, 'YYYY-MM-DD HH24:MI') DDATE, IMAGE_URL, "
					+ "BOARD_NAME from purchase_board inner join IMAGE on PURCHASE_BOARD.PB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'PURCHASE_BOARD')"
					+ "UNION ALL "
					+ "(select SB_NO NUM, SB_WRITER WRITER, SB_TITLE TITLE, SB_CONTENT CONTENT, SB_PRICE PRICE, SB_READCOUNT READCOUNT, "
					+ " SB_CATEGORY CATEGORY, SB_HASHTAG HASHTAG, SB_STATE STATE, TO_CHAR(SB_DATE, 'YYYY-MM-DD HH24:MI') DDATE, IMAGE_URL, "
					+ "BOARD_NAME from sell_board inner join IMAGE on SELL_BOARD.SB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'SELL_BOARD')))"
					+ "where rnum >= ? and rnum <= ? and category = ?";
			
		

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			pstmt.setInt(3, category);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				JSONObject obj = new JSONObject();
				obj.put("num", rset.getInt("NUM"));
				obj.put("writer", rset.getString("WRITER"));
				obj.put("title", rset.getString("TITLE"));
				obj.put("content", rset.getString("CONTENT"));
				obj.put("price", rset.getInt("PRICE"));
				obj.put("image_url", rset.getString("IMAGE_URL").split(" ")[0]);
				obj.put("board_name", rset.getString("BOARD_NAME"));
				obj.put("readcount", rset.getInt("READCOUNT"));
				obj.put("date", rset.getString("DDATE"));
				// obj.put("lat", rset.getDouble("lat"));
				// obj.put("lng", rset.getDouble("lng"));

				array.add(obj);

			}
		} catch (Exception e) {
			System.out.println("getSearchCategory 에러" + e);
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

		return array;
	}
	
	public int getMaxCount() {
		int num = 0;
	
		try {
			conn = ds.getConnection();
			String max_sql = "select max(PB_NO) from PURCHASE_BOARD";
			pstmt = conn.prepareStatement(max_sql);
		
			rset = pstmt.executeQuery();

			if (rset.next()) {
				num = rset.getInt(1) + 1; // 최대값보다 1만큼 큰 값 지정
			} else {
				num = 1;
			}
	}catch(Exception e) {
		System.out.println("getMaxCount에러"+e);
	}
		return num;
	}



}
