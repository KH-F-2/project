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

			System.out.println("DB ���� ���� : " + e);
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
		//page:������
						//limit : ������ �� ����� ��
						//board_re_ref desc, board_re_seq asc�� ���� ������ ����
						//�������� �´� rnum�� ������ŭ �������� ������ �Դϴ�.
						
						String Purchase_list_sql = 
								"select * from " 
								+ "(select rownum rnum,pb_no ,pb_writer, "
								+ "pb_title, pb_content, pb_file, "
								+ "board_readcount,board_date from "
								+		"(select * from PURCHASE_BOARD "
								+	"where rnum>=? and rnum<=? ";
						
						List<PurchaseBoardBean> list = new ArrayList<PurchaseBoardBean>();
											//�� �������� 10���� ����� ���			1page,2page,3page,...
						int startrow=(page-1) * limit + 1; //�б� ������ row ��ȣ (1,11,21,31,...)
						int endrow=startrow + limit -1;    //���� ������ row ��ȣ(10,20,30,40,...)
						try {
							conn = ds.getConnection();
							pstmt = conn.prepareStatement(Purchase_list_sql);
							pstmt.setInt(1, startrow);
							pstmt.setInt(2, endrow);
							rset = pstmt.executeQuery();
							
							//DB���� ������ �����͸� VO��ü�� ����ϴ�.
							while(rset.next()) {
								PurchaseBoardBean buyList = new PurchaseBoardBean();
								buyList.setNum(rset.getInt("pb_no"));
								buyList.setWriter(rset.getString("pb_writer"));
								buyList.setTitle(rset.getString("pb_title"));
								buyList.setContent(rset.getString("pb_content"));
								buyList.setFile(rset.getString("pb_file"));
								buyList.setreadcount(rset.getInt("pb_readcount"));
								buyList.setDate(rset.getDate("pb_date"));
								list.add(buyList);//���� ���� ��ü�� ����Ʈ�� �����մϴ�.				
							}
							return list;//���� ���� ��ü�� ������ ����Ʈ�� ȣ���� ������ 
						}catch(Exception ex) {
							System.out.println("getBoardList() ���� : " + ex);
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
	
	
	
	

