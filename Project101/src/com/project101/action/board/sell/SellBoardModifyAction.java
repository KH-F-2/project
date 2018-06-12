package com.project101.action.board.sell;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.ImageBean;
import com.project101.bean.PurchaseBoardBean;
import com.project101.bean.SellBoardBean;
import com.project101.dao.ImageDAO;
import com.project101.dao.PurchaseBoardDAO;
import com.project101.dao.SellBoardDAO;

public class SellBoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");


		SellBoardDAO sellDAO = new SellBoardDAO();
		SellBoardBean sellBean = new SellBoardBean();
		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
		PurchaseBoardBean purchaseBean = new PurchaseBoardBean();
		ImageBean imageBean = new ImageBean();
		ImageDAO imageDAO = new ImageDAO();
		HttpSession session = request.getSession();
		
		int result = 0;
		int result2 = 0;
		int num = Integer.parseInt(request.getParameter("NO"));
		String BOARD_NAME = request.getParameter("board_name");
		
		if(BOARD_NAME.equals("SELL_BOARD")) {
			sellBean.setSB_NO(num);
			sellBean.setSB_WRITER(session.getAttribute("id").toString());
			sellBean.setSB_TITLE(request.getParameter("TITLE"));
			sellBean.setSB_CONTENT(request.getParameter("CONTENT"));
			sellBean.setSB_PRICE(Integer.parseInt(request.getParameter("PRICE").toString()));
			sellBean.setSB_LAT(Double.parseDouble(request.getParameter("markerLat")));
			sellBean.setSB_LNG(Double.parseDouble(request.getParameter("markerLng")));
			sellBean.setSB_CATEGORY(Integer.parseInt(request.getParameter("CATEGORY")));
			sellBean.setSB_HASHTAG(request.getParameter("HASHTAG"));
			
			result = sellDAO.boardModify(sellBean);
		}else {
			purchaseBean.setPB_NO(num);
			purchaseBean.setPB_TITLE(request.getParameter("TITLE"));
			purchaseBean.setPB_CONTENT(request.getParameter("CONTENT"));
			purchaseBean.setPB_PRICE(Integer.parseInt(request.getParameter("PRICE")));
			purchaseBean.setPB_LAT(Double.parseDouble(request.getParameter("markerLat")));  
			purchaseBean.setPB_LNG(Double.parseDouble(request.getParameter("markerLng")));
			purchaseBean.setPB_CATEGORY(Integer.parseInt(request.getParameter("CATEGORY")));
			purchaseBean.setPB_HASHTAG(request.getParameter("HASHTAG"));
			
			result = purchaseDAO.purchaseModify(purchaseBean, num);
		}


		PrintWriter out = response.getWriter();
		
		if(result != 0) {
			// 이미지 insert
			String url = request.getParameter("img_hidden");
			imageBean.setBOARD_NO(num);
			imageBean.setIMAGE_URL(url);
			
			result2 = imageDAO.imageModify(imageBean, BOARD_NAME);
			if (result2 == 0) {
				System.out.println("image modify fail!");
			}
		}
		

		if (result2 > 0) {
			out.println("<script> alert('게시판 수정 성공!'); location.href='./sbview.sb?num=" + num + "&board_name=" 
					+ BOARD_NAME + "';</script>");
		} else {
			out.println("<script> alert('게시판 수정 실패!'); history.back();</script>");
		}
		out.close();

		return null;
	}

}
