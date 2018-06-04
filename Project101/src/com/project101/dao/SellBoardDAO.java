package com.project101.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
		try {
			conn = ds.getConnection();
			
			String sql = "insert into SELL_BOARD " + "(SB_NO, SB_WRITER, SB_PURCHASE_DATE, SB_TITLE, "
					+ "SB_CONTENT, SB_PRICE, SB_DATE, SB_READCOUNT, SB_LAT, SB_LNG, SB_STATE, "
					+ "SB_CATEGORY, SB_HASHTAG) "
					+ "values(?, ?, ?, ?, ?, ?, sysdate, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardBean.getSB_NO());
			pstmt.setString(2, boardBean.getSB_WRITER());
			pstmt.setDate(3, boardBean.getSB_PURCHASE_DATE());
			pstmt.setString(4, boardBean.getSB_TITLE());
			pstmt.setString(5, boardBean.getSB_CONTENT());
			pstmt.setInt(6, boardBean.getSB_PRICE());
			pstmt.setInt(7, 0);
			pstmt.setInt(8, boardBean.getSB_LAT());
			pstmt.setInt(9, boardBean.getSB_LNG());
			pstmt.setInt(10, 0);
			pstmt.setInt(11, boardBean.getSB_CATEGORY());
			pstmt.setString(12, boardBean.getSB_HASHTAG());

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
	} // boardInsert() ----------

	public List<SellBoardBean> getBoardList(int page, int limit) {
		List<SellBoardBean> boardBeanList = new ArrayList<SellBoardBean>();
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try {
			conn = ds.getConnection();
			
			String sql = "select * from " + "(select rownum rnum, SB_NO, SB_WRITER, SB_TITLE, "
					+ "SB_READCOUNT, SB_DATE from " + "(select * from SELL_BOARD order by SB_NO desc)) "
					+ "where rnum>=? and rnum<=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				SellBoardBean boardBean = new SellBoardBean();
				boardBean.setSB_NO(rset.getInt("SB_NO"));
				boardBean.setSB_WRITER(rset.getString("SB_WRITER"));
				boardBean.setSB_TITLE(rset.getString("SB_TITLE"));
				boardBean.setSB_DATE(rset.getDate("SB_DATE"));
				boardBean.setSB_READCOUNT(rset.getInt("SB_READCOUNT"));
				boardBeanList.add(boardBean);
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
		return boardBeanList;
	} // getBoardList() ----------
	
	
	public List<SellBoardBean> getBoardList(int page, int limit, String SB_WRITER) {
		List<SellBoardBean> list=new ArrayList<SellBoardBean>();
		int startrow=(page-1)*limit+1;
		int endrow=startrow+limit-1;
		try {
			conn=ds.getConnection();
			String sql="select * from "
					+ "(select rownum rnum, SB_NO, SB_WRITER, SB_TITLE, "
					+ "SB_READCOUNT, SB_DATE from "
					+ "(select * from SELL_BOARD  WHERE SB_WRITER = ? order by SB_NO desc)) "
					+ " where rnum>=? and rnum<=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, SB_WRITER);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				SellBoardBean boardBean=new SellBoardBean();
				boardBean.setSB_NO(rset.getInt("SB_NO"));
				boardBean.setSB_WRITER(rset.getString("SB_WRITER"));
				boardBean.setSB_TITLE(rset.getString("SB_TITLE"));
				boardBean.setSB_DATE(rset.getDate("SB_DATE"));
				boardBean.setSB_READCOUNT(rset.getInt("SB_READCOUNT"));
				list.add(boardBean);
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
		
		return list;
	}	// getBoardList() ----------


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
					+ "ON SELL_BOARD.SB_CATEGORY = CATEGORY.CATEGORY_ID "
					+ "WHERE SELL_BOARD.SB_NO=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				obj.put("SB_NO", rset.getInt("SB_NO"));
				obj.put("SB_WRITER", rset.getString("SB_WRITER"));
				obj.put("SB_PURCHASE_DATE", rset.getDate("SB_PURCHASE_DATE"));
				obj.put("SB_TITLE", rset.getString("SB_TITLE"));
				obj.put("SB_CONTENT", rset.getString("SB_CONTENT"));
				obj.put("SB_PRICE", rset.getInt("SB_PRICE"));
				obj.put("SB_DATE", rset.getDate("SB_DATE"));
				obj.put("SB_READCOUNT", rset.getInt("SB_READCOUNT"));
				obj.put("SB_LAT", rset.getInt("SB_LAT"));
				obj.put("SB_LNG", rset.getInt("SB_LNG"));
				obj.put("SB_STATE", rset.getInt("SB_STATE"));
				obj.put("SB_CATEGORY", rset.getString("CATEGORY_NAME"));
				obj.put("SB_HASHTAG", rset.getString("SB_HASHTAG"));
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
			
			String sql = "update SELL_BOARD " + "set SB_WRITER=?, SB_PURCHASE_DATE=?, SB_TITLE=?, SB_CONTENT=?, "
					+ "SB_PRICE=?, SB_LAT=?, SB_LNG=?, SB_CATEGORY=?, SB_HASHTAG=? "
					+ "where SB_NO=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardBean.getSB_WRITER());
			pstmt.setDate(2, boardBean.getSB_PURCHASE_DATE());
			pstmt.setString(3, boardBean.getSB_TITLE());
			pstmt.setString(4, boardBean.getSB_CONTENT());
			pstmt.setInt(5, boardBean.getSB_PRICE());
			pstmt.setInt(6, boardBean.getSB_LAT());
			pstmt.setInt(7, boardBean.getSB_LNG());
			pstmt.setInt(8, boardBean.getSB_CATEGORY());
			pstmt.setString(9, boardBean.getSB_HASHTAG());
			pstmt.setInt(10, boardBean.getSB_NO());

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
			
			sb.append("select rownum rnum, SB_NO, SB_WRITER, SB_TITLE, " + "SB_READCOUNT, SB_DATE from "
					+ "(select * from SELL_BOARD where ");
			
			if (item.equals("title")) {
				sb.append("SB_TITLE LIKE ? ");
			} else if (item.equals("content")) {
				sb.append("SB_CONTENT LIKE ? ");
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
				SellBoardBean sellboard = new SellBoardBean();
				sellboard.setSB_NO(rset.getInt("SB_NO"));
				sellboard.setSB_WRITER(rset.getString("SB_WRITER"));
				sellboard.setSB_TITLE(rset.getString("SB_TITLE"));
				sellboard.setSB_DATE(rset.getDate("SB_DATE"));
				sellboard.setSB_READCOUNT(rset.getInt("SB_READCOUNT"));
				boardBeanList.add(sellboard);
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
}
