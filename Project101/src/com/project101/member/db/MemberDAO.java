package com.project101.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rset;
	int result;

	public MemberDAO() {

		try {

			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {

			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}

	public int isId(String id, String password) {

		try {
			conn = ds.getConnection();
			System.out.println("getConnection");

			pstmt = conn.prepareStatement("select id, password from member where id=?");
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();

			if (rset.next()) {

				if (rset.getString("password").equals(password)) {

					result = 1; // 아이디와 비밀번호가 일치하는 경우
				} else {

					result = 0; // 비밀번호가 일치하지 않는 경우
				}
			} else {

				result = -1; // 아이디가 없는 경우
			}
		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return result;
	}
	
	
}
