package com.project101.board.report.action;

import java.io.*;
import javax.servlet.http.*;
import com.project101.board.report.db.*;

public class ReportBoardAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		ReportBoardDAO reportdao = new ReportBoardDAO();
		ReportBoardBean reportboard = new ReportBoardBean();
		
		reportboard.setRB_WRITER(request.getParameter("RB_WRITER"));
		reportboard.setRB_TITLE(request.getParameter("RB_TITLE"));
		reportboard.setRB_CONTENT(request.getParameter("RB_CONTENT"));
		reportboard.setRB_PRICE(Integer.parseInt(request.getParameter("RB_PRICE")));
		
		int result = reportdao.boardInsert(reportboard);
		if(result==1) {
			out.println("<script> alert('게시판 등록 성공!'); location.href='./BoardList.report';</script>");
		}else {
			out.println("<script> alert('게시판 등록 실패!'); history.back();</script>");
		}
		out.close();
		
		return null;
	}

}
