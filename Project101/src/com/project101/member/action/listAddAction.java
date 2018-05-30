package com.project101.member.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.sellerpage.db.SellerpageBean;
import com.project101.board.sellerpage.db.SellerpageDAO;
import com.project101.notice.db.NoticeBean;
import com.project101.notice.db.NoticeDao;

public class listAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SellerpageDAO sellerdao = new SellerpageDAO();
		List<SellerpageBean> sellerList = new ArrayList<SellerpageBean>();
		String writer = request.getParameter("writer");
		sellerList = sellerdao.getBoardList(no);
		request.setAttribute("SellerList", sellerList);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/sellerpage/sellerpage_view.jsp");
		return forward;
	}

}
