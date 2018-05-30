package com.project101.board.sell.action;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.board.sell.db.ImageBean;
import com.project101.board.sell.db.ImageDAO;
import com.project101.board.sell.db.SellBoardBean;
import com.project101.board.sell.db.SellBoardDAO;

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
		java.util.Date date = format.parse(request.getParameter("SB_PDATE"));

		long date2 = date.getTime();
		Date pdate = new Date(date2);

		int BOARD_NO = sellDAO.getNextBoardNo();

		boardBean.setSB_NO(BOARD_NO);
		boardBean.setSB_WRITER(session.getAttribute("id").toString());
		boardBean.setSB_TITLE(request.getParameter("SB_TITLE"));
		boardBean.setSB_CONTENT(request.getParameter("SB_CONTENT"));
		boardBean.setSB_PDATE(pdate);
		boardBean.setSB_PRICE(Integer.parseInt(request.getParameter("SB_PRICE").toString()));
		
		int result = sellDAO.boardInsert(boardBean);

		if (!request.getParameter("img_hidden").equals("")) {
			String[] url = request.getParameter("img_hidden").split(" ");
			imageBean.setBOARD_NO(BOARD_NO);

			for (String imageurl : url) {
				imageBean.setIMAGE_URL(imageurl);
				int result2 = imageDAO.imageInsert(imageBean);

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
