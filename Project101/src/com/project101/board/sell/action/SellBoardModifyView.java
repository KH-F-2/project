package com.project101.board.sell.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.sell.db.SellBoardBean;
import com.project101.board.sell.db.SellBoardDAO;

public class SellBoardModifyView implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int num=Integer.parseInt(request.getParameter("num"));
		SellBoardDAO selldao=new SellBoardDAO();
		SellBoardBean sellboard=new SellBoardBean();
		
		sellboard=selldao.getDetail(num);
		
		if(sellboard==null) {
			System.out.println("수정 페이지 이동 실패");
			return null;
		}
		System.out.println("수정 페이지 이동 완료");
		
		request.setAttribute("sellboard", sellboard);
		
		forward.setPath("/sellboard/sell_board_modify.jsp");
		forward.setRedirect(false);
		
	    return forward;
	}

}
