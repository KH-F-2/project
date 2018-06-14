package com.project101.action.board.sell;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.dao.CommentDAO;
import com.project101.dao.ImageDAO;
import com.project101.dao.PurchaseBoardDAO;
import com.project101.dao.SellBoardDAO;

public class SellBoardDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		SellBoardDAO sellDAO = new SellBoardDAO();
		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
		ImageDAO imageDAO = new ImageDAO();
		CommentDAO commentDAO = new CommentDAO();
		PrintWriter out = response.getWriter();
		
		int num = Integer.parseInt(request.getParameter("num"));
		double LAT = Double.parseDouble(request.getParameter("centerLat"));
		double LNG = Double.parseDouble(request.getParameter("centerLng"));
		String BOARD_NAME = request.getParameter("board_name");
		
		int result = 0;
		
		if(BOARD_NAME.equals("SELL_BOARD")) {
			result = sellDAO.boardDelete(num); 
		}else {
			result = purchaseDAO.purchaseDelete(num);
		}
		
		
		if (result >= 1) {
			imageDAO.imageDelete(num, BOARD_NAME);
			commentDAO.commentDelete(num, BOARD_NAME);
			out.println("<script>alert('삭제되었습니다.');location = './sbmain.sb?centerLat=" + LAT + "&centerLng=" + LNG + "';</script>");
		}
		else {
			out.println("<script>alert('삭제 실패!');history.back();</script>");
		}
		out.close();
		
		return null;
	}
	
}
