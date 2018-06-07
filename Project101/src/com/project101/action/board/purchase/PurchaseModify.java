package com.project101.action.board.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.PurchaseBoardBean;
import com.project101.dao.PurchaseBoardDAO;

public class PurchaseModify implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();

		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
		PurchaseBoardBean boardBean = new PurchaseBoardBean();

		int num = Integer.parseInt(request.getParameter("PB_NO"));

		boardBean = purchaseDAO.getDetail(num);
		
		if (boardBean == null) {
			System.out.println("수정 이동 실패");
			return null;
		} else {
			System.out.println("수정 이동 완료");
		}
		
		request.setAttribute("boardBean", boardBean);

		forward.setRedirect(false);
		forward.setPath("template.jsp?page=./purchaseboard/pbmodify.jsp");
		return forward;

	}
	
}
