package com.project101.board.purchase.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

			System.out.println("DB 연동 에러 : " + e);
			return;
		}
	}
	
	public int getListCount() {
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from BOARD");
			rset = pstmt.executeQuery();

			if (rset.next()) {
				x = rset.getInt(1);
			}
		} catch (Exception e) {
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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

			}
		}
		return x;

	}

	public List<PurchaseBoardBean> getBuyList(int page, int limit) {
		//page:페이지
						//limit : 페이지 당 목록의 수
						//board_re_ref desc, board_re_seq asc에 의해 정렬한 것을
						//조건절에 맞는 rnum의 범위만큼 가져오는 쿼리문 입니다.
						
						String Purchase_list_sql = 
								"select * from " 
								+ "(select rownum rnum,pb_no ,pb_writer, "
								+ "pb_title, pb_content, pb_file, "
								+ "board_readcount,board_date from "
								+		"(select * from PURCHASE_BOARD "
								+	"where rnum>=? and rnum<=? ";
						
						List<PurchaseBoardBean> list = new ArrayList<PurchaseBoardBean>();
											//한 페이지당 10개씩 목록인 경우			1page,2page,3page,...
						int startrow=(page-1) * limit + 1; //읽기 시작할 row 번호 (1,11,21,31,...)
						int endrow=startrow + limit -1;    //읽을 마지막 row 번호(10,20,30,40,...)
						try {
							conn = ds.getConnection();
							pstmt = conn.prepareStatement(Purchase_list_sql);
							pstmt.setInt(1, startrow);
							pstmt.setInt(2, endrow);
							rset = pstmt.executeQuery();
							
							//DB에서 가져온 데이터를 VO객체에 담습니다.
							while(rset.next()) {
								PurchaseBoardBean buyList = new PurchaseBoardBean();
								buyList.setNum(rset.getInt("pb_no"));
								buyList.setWriter(rset.getString("pb_writer"));
								buyList.setTitle(rset.getString("pb_title"));
								buyList.setContent(rset.getString("pb_content"));
								buyList.setFile(rset.getString("pb_file"));
								buyList.setreadcount(rset.getInt("pb_readcount"));
								buyList.setDate(rset.getDate("pb_date"));
								list.add(buyList);//값을 담은 객체를 리스트에 저장합니다.				
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
	
	
	
	

