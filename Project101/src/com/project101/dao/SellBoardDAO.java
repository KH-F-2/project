package com.project101.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.project101.bean.SellBoardBean;

public class SellBoardDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
	int result;

	public SellBoardDAO() {

		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	} // SellBoardDAO() --------

	public int getListCount() {
		try {
			conn = ds.getConnection();

			String sql = "SELECT COUNT(*) FROM SELL_BOARD";

			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			if (rset.next())
				result = rset.getInt(1);

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
	} // getListCount() -------

	public int getNextBoardNo() {
		int num = 0;
		try {
			conn = ds.getConnection();

			String sql = "select max(SB_NO) from SELL_BOARD";

			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				num = rset.getInt(1) + 1;
			} else {
				num = 1;
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
		return num;
	}

	public int boardInsert(SellBoardBean boardBean) {
		int num = 0;
		try {
			conn = ds.getConnection();

			String sql = "insert into SELL_BOARD " + "(SB_NO, SB_WRITER, SB_TITLE, "
					+ "SB_CONTENT, SB_PRICE, SB_DATE, SB_READCOUNT, SB_LAT, SB_LNG, SB_STATE, "
					+ "SB_CATEGORY, SB_HASHTAG) " + "values(?, ?, ?, ?, ?, sysdate, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardBean.getSB_NO());
			pstmt.setString(2, boardBean.getSB_WRITER());
			pstmt.setString(3, boardBean.getSB_TITLE());
			pstmt.setString(4, boardBean.getSB_CONTENT());
			pstmt.setInt(5, boardBean.getSB_PRICE());
			pstmt.setInt(6, 0);
			pstmt.setDouble(7, boardBean.getSB_LAT());
			pstmt.setDouble(8, boardBean.getSB_LNG());
			pstmt.setInt(9, 0);
			pstmt.setInt(10, boardBean.getSB_CATEGORY());
			pstmt.setString(11, boardBean.getSB_HASHTAG());

			result = pstmt.executeUpdate();
			
			// insert 성공시 history 도 insert
			if(result == 1) {
				pstmt.close();
				
				pstmt = conn.prepareStatement("select max(SH_NO) from SELL_HISTORY");
				rset = pstmt.executeQuery();

				if (rset.next()) {
					num = rset.getInt(1) + 1;
				} else {
					num = 1;
				}
				
				rset.close();
				pstmt.close();
				
				pstmt = conn.prepareStatement("insert into SELL_HISTORY (SH_NO, SH_BOARD_NO, "
						+ "SH_MEMBER, SH_DATE, SH_STATE) values("
						+ "?, ?, ?, sysdate, 0)");
				
				pstmt.setInt(1, num);
				pstmt.setInt(2, boardBean.getSB_NO());
				pstmt.setString(3, boardBean.getSB_WRITER());
				
				pstmt.executeUpdate();
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
	} // boardInsert() ----------

	public JSONArray getBoardList(int page, double lat, double lng) {
		JSONArray array = new JSONArray();
		int startrow = (page - 1) * 20 + 1;
		int endrow = startrow + 20 - 1;
		
		try {
			conn = ds.getConnection();

			String sql = "select * from (select rownum rnum, NUM, WRITER, TITLE, content, price, READCOUNT, DDATE, distance, lat, lng, IMAGE_URL, BOARD_NAME from "
					+ "(select NUM, WRITER, TITLE, content, price, READCOUNT, DDATE, distance, lat, lng, IMAGE_URL, BOARD_NAME from " 
					+ "(select SB_NO as NUM, SB_WRITER as WRITER, SB_TITLE as TITLE, SB_CONTENT content, sb_price price, SB_READCOUNT as READCOUNT, SB_DATE as DDATE, "
					+ "sqrt(power((?-SB_LAT),2) + power((?-SB_LNG),2)) as distance, sb_lat lat, sb_lng lng, IMAGE_URL, BOARD_NAME from " 
					+ "(select * from SELL_BOARD inner join IMAGE on SELL_BOARD.SB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'SELL_BOARD')) " 
					+ "UNION ALL (select PB_NO as NUM, PB_WRITER as WRITER, PB_TITLE as TITLE, pb_content content, pb_price price, PB_READCOUNT as READCOUNT, PB_DATE as DDATE, "
					+ "sqrt(power((?-PB_LAT),2) + power((?-PB_LNG),2)) as distance, pb_lat lat, pb_lng lng, IMAGE_URL, BOARD_NAME from " 
					+ "(select * from PURCHASE_BOARD inner join IMAGE on PURCHASE_BOARD.PB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'PURCHASE_BOARD')) order by distance)) "
					+ "where rnum >= ? and rnum <= ?";
			
			/*String sql = "select * from " + "(select rownum rnum, SB_NO, SB_TITLE, "
					+ "SB_READCOUNT, SB_DATE, SB_LAT, SB_LNG from " + "(select * from SELL_BOARD order by SB_NO desc)) "
					+ "where rnum>=? and rnum<=?";*/

			pstmt = conn.prepareStatement(sql);

			pstmt.setDouble(1, lat);
			pstmt.setDouble(2, lng);
			pstmt.setDouble(3, lat);
			pstmt.setDouble(4, lng);
			pstmt.setInt(5, startrow);
			pstmt.setInt(6, endrow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				JSONObject obj = new JSONObject();
	            obj.put("num", rset.getInt("NUM"));
	            obj.put("writer", rset.getString("WRITER"));
	            obj.put("title", rset.getString("TITLE"));
	            obj.put("content", rset.getString("CONTENT"));
	            obj.put("price", rset.getInt("PRICE"));
	            obj.put("readcount", rset.getInt("READCOUNT"));
	            obj.put("date", rset.getDate("DDATE").toString());
	            obj.put("lat", rset.getDouble("lat"));
	            obj.put("lng", rset.getDouble("lng"));
	            obj.put("distance", rset.getDouble("DISTANCE"));
	            obj.put("image_url", rset.getString("IMAGE_URL").split(" ")[0]);
	            obj.put("board_name", rset.getString("BOARD_NAME"));
				
				array.add(obj);
			}
			
			/*rset.close();
			pstmt.close();
			
			pstmt = conn.prepareStatement("select * from IMAGE where BOARD_NO = ?, BOARD_NAME = 'SELL_BOARD'");
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				ImageBean imageBean = new ImageBean();
				imageBean.setBOARD_NO(rset.getInt("BOARD_NO"));
				imageBean.setBOARD_NAME(rset.getString("BOARD_NAME"));
				imageBean.setIMAGE_URL(rset.getString("IMAGE_URL"));
				imageBeanList.add(imageBean);
			}*/
			
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
		return array;
	} // getBoardList() ----------

	public List<SellBoardBean> getBoardList(int page, int limit, String SB_WRITER) {
		List<SellBoardBean> list = new ArrayList<SellBoardBean>();
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try {
			conn = ds.getConnection();
			String sql = "select * from " + "(select rownum rnum, SB_NO, SB_WRITER, SB_TITLE, "
					+ "SB_READCOUNT, SB_DATE from "
					+ "(select * from SELL_BOARD  WHERE SB_WRITER = ? order by SB_NO desc)) "
					+ " where rnum>=? and rnum<=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, SB_WRITER);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				SellBoardBean boardBean = new SellBoardBean();
				boardBean.setSB_NO(rset.getInt("SB_NO"));
				boardBean.setSB_WRITER(rset.getString("SB_WRITER"));
				boardBean.setSB_TITLE(rset.getString("SB_TITLE"));
				boardBean.setSB_DATE(rset.getDate("SB_DATE"));
				boardBean.setSB_READCOUNT(rset.getInt("SB_READCOUNT"));
				list.add(boardBean);
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

		return list;
	} // getBoardList() ----------
	
	
	public JSONArray getBoardList(int page, int limit) {
		JSONArray arr = new JSONArray();
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try {
			conn = ds.getConnection();

			String sql = "select * from " + "(select rownum rnum, SB_NO, SB_WRITER, SB_TITLE, "
					+ "SB_READCOUNT, TO_CHAR(SB_DATE, 'YYYY-MM-DD HH24:MI') as SB_DATE, SB_HASHTAG, SB_STATE, SB_LAT, SB_LNG, SB_PRICE, IMAGE_URL, BOARD_NAME "
					+ "from (select * from SELL_BOARD inner join IMAGE on SELL_BOARD.SB_NO = IMAGE.BOARD_NO " 
					+ "where IMAGE.BOARD_NAME = 'SELL_BOARD')) "
					+ "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				JSONObject obj = new JSONObject();
				obj.put("SB_NO", rset.getInt("SB_NO"));
				obj.put("SB_WRITER", rset.getString("SB_WRITER"));
				obj.put("SB_TITLE", rset.getString("SB_TITLE"));
				obj.put("SB_READCOUNT", rset.getInt("SB_READCOUNT"));
				obj.put("SB_DATE", rset.getString("SB_DATE"));
				obj.put("SB_HASHTAG", rset.getString("SB_HASHTAG"));
				obj.put("SB_STATE", rset.getInt("SB_STATE"));
				obj.put("SB_LAT", rset.getDouble("SB_LAT"));
				obj.put("SB_LNG", rset.getDouble("SB_LNG"));
				obj.put("SB_PRICE", rset.getInt("SB_PRICE"));
				obj.put("IMAGE_URL", rset.getString("IMAGE_URL").split(" ")[0]);
				obj.put("BOARD_NAME", rset.getString("BOARD_NAME"));
				arr.add(obj);
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
		return arr;
	} // getBoardList() ----------
	
	public int getListCount(String SB_WRITER) {
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from sell_board where SB_WRITER = ? ");
			pstmt.setString(1, SB_WRITER);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				x = rset.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getListCount()에러: ");
			e.printStackTrace();
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
		}
		return x;
	}

	public void setReadCountUpdate(int num) {
		try {
			conn = ds.getConnection();

			String sql = "update SELL_BOARD set SB_READCOUNT=SB_READCOUNT+1 where SB_NO=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.executeUpdate();
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
	} // setReadCountUpdate() ---------

	public JSONObject getDetail(int num) {
		JSONObject obj = new JSONObject();
		try {
			conn = ds.getConnection();

			String sql = "select * from SELL_BOARD inner join CATEGORY "
					+ "ON SELL_BOARD.SB_CATEGORY = CATEGORY.CATEGORY_ID " + "WHERE SELL_BOARD.SB_NO=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				obj.put("NO", rset.getInt("SB_NO"));
				obj.put("WRITER", rset.getString("SB_WRITER"));
				obj.put("TITLE", rset.getString("SB_TITLE"));
				obj.put("CONTENT", rset.getString("SB_CONTENT"));
				obj.put("PRICE", rset.getInt("SB_PRICE"));
				obj.put("DATE", rset.getDate("SB_DATE"));
				obj.put("READCOUNT", rset.getInt("SB_READCOUNT"));
				obj.put("LAT", rset.getDouble("SB_LAT"));
				obj.put("LNG", rset.getDouble("SB_LNG"));
				obj.put("STATE", rset.getInt("SB_STATE"));
				obj.put("CATEGORY", rset.getString("CATEGORY_NAME"));
				obj.put("HASHTAG", rset.getString("SB_HASHTAG"));
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
		return obj;
	} // getDetail() -----------

	public int boardDelete(int num) {
		try {
			conn = ds.getConnection();

			String sql = "delete from SELL_BOARD where SB_NO=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);

			int result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("boardDelete 성공");
				return result;
			}
		} catch (Exception e) {
			System.out.println("SellBoardDAO - boardDelete() 에러 :" + e);
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
		return 0;
	} // boardDelete() ---------

	public int boardModify(SellBoardBean boardBean) {
		try {
			conn = ds.getConnection();

			String sql = "update SELL_BOARD " + "set SB_WRITER=?, SB_TITLE=?, SB_CONTENT=?, "
					+ "SB_PRICE=?, SB_LAT=?, SB_LNG=?, SB_CATEGORY=?, SB_HASHTAG=? " + "where SB_NO=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boardBean.getSB_WRITER());
			pstmt.setString(2, boardBean.getSB_TITLE());
			pstmt.setString(3, boardBean.getSB_CONTENT());
			pstmt.setInt(4, boardBean.getSB_PRICE());
			pstmt.setDouble(5, boardBean.getSB_LAT());
			pstmt.setDouble(6, boardBean.getSB_LNG());
			pstmt.setInt(7, boardBean.getSB_CATEGORY());
			pstmt.setString(8, boardBean.getSB_HASHTAG());
			pstmt.setInt(9, boardBean.getSB_NO());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("boardmodify() 에러 :" + e);
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
	} // boardModify -------------

  	public Map getSearchList(int page, int category, String keyword, String item, double lat, double lng) {
  		Map<String, Object> resultMap = new HashMap<String, Object>();
  		JSONArray resultArr = new JSONArray();
  		String contentStr = "%" + keyword + "%";
		int startrow = (page - 1) * 20 + 1;
		int endrow = startrow + 20 - 1;
		int listcount = 0;

  		try {
			conn = ds.getConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("select rownum rnum, NUM, WRITER, TITLE, content, price, READCOUNT, DDATE, category, hashtag, distance, lat, lng, IMAGE_URL, BOARD_NAME from "
					+ "(select NUM, WRITER, TITLE, content, price, READCOUNT, DDATE, category, hashtag, distance, lat, lng, IMAGE_URL, BOARD_NAME from "
					+ "(select SB_NO NUM, SB_WRITER WRITER, SB_TITLE TITLE, SB_CONTENT content, sb_price price, SB_READCOUNT READCOUNT, SB_DATE DDATE"
					+ ", sb_category category, sb_hashtag hashtag, sqrt(power((?-SB_LAT),2) + power((?-SB_LNG),2)) distance, sb_lat lat, sb_lng lng, IMAGE_URL, BOARD_NAME from "
					+ "(select * from SELL_BOARD inner join IMAGE on SELL_BOARD.SB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'SELL_BOARD')) "
					+ "UNION ALL "
					+ "(select PB_NO NUM, PB_WRITER WRITER, PB_TITLE TITLE, pb_content content, pb_price price, PB_READCOUNT READCOUNT, PB_DATE DDATE"
					+ ", pb_category category, pb_hashtag hashtag, sqrt(power((?-PB_LAT),2) + power((?-PB_LNG),2)) distance, pb_lat lat, pb_lng lng, IMAGE_URL, BOARD_NAME from "
					+ "(select * from PURCHASE_BOARD inner join IMAGE on PURCHASE_BOARD.PB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'PURCHASE_BOARD')) order by distance) where ");
			
			if (!keyword.equals("")) {
				if (item.equals("title")) {
					sb.append("title LIKE ? ");
				} else if (item.equals("content")) {
					sb.append("content LIKE ? ");
				} else if (item.equals("hashtag")) {
					sb.append("hashtag LIKE ? ");
				} else {
					sb.append("title LIKE ? OR content LIKE ? ");
				} 
				
				if (category != 0) {				
					sb.append("and category=? ");
				}
			} else {
				sb.append("category=? ");
			}
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setDouble(1, lat);
			pstmt.setDouble(2, lng);
			pstmt.setDouble(3, lat);
			pstmt.setDouble(4, lng);
			
			if (!keyword.equals("")) {
				pstmt.setString(5, contentStr);

				if (category != 0) {
					if (item.equals("title_content")) {
						pstmt.setString(6, contentStr);
						pstmt.setInt(7, category);				
					} else {	
						pstmt.setInt(6, category);
					}				
				}
			} else {
				pstmt.setInt(5, category);
			}
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				listcount++;
			}
			
			resultMap.put("listcount", listcount);
			System.out.println("here : " + listcount);
			
			rset.close();
			pstmt.close();

			sb.insert(0, "select * from (");
			sb.append(") where rnum>=? and rnum<=? order by distance");

			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setDouble(1, lat);
			pstmt.setDouble(2, lng);
			pstmt.setDouble(3, lat);
			pstmt.setDouble(4, lng);
			
			if (!keyword.equals("")) {
				pstmt.setString(5, contentStr);
				if (category != 0) {
					if (item.equals("title_content")) {
						pstmt.setString(6, contentStr);
						pstmt.setInt(7, category);
						pstmt.setInt(8, startrow);
						pstmt.setInt(9, endrow);
					} else {
						pstmt.setInt(6, category);
						pstmt.setInt(7, startrow);
						pstmt.setInt(8, endrow);
					}
				} else {
					pstmt.setInt(6, startrow);
					pstmt.setInt(7, endrow);
				}				
			} else {
				pstmt.setInt(5, category);
				pstmt.setInt(6, startrow);
				pstmt.setInt(7, endrow);
			}
			
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
				jsonObj.put("distance", rset.getDouble("DISTANCE"));
				jsonObj.put("image_url", rset.getString("IMAGE_URL").split(" ")[0]);
				jsonObj.put("board_name", rset.getString("BOARD_NAME"));
	            
				resultArr.add(jsonObj);
			}
			resultMap.put("jsonArr", resultArr);
			
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
  		
  		return resultMap;
  	}
  
	public Map<String, Object> getSearchList(int page, int limit, String content, String item) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SellBoardBean> boardBeanList = new ArrayList<SellBoardBean>();
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		int listcount = 0;
		String contentStr = "%" + content + "%";
		try {
			conn = ds.getConnection();

			StringBuffer sb = new StringBuffer();

			sb.append("select rownum rnum, SB_NO, SB_WRITER, SB_TITLE, "
					+ "SB_READCOUNT, SB_DATE, SB_LAT, SB_LNG from "
					+ "(select * from SELL_BOARD where ");

			if (item.equals("title")) {
				sb.append("SB_TITLE LIKE ? ");
			} else if (item.equals("content")) {
				sb.append("SB_CONTENT LIKE ? ");
			} else if (item.equals("hashtag")) {
				sb.append("SB_HASHTAG like ? ");
			} else {
				sb.append("SB_TITLE LIKE ? OR SB_CONTENT LIKE ? ");
			}
			sb.append("order by SB_NO desc)");

			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, contentStr);

			if (item.equals("title_content")) {
				pstmt.setString(2, contentStr);
			}
			rset = pstmt.executeQuery();

			while (rset.next()) {
				listcount++;
			}
			map.put("listcount", listcount);

			rset.close();
			pstmt.close();

			sb.insert(0, "select * from (");
			sb.append(") where rnum>=? and rnum<=?");

			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, contentStr);

			if (item.equals("title_content")) {
				pstmt.setString(2, contentStr);
				pstmt.setInt(3, startrow);
				pstmt.setInt(4, endrow);
			} else {
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
			}
			rset = pstmt.executeQuery();

			while (rset.next()) {
				SellBoardBean boardBean = new SellBoardBean();
				boardBean.setSB_NO(rset.getInt("SB_NO"));
				boardBean.setSB_WRITER(rset.getString("SB_WRITER"));
				boardBean.setSB_TITLE(rset.getString("SB_TITLE"));
				boardBean.setSB_DATE(rset.getDate("SB_DATE"));
				boardBean.setSB_READCOUNT(rset.getInt("SB_READCOUNT"));
				boardBean.setSB_LAT(rset.getDouble("SB_LAT"));
				boardBean.setSB_LNG(rset.getDouble("SB_LNG"));
				boardBeanList.add(boardBean);
			}
			map.put("boardBeanList", boardBeanList);
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
		return map;
	}

	public ArrayList<SellBoardBean> getimage(String id) {
		ArrayList<SellBoardBean> getimage = new ArrayList<SellBoardBean>();
		try {
			conn=ds.getConnection();
			String sql="select B.SB_TITLE, B.SB_CONTENT, A.IMAGE_URL, B.SB_NO " + 
					"from image A, sell_board B " + 
					"where B.sb_no = A.board_no and B.sb_writer = ? ";
					
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,id);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				SellBoardBean boardBean=new SellBoardBean();
				boardBean.setSB_NO(rset.getInt("SB_NO"));
				boardBean.setSB_CONTENT(rset.getString("SB_CONTENT"));
				boardBean.setSB_TITLE(rset.getString("SB_TITLE"));
				boardBean.setIMAGE_URL(rset.getString("IMAGE_URL"));
				getimage.add(boardBean);
			}
			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					if(pstmt!=null)	pstmt.close();
					if(conn!=null)		conn.close();
					if(rset!=null)		rset.close();
				}catch(Exception e) {e.printStackTrace();}
			}
		
		return getimage;
	}

	public int tradeItem(int SB_NO, String id) {
	      int state = 1;
	      try {
	         conn = ds.getConnection();
	         
	         pstmt = conn.prepareStatement("select SB_STATE from SELL_BOARD where SB_NO = ?");
	         pstmt.setInt(1, SB_NO);
	         rset = pstmt.executeQuery();
	         
	         if (rset.next()) {
	            state = rset.getInt(1);
	         }
	         
	         // 구매 신청이 완료된 게시물 일시 return 0
	         if(state == 1) {
	            return 0;
	         }
	         
	         pstmt = conn.prepareStatement("update SELL_BOARD set SB_STATE=1 where SB_NO=?");
	         pstmt.setInt(1, SB_NO);
	         
	         result = pstmt.executeUpdate();
	         pstmt.close();
	         
	         pstmt = conn.prepareStatement("update SELL_HISTORY set SH_STATE=1, "
	               + "SH_OPPONENT=? where SH_BOARD_NO=?");
	         
	         pstmt.setString(1, id);
	         pstmt.setInt(2, SB_NO);
	         
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

	public Map<String, Object> getMyList(String id, int page) {
		Map<String, Object> resultMap = new HashMap<>();
  		JSONArray jsonArr = new JSONArray();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + 10 - 1;
		int listCount = 0;
		
  		try {
			conn = ds.getConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("select rownum rnum, NUM, WRITER, TITLE, content, price, READCOUNT, DDATE, category, hashtag, lat, lng, IMAGE_URL, BOARD_NAME from "
					+ "(select NUM, WRITER, TITLE, content, price, READCOUNT, DDATE, category, hashtag, lat, lng, IMAGE_URL, BOARD_NAME from "
					+ "(select SB_NO NUM, SB_WRITER WRITER, SB_TITLE TITLE, SB_CONTENT content, sb_price price, SB_READCOUNT READCOUNT, SB_DATE DDATE"
					+ ", sb_category category, sb_hashtag hashtag, sb_lat lat, sb_lng lng, IMAGE_URL, BOARD_NAME from "
					+ "(select * from SELL_BOARD inner join IMAGE on SELL_BOARD.SB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'SELL_BOARD')) "
					+ "UNION ALL "
					+ "(select PB_NO NUM, PB_WRITER WRITER, PB_TITLE TITLE, pb_content content, pb_price price, PB_READCOUNT READCOUNT, PB_DATE DDATE"
					+ ", pb_category category, pb_hashtag hashtag, pb_lat lat, pb_lng lng, IMAGE_URL, BOARD_NAME from "
					+ "(select * from PURCHASE_BOARD inner join IMAGE on PURCHASE_BOARD.PB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'PURCHASE_BOARD'))) where  writer=? order by ddate");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				listCount++;
			}
			
			rset.close();
			pstmt.close();
			
			sb.insert(0, "select * from (");
			sb.append(") where rnum>=? and rnum<=?");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			
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
			resultMap.put("listCount", listCount);
			resultMap.put("jsonArr", jsonArr);
			
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
  		
  		return resultMap;
	}

	}
