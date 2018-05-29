package com.project101.board.purchase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.purchase.db.PurchaseBoardBean;
import com.project101.board.purchase.db.PurchaseBoardDAO;

public class PurchaseModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();

		PurchaseBoardDAO buydao = new PurchaseBoardDAO();
		PurchaseBoardBean buydata = new PurchaseBoardBean();

		int num = Integer.parseInt(request.getParameter("num"));

		buydata = buydao.getDetail(num);
		
		if (buydata == null) {
			System.out.println("수정 이동 실패");
			return null;
		} else {
			System.out.println("수정 이동 완료");
		}
		request.setAttribute("buydata", buydata);

		forward.setRedirect(false);
		forward.setPath("./buy/buy_board_modify.jsp");
		return forward;

	}
		
	

}
