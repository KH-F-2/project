package com.project101.action.board.sell;

import java.io.*;

import javax.servlet.http.*;

import com.project101.board.sell.db.*;

public class SellBoardDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		SellBoardDAO boardDAO = new SellBoardDAO();
		PrintWriter out = response.getWriter();
		
		int num = Integer.parseInt(request.getParameter("num"));
		int result = 0;
		
		result = boardDAO.boardDelete(num); 
		
		if (result == 1) {
			out.println("<script>alert('삭제되었습니다.');location = './sbmain.sb';</script>");
		}
		else {
			out.println("<script>alert('삭제 실패!');history.back();</script>");
		}
		out.close();
		
		return null;
	}
	
}
