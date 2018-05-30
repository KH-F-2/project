package com.project101.board.report.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.report.db.*;

public class ReportBoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=euc-kr");
		request.setCharacterEncoding("utf-8");
		ReportBoardDAO reportDao = new ReportBoardDAO();
		ReportBoardBean boardBean = new ReportBoardBean();
		ActionForward forward = new ActionForward();

		int num = Integer.parseInt(request.getParameter("RB_NO"));

		reportDao.setReadCountUpdate(num);
		boardBean = reportDao.getDetail(num);

		request.setAttribute("boardbean", boardBean);

		if (boardBean == null) {
			System.out.println("상세보기 실패!");
			return null;
		} else {
			System.out.println("상세보기 성공!");
			forward.setRedirect(false);
			forward.setPath("reportboard/rb_view.jsp");
		}

		return forward;
	}

}
