package com.project101.board.report.action;

import java.io.*;

import javax.servlet.http.*;

import com.project101.board.report.db.*;

public class ReportBoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		ActionForward forward = new ActionForward();
		int num = Integer.parseInt(request.getParameter("RB_NO"));
		ReportBoardDAO reportDao = new ReportBoardDAO();
		ReportBoardBean boardBean = new ReportBoardBean();
		PrintWriter out = response.getWriter();

		int result = 0;

		result = reportDao.boardDelete(num);

		if (result == 1) {
			out.println("<script>alert('삭제되었습니다.');location = './rbmain.rb';</script>");
		} else {
			out.println("<script>alert('삭제 실패!');history.back();</script>");
		}

		out.close();

		return null;
	}

}
