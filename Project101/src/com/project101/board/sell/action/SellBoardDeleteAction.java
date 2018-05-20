package com.project101.board.sell.action;

import java.io.*;

import javax.servlet.http.*;

import com.project101.board.sell.db.*;

public class SellBoardDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		String id = request.getParameter("Board_ID");
		SellBoardDAO boarddao = new SellBoardDAO();
		SellBoardBean boarddata = new SellBoardBean();
		
		int result = 1;
		
		result = boarddao.SellBoardDelete(); 
		PrintWriter out = response.getWriter();
		if (result == 1) {
			out.println("<script>alert('삭제가 실패되었습니다.');history.back();</script>");
		}
		response.setContentType("text/html;charset=utf-8");
		out.println("<script>alert('삭제되었습니다.');location = '.BoardList.sell';</script>");
		out.close();
		forward.setRedirect(false);
		
		return null;
	}
	
}
