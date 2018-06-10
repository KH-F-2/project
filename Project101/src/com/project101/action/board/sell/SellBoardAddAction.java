package com.project101.action.board.sell;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.ImageBean;
import com.project101.bean.SellBoardBean;
import com.project101.dao.ImageDAO;
import com.project101.dao.SellBoardDAO;


public class SellBoardAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		SellBoardBean boardBean = new SellBoardBean();
		SellBoardDAO sellDAO = new SellBoardDAO();
		ImageBean imageBean = new ImageBean();
		ImageDAO imageDAO = new ImageDAO();

		HttpSession session = request.getSession();


		int BOARD_NO = sellDAO.getNextBoardNo();

		boardBean.setSB_NO(BOARD_NO);
		boardBean.setSB_WRITER(session.getAttribute("id").toString());
		boardBean.setSB_TITLE(request.getParameter("TITLE"));
		boardBean.setSB_CONTENT(request.getParameter("CONTENT"));
		boardBean.setSB_PRICE(Integer.parseInt(request.getParameter("PRICE").toString()));
		boardBean.setSB_LAT(Double.parseDouble(request.getParameter("markerLat")));
		boardBean.setSB_LNG(Double.parseDouble(request.getParameter("markerLng")));
		boardBean.setSB_CATEGORY(Integer.parseInt(request.getParameter("CATEGORY")));
		boardBean.setSB_HASHTAG(request.getParameter("HASHTAG"));
		
		int result = sellDAO.boardInsert(boardBean);

		// 이미지 insert
		String tableName = "SELL_BOARD";
		String url = request.getParameter("img_hidden");
		imageBean.setBOARD_NO(BOARD_NO);
		imageBean.setIMAGE_URL(url);
		
		int result2 = 0;
		if(result != 0) {
			result2 = imageDAO.imageInsert(imageBean, tableName);

			if (result2 == 0) {
				System.out.println("image insert fail!");
			}
		}
		

		if (result == 1) {
			out.println("<script> alert('게시판 등록 성공!'); location.href='./sbview.sb?num=" + BOARD_NO+ "';</script>");
		} 
		else {
			out.println("<script> alert('게시판 등록 실패!'); history.back();</script>");
		}
		out.close();

		return null;
	}

}
