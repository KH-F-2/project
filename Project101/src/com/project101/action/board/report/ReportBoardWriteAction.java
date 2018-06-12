package com.project101.action.board.report;

import java.io.*;
import javax.servlet.http.*;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.ReportBoardBean;
import com.project101.dao.ReportBoardDAO;

public class ReportBoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		ReportBoardDAO reportDao = new ReportBoardDAO();
		ReportBoardBean boardBean = new ReportBoardBean();
		System.out.println(request.getParameter("RB_WRITER"));
		boardBean.setRB_WRITER(request.getParameter("RB_WRITER"));
		boardBean.setRB_TITLE(request.getParameter("RB_TITLE"));
		boardBean.setRB_CONTENT(request.getParameter("RB_CONTENT"));
		boardBean.setRB_PRICE(Integer.parseInt(request.getParameter("RB_PRICE")));

		int result = reportDao.boardInsert(boardBean);
		
		if (result == 1) {
			out.println("<script> alert('게시판 등록 성공!'); location.href='./rbmain.rb';</script>");
		} else {
			out.println("<script> alert('게시판 등록 실패!'); history.back();</script>");
		}
		out.close();

		return null;
	}

}
