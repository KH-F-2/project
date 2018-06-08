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

import com.project101.bean.MessageBoardBean;

public class MessageDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
	int result;

	public MessageDAO() {

		try {

			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {

			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}
	public int msInsert(MessageBoardBean msBoardBean) {
		try {
			conn = ds.getConnection();
			String sql = "insert into message " 
					+ "(MS_NO, MS_SEND, MS_TO, MS_DATE, MS_TITLE, MS_CONTENT ) "
					+ "values(?,?,?,sysdate,?,?)";
			pstmt = conn.prepareStatement(sql);
			System.out.println("MS_NO" + msBoardBean.getMS_NO());
			pstmt.setInt(1, msBoardBean.getMS_NO());
			pstmt.setString(2, msBoardBean.getMS_SEND());
			pstmt.setString(3, msBoardBean.getMS_TO());
			pstmt.setString(4, msBoardBean.getMS_TITLE());
			pstmt.setString(5, msBoardBean.getMS_CONTENT());			
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("msInsert 오류 : " + e);
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int msInsert2(MessageBoardBean msBoardBean) {
		try {
			conn = ds.getConnection();
			
			String select_sql = "select max(MS_NO) from message";			
			pstmt = conn.prepareStatement(select_sql);			
			int num;
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				num = rset.getInt(1)+1;
			} else {
				num = 1;
			}
			
			
			String sql = "insert into message " 
					+ "(MS_NO, MS_SEND, MS_TO, MS_DATE, MS_TITLE, MS_CONTENT ) "
					+ "values(?,?,?,sysdate,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, msBoardBean.getMS_SEND());
			pstmt.setString(3, msBoardBean.getMS_TO());
			pstmt.setString(4, msBoardBean.getMS_TITLE());
			pstmt.setString(5, msBoardBean.getMS_CONTENT());			
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("msInsert2 오류 : " + e);
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<MessageBoardBean> getBoardList(int page, int limit, String id) {
		String board_list_sql = 
				"select * from " 
				+ "(select rownum rnum,MS_NO ,MS_SEND , "
				+ "MS_TO, MS_DATE, MS_TITLE, "
				+ "MS_CONTENT"
				+ " from "
				+		"(select * from message "
				+		"where MS_SEND=? order by MS_NO desc)) "
				+	" where rnum>=? and rnum<=? ";
		
		List<MessageBoardBean> list = new ArrayList<MessageBoardBean>();
							//한 페이지당 10개씩 목록인 경우			1page,2page,3page,...
		int startrow=(page-1) * limit + 1; //읽기 시작할 row 번호 (1,11,21,31,...)
		int endrow=startrow + limit -1;    //읽을 마지막 row 번호(10,20,30,40,...)
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rset = pstmt.executeQuery();
				
			//DB에서 가져온 데이터를 VO객체에 담습니다.
			while(rset.next()) {
				MessageBoardBean board = new MessageBoardBean();
				board.setMS_NO(rset.getInt("MS_NO"));
				board.setMS_SEND(rset.getString("MS_SEND"));
				board.setMS_TO(rset.getString("MS_TO"));
				board.setMS_DATE(rset.getDate("MS_DATE"));
				board.setMS_TITLE(rset.getString("MS_TITLE"));
				board.setMS_CONTENT(rset.getString("MS_CONTENT"));
				list.add(board);//값을 담은 객체를 리스트에 저장합니다.				
			}
			return list;//값을 담은 객체를 저장한 리스트를 호출한 곳으로 
		}catch(Exception ex) {
			System.out.println("getBoardList() 에러 : " + ex);
		}finally {
			if(rset!=null) {
				try {
					rset.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public MessageBoardBean getDetail(int MS_NO) {
		MessageBoardBean boardBean = new MessageBoardBean();
		try {
			String sql = "select * from message where MS_NO=?";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, MS_NO);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				boardBean.setMS_NO(rset.getInt("MS_NO"));
				boardBean.setMS_SEND(rset.getString("MS_SEND"));
				boardBean.setMS_TO(rset.getString("MS_TO"));
				boardBean.setMS_DATE(rset.getDate("MS_DATE"));
				boardBean.setMS_TITLE(rset.getString("MS_TITLE"));
				boardBean.setMS_CONTENT(rset.getString("MS_CONTENT"));
			}
			
		}catch (Exception e ) {
			e.printStackTrace();
		}finally {
			if(rset!=null) {
				try {
					rset.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}		
		return boardBean;
	}

	public List<MessageBoardBean> getBoardToList(int page, int limit, String id) {
		String board_list_sql = 
				"select * from " 
				+ "(select rownum rnum,MS_NO ,MS_SEND , "
				+ "MS_TO, MS_DATE, MS_TITLE, "
				+ "MS_CONTENT"
				+ " from "
				+		"(select * from message "
				+		" order by MS_NO desc)) "
				+	"where MS_TO=? and rnum>=? and rnum<=? ";
		
		List<MessageBoardBean> list = new ArrayList<MessageBoardBean>();
							//한 페이지당 10개씩 목록인 경우			1page,2page,3page,...
		int startrow=(page-1) * limit + 1; //읽기 시작할 row 번호 (1,11,21,31,...)
		int endrow=startrow + limit -1;    //읽을 마지막 row 번호(10,20,30,40,...)
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rset = pstmt.executeQuery();
				
			//DB에서 가져온 데이터를 VO객체에 담습니다.
			while(rset.next()) {
				MessageBoardBean board = new MessageBoardBean();
				board.setMS_NO(rset.getInt("MS_NO"));
				board.setMS_SEND(rset.getString("MS_SEND"));
				board.setMS_TO(rset.getString("MS_TO"));
				board.setMS_DATE(rset.getDate("MS_DATE"));
				board.setMS_TITLE(rset.getString("MS_TITLE"));
				board.setMS_CONTENT(rset.getString("MS_CONTENT"));
				list.add(board);//값을 담은 객체를 리스트에 저장합니다.				
			}
			return list;//값을 담은 객체를 저장한 리스트를 호출한 곳으로 
		}catch(Exception ex) {
			System.out.println("getBoardList() 에러 : " + ex);
		}finally {
			if(rset!=null) {
				try {
					rset.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public int getSendListCount(String id) {
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select count(*) from message where MS_SEND = ?");
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				x = rset.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getSendListCount() 에러 : " + e);
		}finally {
			if(rset!=null) {
				try {
					rset.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return x;
	}

	public int getToListCount(String id) {
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select count(*) from message where MS_TO = ?");
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				x = rset.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getSendListCount() 에러 : " + e);
		}finally {
			if(rset!=null) {
				try {
					rset.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return x;
	}
}
