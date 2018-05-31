package com.project101.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.project101.bean.Trade;

public class TradeDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
	int result;

	public TradeDAO() {

		try {

			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {

			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}
	
	//글 갯수 구하기
	public int getListCount() {
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select count(*) from trade");
			rset = pstmt.executeQuery();

			if (rset.next()) {
				x = rset.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getListCount() 에러 : " + e);
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

	//글 목록 보기
	public List<Trade> getBoardList(int page, int limit, String id) {
		//page:페이지
				//limit : 페이지 당 목록의 수
				//board_re_ref desc, board_re_seq asc에 의해 정렬한 것을
				//조건절에 맞는 rnum의 범위만큼 가져오는 쿼리문 입니다.
				
				String sql = 
						"select * from " 
						+ "(select rownum rnum,tr_num ,tr_category, "
						+ "tr_subject, tr_price, tr_date, "
						+ "tr_name,tr_content "
						+ "from "
						+		"(select * from trade where tr_name=?"
						+		" order by tr_num )) "
						+	"where rnum>=? and rnum<=? ";
				
				List<Trade> list = new ArrayList<Trade>();
									//한 페이지당 10개씩 목록인 경우			1page,2page,3page,...
				int startrow=(page-1) * limit + 1; //읽기 시작할 row 번호 (1,11,21,31,...)
				int endrow=startrow + limit -1;    //읽을 마지막 row 번호(10,20,30,40,...)
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.setInt(2, startrow);
					pstmt.setInt(3, endrow);
					rset = pstmt.executeQuery();
					
					//DB에서 가져온 데이터를 VO객체에 담습니다.
					while(rset.next()) {
						Trade tr = new Trade();
						tr.setTR_NUM(rset.getInt("TR_NUM"));
						tr.setTR_CATEGORY(rset.getString("TR_CATEGORY"));
						tr.setTR_SUBJECT(rset.getString("TR_SUBJECT"));
						tr.setTR_PRICE(rset.getInt("TR_PRICE"));
						tr.setTR_DATE(rset.getDate("TR_DATE"));
						tr.setTR_NAME(rset.getString("TR_NAME"));
						tr.setTR_CONTENT(rset.getString("TR_CONTENT"));
						
						list.add(tr);//값을 담은 객체를 리스트에 저장합니다.				
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

	public Trade getDetail(int num) {
		Trade tr = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(
					"select * from trade where TR_NUM = ?");
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				tr = new Trade();
				tr.setTR_NUM(rset.getInt("TR_NUM"));
				tr.setTR_CATEGORY(rset.getString("TR_CATEGORY"));
				tr.setTR_SUBJECT(rset.getString("TR_SUBJECT"));
				tr.setTR_PRICE(rset.getInt("TR_PRICE"));
				tr.setTR_DATE(rset.getDate("TR_DATE"));
				tr.setTR_NAME(rset.getString("TR_NAME"));
				tr.setTR_CONTENT(rset.getString("TR_CONTENT"));
			}
			return tr;
		}catch (Exception e ) {
			System.out.println("getDetailAction 에러 :" + e);
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

	public List<Trade> getBoardAdminList(int page, int limit, String id) {
		//page:페이지
		//limit : 페이지 당 목록의 수
		//board_re_ref desc, board_re_seq asc에 의해 정렬한 것을
		//조건절에 맞는 rnum의 범위만큼 가져오는 쿼리문 입니다.
		String sql = 
				"select * from " 
				+ "(select rownum rnum,tr_num ,tr_category, "
				+ "tr_subject, tr_price, tr_date, "
				+ "tr_name,tr_content "
				+ "from "
				+		"(select * from trade"
				+		" order by tr_num )) "
				+	"where rnum>=? and rnum<=? ";
		
		List<Trade> list = new ArrayList<Trade>();
							//한 페이지당 10개씩 목록인 경우			1page,2page,3page,...
		int startrow=(page-1) * limit + 1; //읽기 시작할 row 번호 (1,11,21,31,...)
		int endrow=startrow + limit -1;    //읽을 마지막 row 번호(10,20,30,40,...)
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rset = pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO객체에 담습니다.
			while(rset.next()) {
				Trade tr = new Trade();
				tr.setTR_NUM(rset.getInt("TR_NUM"));
				tr.setTR_CATEGORY(rset.getString("TR_CATEGORY"));
				tr.setTR_SUBJECT(rset.getString("TR_SUBJECT"));
				tr.setTR_PRICE(rset.getInt("TR_PRICE"));
				tr.setTR_DATE(rset.getDate("TR_DATE"));
				tr.setTR_NAME(rset.getString("TR_NAME"));
				tr.setTR_CONTENT(rset.getString("TR_CONTENT"));
				
				list.add(tr);//값을 담은 객체를 리스트에 저장합니다.				
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

}
