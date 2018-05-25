package com.project101.board.epilogue.db;

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

import com.sun.org.glassfish.external.statistics.annotations.Reset;

public class EpilogueBoardDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
	int result;

	public EpilogueBoardDAO() {

		try {

			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {

			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}

	public int getListCount(HashMap<String, Object> listOpt) {
		try {
			conn = ds.getConnection();
			String sql = "SELECT COUNT(*) FROM EPILOGUE_BOARD";
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
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
		return result;
	}
	private static EpilogueBoardDAO instance;
    
    public static EpilogueBoardDAO getInstance(){
        if(instance==null)
            instance=new EpilogueBoardDAO();
        return instance;
    }

	public ArrayList<EpilogueBoardBean> getBoardList(HashMap<String, Object> listOpt) {
			ArrayList<EpilogueBoardBean> list = new ArrayList<EpilogueBoardBean>();
			String opt = (String)listOpt.get("opt"); // 검색옵션(제목, 내용, 글쓴이 등..)
	        String condition = (String)listOpt.get("condition"); // 검색내용
	        int start = (Integer)listOpt.get("start"); // 현재 페이지번호
	        
	        try {
	            conn = ds.getConnection();
	            StringBuffer sql = new StringBuffer();
	            
	            // 글목록 전체를 보여줄 때
	            if(opt == null)
	            {
	                // BOARD_RE_REF(그룹번호)의 내림차순 정렬 후 동일한 그룹번호일 때는
	                // BOARD_RE_SEQ(답변글 순서)의 오름차순으로 정렬 한 후에
	                // 10개의 글을 한 화면에 보여주는(start번째 부터 start+9까지) 쿼리
	                // desc : 내림차순, asc : 오름차순 ( 생략 가능 )
	                sql.append("select * from ");
	                sql.append("(select rownum rnum, SB_NO, SB_WRITER, SB_TITLE");
	                sql.append(", SB_READCOUNT, SB_DATE, SB_CONTENT ");
	                sql.append("FROM");
	                sql.append(" (select * from epilogue_board order by SB_NO desc)) ");
	                sql.append("where rnum>=? and rnum<=?");
	                
	                pstmt = conn.prepareStatement(sql.toString());
	                pstmt.setInt(1, start);
	                pstmt.setInt(2, start+9);
	                
	                // StringBuffer를 비운다.
	                sql.delete(0, sql.toString().length());
	            }
	            else if(opt.equals("0")) // 제목으로 검색
	            {
	            	sql.append("select * from ");
	                sql.append("(select rownum rnum, SB_NO, SB_WRITER, SB_TITLE");
	                sql.append(", SB_READCOUNT, SB_DATE, SB_CONTENT ");
	                sql.append("FROM ");
	                sql.append("(select * from epilogue_board where SB_TITLE like ? ");
	                sql.append("order BY SB_NO desc)) ");
	                sql.append("where rnum>=? and rnum<=?");

	                pstmt = conn.prepareStatement(sql.toString());
	                pstmt.setString(1, "%"+condition+"%");
	                pstmt.setInt(2, start);
	                pstmt.setInt(3, start+9);
	                
	                sql.delete(0, sql.toString().length());
	            }
	            else if(opt.equals("1")) // 내용으로 검색
	            {
	            	sql.append("select * from ");
	                sql.append("(select rownum rnum, SB_NO, SB_WRITER, SB_TITLE");
	                sql.append(", SB_READCOUNT, SB_DATE, SB_CONTENT ");
	                sql.append("FROM ");
	                sql.append("(select * from epilogue_board where SB_CONTENT like ? ");
	                sql.append("order BY SB_NO desc)) ");
	                sql.append("where rnum>=? and rnum<=?");
	                
	                pstmt = conn.prepareStatement(sql.toString());
	                pstmt.setString(1, "%"+condition+"%");
	                pstmt.setInt(2, start);
	                pstmt.setInt(3, start+9);
	                
	                sql.delete(0, sql.toString().length());
	            }
	            else if(opt.equals("2")) // 제목+내용으로 검색
	            {
	            	sql.append("select * from ");
	                sql.append("(select rownum rnum, SB_NO, SB_WRITER, SB_TITLE");
	                sql.append(", SB_READCOUNT, SB_DATE, SB_CONTENT ");
	                sql.append("FROM ");
	                sql.append("(select * from epilogue_board where SB_TITLE like ? OR SB_CONTENT like ? ");
	                sql.append("order BY SB_NO desc)) ");
	                sql.append("where rnum>=? and rnum<=?");
	                
	                pstmt = conn.prepareStatement(sql.toString());
	                pstmt.setString(1, "%"+condition+"%");
	                pstmt.setString(2, "%"+condition+"%");
	                pstmt.setInt(3, start);
	                pstmt.setInt(4, start+9);
	                
	                sql.delete(0, sql.toString().length());
	            }
	            else if(opt.equals("3")) // 글쓴이로 검색
	            {
	            	sql.append("select * from ");
	                sql.append("(select rownum rnum, SB_NO, SB_WRITER, SB_TITLE");
	                sql.append(", SB_READCOUNT, SB_DATE, SB_CONTENT ");
	                sql.append("FROM ");
	                sql.append("(select * from epilogue_board where SB_WRITER like ? ");
	                sql.append("order BY SB_NO desc)) ");
	                sql.append("where rnum>=? and rnum<=?");
	                
	                pstmt = conn.prepareStatement(sql.toString());
	                pstmt.setString(1, "%"+condition+"%");
	                pstmt.setInt(2, start);
	                pstmt.setInt(3, start+9);
	                
	                sql.delete(0, sql.toString().length());
	            }
	            
	            rset = pstmt.executeQuery();
	            while(rset.next())
	            {
	                EpilogueBoardBean board = new EpilogueBoardBean();
	                board.setSB_NO(rset.getInt("SB_NO"));
	                board.setSB_WRITER(rset.getString("SB_WRITER"));
	                board.setSB_TITLE(rset.getString("SB_TITLE"));
	                board.setSB_CONTENT(rset.getString("SB_CONTENT"));
	                board.setSB_DATE(rset.getDate("SB_DATE"));
	                board.setSB_READCOUNT(rset.getInt("SB_READCOUNT"));
	                list.add(board);
	            }
	            
	        } catch (Exception e) {
	            System.out.println("getBoardList 오류 :" + e);
	            e.printStackTrace();
	        }
	        
	        close();
	        return list;
	}
	
	//글의 개수 가져오는 메서드
	public int getBoardListCount(HashMap<String, Object> listOpt) {
		int result = 0;
        String opt = (String)listOpt.get("opt"); // 검색옵션(제목, 내용, 글쓴이 등..)
        String condition = (String)listOpt.get("condition"); // 검색내용
        
        try {
            conn = ds.getConnection();
            StringBuffer sql = new StringBuffer();
            
            if(opt == null)    // 전체글의 개수
            {
                sql.append("select count(*) from epilogue_board");
                pstmt = conn.prepareStatement(sql.toString());
                
                // StringBuffer를 비운다.
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) // 제목으로 검색한 글의 개수
            {
                sql.append("select count(*) from epilogue_board where SB_TITLE like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("1")) // 내용으로 검색한 글의 개수
            {
                sql.append("select count(*) from epilogue_board where SB_CONTENT like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("2")) // 제목+내용으로 검색한 글의 개수
            {
                sql.append("select count(*) from epilogue_board ");
                sql.append("where SB_TITLE like ? or SB_CONTENT like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                pstmt.setString(2, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("3")) // 글쓴이로 검색한 글의 개수
            {
                sql.append("select count(*) from epilogue_board where SB_WRITER like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            
            rset = pstmt.executeQuery();
            if(rset.next())    result = rset.getInt(1);
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return result;
	}
	    // DB 자원해제
	    private void close()
	    {
	        try {
	            if ( pstmt != null ){ pstmt.close(); pstmt=null; }
	            if ( conn != null ){ conn.close(); conn=null;    }
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	        }
	    } // end close()

	public int boardInsert(EpilogueBoardBean epilboard) {
		int num = 0;
		try {
			conn = ds.getConnection();
			String sql = "select max(SB_NO) from EPILOGUE_BOARD";
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				num = rset.getInt(1) + 1;
			} else {
				num = 1;
			}

			rset.close();
			pstmt.close();

			String insert_sql = "insert into EPILOGUE_BOARD " + "(SB_NO, SB_WRITER, SB_TITLE, "
					+ "SB_CONTENT, SB_PRICE, SB_DATE, SB_READCOUNT, SB_FILE, SB_GRADE) " + "values(?,?,?,?,?,sysdate,?,?,?)";
			pstmt = conn.prepareStatement(insert_sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, epilboard.getSB_WRITER());
			pstmt.setString(3, epilboard.getSB_TITLE());
			pstmt.setString(4, epilboard.getSB_CONTENT());
			pstmt.setInt(5, epilboard.getSB_PRICE());
			pstmt.setInt(6, epilboard.getSB_READCOUNT());
			pstmt.setString(7, epilboard.getSB_FILE());
			pstmt.setInt(8, epilboard.getSB_GRADE());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
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
		return result;
	}

	public void setReadCountUpdate(int num) {
		try {
			conn = ds.getConnection();
			String sql = "update EPILOGUE_BOARD set SB_READCOUNT = SB_READCOUNT+1 where SB_NO=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
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

	}

	public EpilogueBoardBean getDetail(int num) {
		EpilogueBoardBean epilboard = new EpilogueBoardBean();
		try {
			conn = ds.getConnection();
			String sql = "select * from EPILOGUE_BOARD where SB_NO=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				epilboard.setSB_NO(rset.getInt("SB_NO"));
				epilboard.setSB_WRITER(rset.getString("SB_WRITER"));
				epilboard.setSB_TITLE(rset.getString("SB_TITLE"));
				epilboard.setSB_CONTENT(rset.getString("SB_CONTENT"));
				epilboard.setSB_PRICE(rset.getInt("SB_PRICE"));
				epilboard.setSB_DATE(rset.getDate("SB_DATE"));
				epilboard.setSB_READCOUNT(rset.getInt("SB_READCOUNT"));
				epilboard.setSB_FILE(rset.getString("SB_FILE"));
				epilboard.setSB_GRADE(rset.getInt("SB_GRADE"));

				return epilboard;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	public boolean boardDelete(EpilogueBoardBean epildata) {
		try {
			String sql = "delete from EPILOGUE_BOARD where SB_NO=?";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, epildata.getSB_NO());

			int result = pstmt.executeUpdate();

			if (result == 0) {
					return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("SellBoardDAO - boardDelete() 에러 :" + e);
			e.printStackTrace();
		} finally {
			if (rset != null)
				try {
					rset.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return false;
	}

	public int boardModify(EpilogueBoardBean epildata) {
			try {
				String sql = 
					"update EPILOGUE_BOARD set SB_TITLE =?, "
				+	"SB_CONTENT = ?  "
				+	"where SB_NO = ? ";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, epildata.getSB_TITLE());
			pstmt.setString(2, epildata.getSB_CONTENT());
			pstmt.setInt(3, epildata.getSB_NO());
			
			result = pstmt.executeUpdate();
		
		}catch (Exception e) {
			System.out.println("boardModify 에러 : " + e);
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
		return result;
	}

}
