package com.project101.action.board.epilogue;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.EpilogueBoardBean;
import com.project101.dao.EpilogueBoardDAO;

public class EpilogueDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		boolean result = false;
		
		EpilogueBoardDAO ebDAO = new EpilogueBoardDAO();
		EpilogueBoardBean boardBean = new EpilogueBoardBean();

		int num = Integer.parseInt(request.getParameter("num"));
		boardBean.setSB_NO(num);

		result = ebDAO.boardDelete(boardBean);

		if (result == false) {
			out.println("<script>alert('삭제 실패');history.back();</script>");
		} else {
			out.println("<script>alert('삭제되었습니다.'); location ='./ebmain.eb';</script>");
		}

		out.close();

		forward.setRedirect(false);

		return null;
	}
}
