package com.project101.action.board.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.PurchaseBoardBean;
import com.project101.dao.PurchaseBoardDAO;

public class PurchaseWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
		PurchaseBoardBean boardBean = new PurchaseBoardBean();
		ActionForward forward = new ActionForward();

		boolean result = false;

		try {
			request.setCharacterEncoding("UTF-8");
			String id = request.getParameter("PB_WRITER");
			String title = request.getParameter("PB_TITLE");
			String content = request.getParameter("PB_CONTENT");
			//정해지면 봉인 헤제
			//Double lat = Double.parseDouble(request.getParameter("PB_LAT"));
			//Double lng = Double.parseDouble(request.getParameter("PB_LNG"));
			int price = Integer.parseInt(request.getParameter("PB_PRICE"));
			int category = Integer.parseInt(request.getParameter("PB_CATEGORY"));
			String hashtag = request.getParameter("PB_HASHTAG");
			
			boardBean.setPB_WRITER(id);
			boardBean.setPB_TITLE(title);
			boardBean.setPB_CONTENT(content);
			boardBean.setPB_PRICE(price);
			//boardBean.setPB_LAT(lat);  //이걸 넣으려면 아래 insert메소드와 dao의 insert 손봐야한다 
			//boardBean.setPB_LNG(lng);
			boardBean.setPB_CATEGORY(category);
			boardBean.setPB_HASHTAG(hashtag);

			result = purchaseDAO.purchaseInsert(boardBean, id, title, content, price, category, hashtag);

			if (result == false) {
				System.out.println("게시판 등록 실패");
				return null;
			} else {
				System.out.println("게시판 등록 완료");
			}

			forward.setRedirect(true);
			forward.setPath("./pbmain.pb");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}
}