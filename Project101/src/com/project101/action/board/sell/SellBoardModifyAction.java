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
		boardBean.setSB_TITLE(request.getParameter("SB_TITLE"));
		boardBean.setSB_CONTENT(request.getParameter("SB_CONTENT"));
		boardBean.setSB_PURCHASE_DATE(pdate);
		boardBean.setSB_PRICE(Integer.parseInt(request.getParameter("SB_PRICE").toString()));
		boardBean.setSB_LAT(0);
		boardBean.setSB_LNG(0);
		boardBean.setSB_CATEGORY(Integer.parseInt(request.getParameter("SB_CATEGORY")));
		boardBean.setSB_HASHTAG(request.getParameter("SB_HASHTAG"));

		int result = sellDAO.boardModify(boardBean);
		PrintWriter out = response.getWriter();

		if (result == 1) {
			if (!request.getParameter("img_hidden").equals("")) {
				String tableName = "SELL_BOARD";
				imageDAO.imageDelete(num, tableName);
				String[] url = request.getParameter("img_hidden").split(" ");
				imageBean.setBOARD_NO(num);
				
				for (String imageurl : url) {
					imageBean.setIMAGE_URL(imageurl);
					imageDAO.imageInsert(imageBean, tableName);
				}
			}
			out.println("<script> alert('게시판 수정 성공!'); location.href='./sbview.sb?num=" + num + "';</script>");
		} else {
			out.println("<script> alert('게시판 수정 실패!'); history.back();</script>");
		}
		out.close();

		return null;
	}

}
