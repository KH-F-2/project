package com.project101.action.board.sell;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.*;
import com.project101.bean.*;
import com.project101.dao.*;


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

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = format.parse(request.getParameter("SB_PURCHASE_DATE"));

		long date2 = date.getTime();
		Date pdate = new Date(date2);

		int BOARD_NO = sellDAO.getNextBoardNo();

		boardBean.setSB_NO(BOARD_NO);
		boardBean.setSB_WRITER(session.getAttribute("id").toString());
		boardBean.setSB_TITLE(request.getParameter("SB_TITLE"));
		boardBean.setSB_CONTENT(request.getParameter("SB_CONTENT"));
		boardBean.setSB_PURCHASE_DATE(pdate);
		boardBean.setSB_PRICE(Integer.parseInt(request.getParameter("SB_PRICE").toString()));
		boardBean.setSB_LAT(0);
		boardBean.setSB_LNG(0);
		boardBean.setSB_CATEGORY(Integer.parseInt(request.getParameter("SB_CATEGORY")));
		boardBean.setSB_HASHTAG(request.getParameter("SB_HASHTAG"));
		
		int result = sellDAO.boardInsert(boardBean);

		// 이미지 있으면 IMAGE 테이블 insert
		if (!request.getParameter("img_hidden").equals("")) {
			String tableName = "SELL_BOARD";
			String[] url = request.getParameter("img_hidden").split(" ");
			imageBean.setBOARD_NO(BOARD_NO);

			for (String imageurl : url) {
				imageBean.setIMAGE_URL(imageurl);
				int result2 = imageDAO.imageInsert(imageBean, tableName);

				if (result2 == 0) {
					System.out.println("image insert fail!");
				}
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
