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

import com.project101.bean.Endhistory;

public class EndhistoryDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
	int result;

	public EndhistoryDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {

			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}

	public Endhistory getDetail(int num) {
		Endhistory eh = new Endhistory();
		try {
			conn = ds.getConnection();
			String sql = "select * from endhistory where EH_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				eh.setEH_NUM(rset.getInt("EH_NUM"));
				eh.setEH_CATEGORY(rset.getString("EH_CATEGORY"));
				eh.setEH_SUBJECT(rset.getString("EH_SUBJECT"));
				eh.setEH_PRICE(rset.getInt("EH_PRICE"));
				eh.setEH_DATE(rset.getDate("EH_DATE"));
				eh.setEH_NAME(rset.getString("EH_NAME"));
				
				return eh;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getDetail 오류 :" + e);
		} finally {
			try {
				if (rset != null)
					rset.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	//글갯수
	public int getListCount() {
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select count(*) from endhistory");
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
	//글목록
	public List<Endhistory> getBoardList(int page, int limit) {
		//page:페이지
				//limit : 페이지 당 목록의 수
				//board_re_ref desc, board_re_seq asc에 의해 정렬한 것을
				//조건절에 맞는 rnum의 범위만큼 가져오는 쿼리문 입니다.
				
				String board_list_sql = 
						"select * from " 
						+ "(select rownum rnum,eh_num ,eh_category, "
						+ "eh_subject, eh_price,eh_date, eh_name "
						+ "from "
						+		"(select * from endhistory "
						+		" order by eh_num desc)) "
						+	"where rnum>=? and rnum<=? ";
				
				List<Endhistory> list = new ArrayList<Endhistory>();
									//한 페이지당 10개씩 목록인 경우			1page,2page,3page,...
				int startrow=(page-1) * limit + 1; //읽기 시작할 row 번호 (1,11,21,31,...)
				int endrow=startrow + limit -1;    //읽을 마지막 row 번호(10,20,30,40,...)
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(board_list_sql);
					pstmt.setInt(1, startrow);
					pstmt.setInt(2, endrow);
					rset = pstmt.executeQuery();
					
					//DB에서 가져온 데이터를 VO객체에 담습니다.
					while(rset.next()) {
						Endhistory eh = new Endhistory();
						eh.setEH_NUM(rset.getInt("EH_NUM"));
						eh.setEH_CATEGORY(rset.getString("EH_CATEGORY"));
						eh.setEH_SUBJECT(rset.getString("EH_SUBJECT"));
						eh.setEH_PRICE(rset.getInt("EH_PRICE"));
						eh.setEH_DATE(rset.getDate("EH_DATE"));
						eh.setEH_NAME(rset.getString("EH_NAME"));
						list.add(eh);//값을 담은 객체를 리스트에 저장합니다.				
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
