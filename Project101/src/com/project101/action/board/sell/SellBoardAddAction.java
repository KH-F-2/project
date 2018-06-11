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


public class SellBoardAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		SellBoardBean sellBoardBean = new SellBoardBean();
		SellBoardDAO sellDAO = new SellBoardDAO();
		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
		PurchaseBoardBean purchaseBoardBean = new PurchaseBoardBean();
		ImageBean imageBean = new ImageBean();
		ImageDAO imageDAO = new ImageDAO();

		HttpSession session = request.getSession();
		int result = 0;

		String BOARD_NAME = request.getParameter("board_name");
		System.out.println(BOARD_NAME);
		int BOARD_NUM = 0;
		
		if(BOARD_NAME.equals("SELL_BOARD")) {
			int BOARD_NO = sellDAO.getNextBoardNo();
			sellBoardBean.setSB_NO(BOARD_NO);
			sellBoardBean.setSB_WRITER(session.getAttribute("id").toString());
			sellBoardBean.setSB_TITLE(request.getParameter("TITLE"));
			sellBoardBean.setSB_CONTENT(request.getParameter("CONTENT"));
			sellBoardBean.setSB_PRICE(Integer.parseInt(request.getParameter("PRICE").toString()));
			sellBoardBean.setSB_LAT(Double.parseDouble(request.getParameter("markerLat")));
			sellBoardBean.setSB_LNG(Double.parseDouble(request.getParameter("markerLng")));
			sellBoardBean.setSB_CATEGORY(Integer.parseInt(request.getParameter("CATEGORY")));
			sellBoardBean.setSB_HASHTAG(request.getParameter("HASHTAG"));
			
			result = sellDAO.boardInsert(sellBoardBean);
			BOARD_NUM = BOARD_NO;
		}else {
			int BOARD_NO = purchaseDAO.getMaxCount();
			purchaseBoardBean.setPB_WRITER(session.getAttribute("id").toString());
			purchaseBoardBean.setPB_TITLE(request.getParameter("TITLE"));
			purchaseBoardBean.setPB_CONTENT(request.getParameter("CONTENT"));
			purchaseBoardBean.setPB_CATEGORY(Integer.parseInt(request.getParameter("CATEGORY")));
			purchaseBoardBean.setPB_PRICE(Integer.parseInt(request.getParameter("PRICE")));
			purchaseBoardBean.setPB_HASHTAG(request.getParameter("HASHTAG"));
			purchaseBoardBean.setPB_LAT(Double.parseDouble(request.getParameter("markerLat")));  
			purchaseBoardBean.setPB_LNG(Double.parseDouble(request.getParameter("markerLng")));

			result = purchaseDAO.purchaseInsert(purchaseBoardBean);
			BOARD_NUM = BOARD_NO;
		}
		
		if (result == 0) {
			System.out.println("게시판 등록 실패");
			return null;
		} else {
			System.out.println("게시판 등록 완료");
		}
		// 이미지 insert
		String url = request.getParameter("img_hidden");
		imageBean.setBOARD_NO(BOARD_NUM);
		imageBean.setIMAGE_URL(url);
		
		int result2 = 0;
		if(result != 0) {
			result2 = imageDAO.imageInsert(imageBean, BOARD_NAME);

			if (result2 == 0) {
				System.out.println("image insert fail!");
			}
		}
		

		if (result == 1) {
			out.println("<script> alert('게시판 등록 성공!'); location.href='./sbview.sb?num=" + BOARD_NUM + "&board_name=" 
					+ BOARD_NAME + "';</script>");
		} 
		else {
			out.println("<script> alert('게시판 등록 실패!'); history.back();</script>");
		}
		out.close();

		return null;
	}

}
