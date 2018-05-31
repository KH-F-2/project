package com.project101.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;

public class listAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		SellerpageDAO sellerdao = new SellerpageDAO();
//		List<SellerpageBean> sellerList = new ArrayList<SellerpageBean>();
		String writer = request.getParameter("writer");
//		sellerList = sellerdao.getBoardList(no);
//		request.setAttribute("SellerList", sellerList);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/sellerpage/sellerpage_view.jsp");
		return forward;
	}

}
