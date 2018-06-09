package com.project101.action.board.sell;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.ImageBean;
import com.project101.bean.SellBoardBean;
import com.project101.dao.ImageDAO;
import com.project101.dao.SellBoardDAO;

public class SellBoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = format.parse(request.getParameter("SB_PURCHASE_DATE"));
		Date pdate = new Date(date.getTime());

		SellBoardDAO sellDAO = new SellBoardDAO();
		SellBoardBean boardBean = new SellBoardBean();
		ImageBean imageBean = new ImageBean();
		ImageDAO imageDAO = new ImageDAO();
		HttpSession session = request.getSession();
		
		int num = Integer.parseInt(request.getParameter("SB_NO"));

		boardBean.setSB_NO(num);
		boardBean.setSB_WRITER(session.getAttribute("id").toString());
		boardBean.setSB_TITLE(request.getParameter("TITLE"));
		boardBean.setSB_CONTENT(request.getParameter("CONTENT"));
		boardBean.setSB_PRICE(Integer.parseInt(request.getParameter("PRICE").toString()));
		boardBean.setSB_LAT(Double.parseDouble(request.getParameter("markerLat")));
		boardBean.setSB_LNG(Double.parseDouble(request.getParameter("markerLng")));
		boardBean.setSB_CATEGORY(Integer.parseInt(request.getParameter("CATEGORY")));
		boardBean.setSB_HASHTAG(request.getParameter("HASHTAG"));

		int result = sellDAO.boardModify(boardBean);
		PrintWriter out = response.getWriter();
		
		// 이미지 insert
		String tableName = "SELL_BOARD";
		String url = request.getParameter("img_hidden");
		imageBean.setBOARD_NO(num);
		imageBean.setIMAGE_URL(url);
		
		int result2 = imageDAO.imageModify(imageBean, tableName);
		if (result2 == 0) {
			System.out.println("image modify fail!");
		}
		
		System.out.println("result : "+result+" result2 : "+result2);

		if (result < 0 && result2 < 0) {
			out.println("<script> alert('게시판 수정 성공!'); location.href='./sbview.sb?num=" + num + "';</script>");
		} else {
			out.println("<script> alert('게시판 수정 실패!'); history.back();</script>");
		}
		out.close();

		return null;
	}

}
