package com.project101.action.board.report;

import java.io.PrintWriter;

import javax.servlet.http.*;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.ReportBoardBean;
import com.project101.dao.ReportBoardDAO;

public class ReportBoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ActionForward forward = new ActionForward();

		int num = Integer.parseInt(request.getParameter("RB_NO").trim());

		ReportBoardDAO reportDao = new ReportBoardDAO();
		ReportBoardBean boardBean = new ReportBoardBean();
		HttpSession session = request.getSession();

		boardBean.setRB_NO(num);
		boardBean.setRB_TITLE(request.getParameter("RB_TITLE"));
		boardBean.setRB_CONTENT(request.getParameter("RB_CONTENT"));
		boardBean.setRB_PRICE(Integer.parseInt(request.getParameter("RB_PRICE").toString()));

		System.out.println("rb_content===" + boardBean.getRB_CONTENT());
		int result = reportDao.boardModify(boardBean);
		PrintWriter out = response.getWriter();
		if (result == 1) {

			out.println("<script> alert('게시판 수정 성공!');");
			out.println("location.href='./rbmain.rb?RB_NO=" + num + "';");
			out.println("</script>");
		} else {

			out.println("<script> alert('게시판 수정 실패!'); history.back();</script>");
		}
		out.close();

		return null;
	}

}
