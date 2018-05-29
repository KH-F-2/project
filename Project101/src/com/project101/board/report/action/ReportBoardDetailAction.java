package com.project101.board.report.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.report.db.*;

public class ReportBoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=euc-kr");
		request.setCharacterEncoding("utf-8");
		ReportBoardDAO reportdao=new ReportBoardDAO();
		ReportBoardBean reportboard=new ReportBoardBean();
		ActionForward forward=new ActionForward();
		
		int num=Integer.parseInt(request.getParameter("RB_NO"));
		
		reportdao.setReadCountUpdate(num);
		reportboard=reportdao.getDetail(num);
		
		request.setAttribute("reportboard", reportboard);
		
		if(reportboard==null) {
			System.out.println("상세보기 실패!");
			return null;
		}else {
			System.out.println("상세보기 성공!");
			forward.setRedirect(false);
			forward.setPath("reportboard/report_board_view.jsp");
		}
		
		
		return forward;
	}

}
