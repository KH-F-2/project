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
						//board_re_ref desc, board_re_seq asc�� ���� ����� ���
						//����� �´� rnum�� �����ŭ ������ ��� �Դϴ�.
						
						String Purchase_list_sql = 
								"select * from " 
								+ "(select rownum rnum,pb_no ,pb_writer, "
								+ "pb_title, pb_content, pb_file, "
								+ "board_readcount,board_date from "
								+		"(select * from PURCHASE_BOARD "
								+	"where rnum>=? and rnum<=? ";
						
						List<PurchaseBoardBean> list = new ArrayList<PurchaseBoardBean>();
											//�� ������� 10���� ����� ���			1page,2page,3page,...
						int startrow=(page-1) * limit + 1; //�б� ������ row ��ȣ (1,11,21,31,...)
						int endrow=startrow + limit -1;    //��� ���� row ��ȣ(10,20,30,40,...)
						try {
							conn = ds.getConnection();
							pstmt = conn.prepareStatement(Purchase_list_sql);
							pstmt.setInt(1, startrow);
							pstmt.setInt(2, endrow);
							rset = pstmt.executeQuery();
							
							//DB���� ����� �����͸� VO��ü�� ���ϴ�.
							while(rset.next()) {
								PurchaseBoardBean buyList = new PurchaseBoardBean();
								buyList.setNum(rset.getInt("pb_no"));
								buyList.setWriter(rset.getString("pb_writer"));
								buyList.setTitle(rset.getString("pb_title"));
								buyList.setContent(rset.getString("pb_content"));
								buyList.setFile(rset.getString("pb_file"));
								buyList.setreadcount(rset.getInt("pb_readcount"));
								buyList.setDate(rset.getDate("pb_date"));
								list.add(buyList);//��� ��� ��ü�� ����Ʈ�� �����մϴ�.				
							}
							return list;//��� ��� ��ü�� ������ ����Ʈ�� ȣ���� ����� 
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
	
	

	//수정
	public boolean isBuyWriter(int num, String id) {
		String buy_sql = "select * from PURCHASE_BOARD where PB_NO=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(buy_sql);
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			rset.next();
			if(id.equals(rset.getString("PB_WRITER"))) {
				return true;				
			}
		}catch (Exception e) {
			System.out.println("isBuyWriter 에러 : " + e);
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
		return false;	
	}

	//수정
	public boolean buyModify(PurchaseBoardBean buydata) {
		String sql = 
				"update purchase_board set PB_TITLE =?, "
			+	"PB_CONTENT = ? "
			+	"where PB_NO = ? ";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buydata.getTitle());
			pstmt.setString(2, buydata.getContent());
			pstmt.setInt(3, buydata.getNum());
			pstmt.executeUpdate();
		
		return true;
	}catch (Exception e) {
		System.out.println("buyModify 에러 : " + e);
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
	return false;
	}

	//디테일
	//조회수 readcount 추가
	public void setReadCountUpdate(int num) {
		String sql = "update purchase_board "
				+	"set pb_readcount = pb_readcount+1 "
				+	"where PB_NO = ?";
		try {
			conn = ds.getConnection();			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeQuery();
		}catch(Exception e) {
			System.out.println("setReadCountUpdate() 에러 : " + e);
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
	}
	public PurchaseBoardBean getDetail(int num) {
		PurchaseBoardBean buydata = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(
					"select * from purchase_board where PB_NO = ?");
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				buydata = new PurchaseBoardBean();
				buydata.setNum(rset.getInt("PB_NUM"));
				buydata.setWriter(rset.getString("PB_WRITER"));
				buydata.setTitle(rset.getString("PB_TITLE"));
				buydata.setContent(rset.getString("PB_CONTENT"));
				buydata.setFile(rset.getString("PB_FILE"));	
				buydata.setreadcount(rset.getInt("PB_READCOUN"));
				buydata.setDate(rset.getDate("PB_DATE"));
			}
			return buydata;
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
	
	//글 갯수 구하기
	public int getListCount() {
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select count(*) from PURCHASE_BOARD");
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
	public List<PurchaseBoardBean> getBuyList(int page, int limit) {
		//page:페이지
				//limit : 페이지 당 목록의 수
				//board_re_ref desc, board_re_seq asc에 의해 정렬한 것을
				//조건절에 맞는 rnum의 범위만큼 가져오는 쿼리문 입니다.
				
				String Purchase_list_sql = 
						"select * from " 
						+ "(select rownum rnum,pb_no ,pb_writer, "
						+ "pb_title, pb_content, pb_file, "
						+ "pb_readcount,pb_date from "
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

