package com.project101.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.project101.bean.Epil;

public class EpilDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
	int result;

	public EpilDAO() {

		try {

			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {

			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}

	public int epInsert(Epil epBoardBean) {
		int num = 0;
		try {
			conn = ds.getConnection();
			String sql = "select max(EP_NO) from epil";
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				num = rset.getInt(1) + 1;
			} else {
				num = 1;
			}	
			rset.close();
			pstmt.close();
			
			String insert_sql = "insert into epil values(?,?,?,?,sysdate,?)";
			pstmt = conn.prepareStatement(insert_sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, epBoardBean.getEP_NAME());
			pstmt.setString(3, epBoardBean.getEP_WRITER());
			pstmt.setString(4, epBoardBean.getEP_CONTENT());
			pstmt.setInt(5, epBoardBean.getEP_STAR());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("epInsert 오류 : " + e);
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

	public ArrayList<Epil> getEpilList(String epname) {
		ArrayList<Epil> list = new ArrayList<Epil>();
		
		try {
			conn = ds.getConnection();
			
			String sql = "select * from epil where EP_NAME=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,epname);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Epil epBoardBean = new Epil();
				epBoardBean.setEP_NO(rset.getInt("EP_NO"));
				epBoardBean.setEP_NAME(rset.getString("EP_NAME"));
				epBoardBean.setEP_WRITER(rset.getString("EP_WRITER"));
				epBoardBean.setEP_CONTENT(rset.getString("EP_CONTENT"));
				epBoardBean.setEP_DATE(rset.getDate("EP_DATE"));
				epBoardBean.setEP_STAR(rset.getInt("EP_STAR"));
				list.add(epBoardBean);
			}
			return list;
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("getEpilList 오류 :" + e);
		} finally{
			try {
				if (pstmt!=null) {
					pstmt.close();
				}
				if (conn!=null) {
					conn.close();
				}
				if (rset!=null) {
					rset.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			System.out.println("getEpilList 오류 : " + e);
			}
		}
	
	return null;
	}
}
