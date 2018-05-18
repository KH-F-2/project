package com.project101.board.sell.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.board.sell.db.SellBoardBean;
import com.project101.board.sell.db.SellBoardDAO;

public class SellBoardAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
			
		SellBoardDAO selldao=new SellBoardDAO();
		SellBoardBean sellboard=new SellBoardBean();
		HttpSession session=request.getSession();
		
		sellboard.setSB_WRITER(session.getAttribute("id").toString());
		sellboard.setSB_TITLE(request.getParameter("SB_TITLE"));
		sellboard.setSB_CONTENT(request.getParameter("SB_CONTENT"));
		sellboard.setSB_PRICE(Integer.parseInt(request.getParameter("SB_PRICE").toString()));
		sellboard.setSB_CONTENT(request.getParameter("SB_CONTENT"));
		
		
		int result=selldao.boardInsert(sellboard);
		if(result==1) {
			out.println("<script> alert('게시판 등록 성공!'); location.href='./BoardList.sell';</script>");
		}else {
			out.println("<script> alert('게시판 등록 실패!'); history.back();</script>");
		}
		out.close();
		
		return null;
	}

}
