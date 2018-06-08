package com.project101.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.project101.bean.NoticeBean;



public class NoticeDao {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;
	
	public NoticeDao() {
		try {
			Context init = new InitialContext();
			 ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}
	
	public List<NoticeBean> getBoardList(int notice_category){
		List<NoticeBean> list = new ArrayList<NoticeBean>();
		try {
			
			con = ds.getConnection();
			String sql = "select * from notice where notice_category = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_category);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				NoticeBean bean = new NoticeBean();
				bean.setNotice_no(rs.getInt("notice_no"));
				bean.setNotice_A(rs.getString("notice_A"));
				bean.setNotice_Q(rs.getString("notice_Q"));
				bean.setNotice_category(rs.getInt("notice_category"));
				
				list.add(bean);
				
			}
			return list;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)   pstmt.close();
	            if(con!=null)      con.close();
	            if(rs!=null)      rs.close();
			}catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	return list;
	
	
	
	}
}
