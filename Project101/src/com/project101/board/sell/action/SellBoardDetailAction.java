package com.project101.board.sell.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.sell.db.SellBoardBean;
import com.project101.board.sell.db.SellBoardDAO;

public class SellBoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=euc-kr");
		request.setCharacterEncoding("euc-kr");
		SellBoardDAO selldao=new SellBoardDAO();
		ActionForward forward=new ActionForward();
		SellBoardBean sellboard=new SellBoardBean();
		
		/*
		int num=Integer.parseInt(request.getParameter("num"));
		
		selldao.setReadCountUpdate(num);
		sellboard=selldao.getDetail(num);
		
		request.setAttribute("sellboard", sellboard);
		
		if(sellboard==null) {
			System.out.println("상세보기 실패!");
			return null;
		}else {
			System.out.println("상세보기 성공!");
			forward.setRedirect(false);
			forward.setPath("./member/template.jsp?page=/board/qna_board_view");
		}
		*/
		forward.setRedirect(false);
		forward.setPath("/sellboard/sell_board_view.jsp");
		return forward;
	}

}
