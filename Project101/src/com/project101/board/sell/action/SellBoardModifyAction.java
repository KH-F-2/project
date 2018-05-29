package com.project101.board.sell.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.board.sell.db.SellBoardBean;
import com.project101.board.sell.db.SellBoardDAO;

public class SellBoardModifyAction implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      response.setContentType("text/html;charset=utf-8");
      request.setCharacterEncoding("utf-8");
      ActionForward forward = new ActionForward();
      
      int num=Integer.parseInt(request.getParameter("SB_NO"));
      
      SellBoardDAO selldao=new SellBoardDAO();
      SellBoardBean sellboard=new SellBoardBean();
      HttpSession session=request.getSession();
      
      sellboard.setSB_NO(num);
      sellboard.setSB_WRITER(session.getAttribute("id").toString());
      sellboard.setSB_TITLE(request.getParameter("SB_TITLE"));
      sellboard.setSB_CONTENT(request.getParameter("SB_CONTENT"));
      sellboard.setSB_PRICE(Integer.parseInt(request.getParameter("SB_PRICE").toString()));
      /*SimpleDateFormat format=new SimpleDateFormat("MM-dd-yyyy");
      sellboard.setSB_BDATE(format.format(request.getParameter("SB_BDATE")));*/
      
      
      int result=selldao.boardModify(sellboard);
      PrintWriter out=response.getWriter();
      if(result==1) {
         out.println("<script> alert('게시판 수정 성공!'); location.href='./BoardDetail.sell?num="+num+"';</script>");
      }else {
         out.println("<script> alert('게시판 수정 실패!'); history.back();</script>");
      }
      out.close();
      
      return null;
   }

}