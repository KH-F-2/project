package com.project101.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.project101.bean.ImageBean;

public class ImageDAO {
   DataSource ds;
   Connection conn;
   PreparedStatement pstmt;
   ResultSet rset;
   int result;

   public ImageDAO() {
      try {
         Context init = new InitialContext();
         ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
      } catch (Exception e) {
         System.out.println("DB 연결 실패 : " + e);
         return;
      }
   }

   public int imageInsert(ImageBean imageBean, String tableName) {
      try {
         conn = ds.getConnection();

         String sql = "insert into IMAGE " + "values(?, ?, ?)";
         
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setInt(1, imageBean.getBOARD_NO());
         pstmt.setString(2, tableName);
         pstmt.setString(3, imageBean.getIMAGE_URL());

         result = pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (rset != null) {
               rset.close();
            }
            if (pstmt != null) {
               pstmt.close();
            }
            if (conn != null) {
               conn.close();
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return result;
   }

   public ImageBean getImage(int num, String tableName) {
      ImageBean imageBean = new ImageBean();
      try {
         conn = ds.getConnection();
         
         String sql = "select * from IMAGE where BOARD_NO=? and BOARD_NAME=?";
         
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setInt(1, num);
         pstmt.setString(2, tableName);

         rset = pstmt.executeQuery();

         if (rset.next()) {
            imageBean.setBOARD_NO(rset.getInt("BOARD_NO"));
            imageBean.setBOARD_NAME(rset.getString("BOARD_NAME"));
            imageBean.setIMAGE_URL(rset.getString("IMAGE_URL"));
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (rset != null) {
               rset.close();
            }
            if (pstmt != null) {
               pstmt.close();
            }
            if (conn != null) {
               conn.close();
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return imageBean;
   }

   public int imageDelete(int board_no, String tableName) {
      try {
         conn = ds.getConnection();

         String sql = "delete from IMAGE where BOARD_NO=? and BOARD_NAME=?";
         
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setInt(1, board_no);
         pstmt.setString(2, tableName);

         result = pstmt.executeUpdate();
         
         if (result == 0) {
            System.out.println("imageDelete() fail");
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (rset != null) {
               rset.close();
            }
            if (pstmt != null) {
               pstmt.close();
            }
            if (conn != null) {
               conn.close();
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return result;
   }

   public int imageModify(ImageBean imageBean, String tableName) {
      try {
         conn = ds.getConnection();

         String sql = "update IMAGE set IMAGE_URL = ? where BOARD_NO = ? and BOARD_NAME = ?";
         
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, imageBean.getIMAGE_URL());
         pstmt.setInt(2, imageBean.getBOARD_NO());
         pstmt.setString(3, tableName);

         result = pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (rset != null) {
               rset.close();
            }
            if (pstmt != null) {
               pstmt.close();
            }
            if (conn != null) {
               conn.close();
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return result;
   }
   
   
}