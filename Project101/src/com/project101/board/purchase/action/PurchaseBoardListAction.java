package com.project101.board.purchase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PurchaseBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


		ActionForward forward = new ActionForward();
		forward.setRedirect(false);

		forward.setPath("board/buy_board_main.jsp");

		return forward;
	}

}
