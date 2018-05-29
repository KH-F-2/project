package com.project101.board.report.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.report.db.ReportBoardBean;
import com.project101.board.report.db.ReportBoardDAO;

public class ReportBoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ActionForward forward = new ActionForward();
		ReportBoardDAO reportdao = new ReportBoardDAO();
		ReportBoardBean reportboard = new ReportBoardBean();
		
		int num = Integer.parseInt(request.getParameter("RB_NO"));
		
		System.out.println("num : " + num);
		
		reportboard = reportdao.getDetail(num);
		
		if(reportboard == null) {
			System.out.println("수정 상세보기 실패");
			return null;
		}
		System.out.println("수정 상세보기 완료");
		
		request.setAttribute("reportboard", reportboard);
		forward.setRedirect(false);
		
		forward.setPath("./reportboard/report_board_modify.jsp");
		return forward;
	}

}
