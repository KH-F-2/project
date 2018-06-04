package com.project101.action.board.sell;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.dao.ImageDAO;
import com.project101.dao.SellBoardDAO;

public class SellBoardDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		SellBoardDAO boardDAO = new SellBoardDAO();
		ImageDAO imageDAO = new ImageDAO();
		PrintWriter out = response.getWriter();
		
		int num = Integer.parseInt(request.getParameter("num"));
		int result = 0;
		String tableName = "SELL_BOARD";
		
		result = boardDAO.boardDelete(num); 
		result = imageDAO.imageDelete(num, tableName);
		
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
