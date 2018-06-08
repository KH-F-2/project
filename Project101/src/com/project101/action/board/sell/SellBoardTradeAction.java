package com.project101.action.board.sell;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.dao.SellBoardDAO;

public class SellBoardTradeAction implements Action {

<<<<<<< HEAD
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		SellBoardDAO sellDAO = new SellBoardDAO();
		String id = session.getAttribute("id").toString();
		int SB_NO = Integer.parseInt(request.getParameter("SB_NO"));
		
		int result = sellDAO.tradeItem(SB_NO, id);
		
		if(result == 0 ) {
			out.print("판매가 완료된 상품입니다.");
		}else {
			out.print("구매신청 되었습니다.");
		}
		
		return null;
	}

}
=======
   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      response.setContentType("text/html;charset=UTF-8");
      request.setCharacterEncoding("UTF-8");
      
      PrintWriter out = response.getWriter();
      HttpSession session = request.getSession();
      
      SellBoardDAO sellDAO = new SellBoardDAO();
      String id = session.getAttribute("id").toString();
      int SB_NO = Integer.parseInt(request.getParameter("SB_NO"));
      
      int result = sellDAO.tradeItem(SB_NO, id);
      
      if(result == 0 ) {
         out.print("판매가 완료된 상품입니다.");
      }else {
         out.print("구매신청 되었습니다.");
      }
      
      return null;
   }

}
>>>>>>> origin/yeunju
