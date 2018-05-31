package com.project101.action.board.report;

import java.io.*;

import javax.servlet.http.*;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.dao.ReportBoardDAO;

public class ReportBoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
	
		int num = Integer.parseInt(request.getParameter("RB_NO"));
		ReportBoardDAO reportDAO = new ReportBoardDAO();

		int result = 0;
		result = reportDAO.boardDelete(num);

		if (result == 1) {
			out.println("<script>alert('삭제되었습니다.');location = './rbmain.rb';</script>");
		} else {
			out.println("<script>alert('삭제 실패!');history.back();</script>");
		}

		out.close();

		return null;
	}

}
