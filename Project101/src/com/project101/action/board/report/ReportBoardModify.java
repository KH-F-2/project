package com.project101.action.board.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.ReportBoardBean;
import com.project101.dao.ReportBoardDAO;

public class ReportBoardModify implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ActionForward forward = new ActionForward();
		ReportBoardDAO reportDao = new ReportBoardDAO();
		ReportBoardBean boardBean = new ReportBoardBean();

		int num = Integer.parseInt(request.getParameter("RB_NO"));

		System.out.println("num : " + num);

		boardBean = reportDao.getDetail(num);

		if (boardBean == null) {
			System.out.println("수정 상세보기 실패");
			return null;
		}
		System.out.println("수정 상세보기 완료");

		request.setAttribute("boardBean", boardBean);
		forward.setRedirect(false);

		forward.setPath("/reportboard/rb_modify.jsp");
		return forward;
	}

}
