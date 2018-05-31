package com.project101.action.board.epilogue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.EpilogueBoardBean;
import com.project101.dao.EpilogueBoardDAO;

public class EpilogueView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/htmlcharset=euc-kr");
		request.setCharacterEncoding("euc-kr");
		
		EpilogueBoardDAO ebDAO = new EpilogueBoardDAO();
		EpilogueBoardBean ebBoardBean = new EpilogueBoardBean();
		ActionForward forward = new ActionForward();

		int num = Integer.parseInt(request.getParameter("num"));

		ebDAO.setReadCountUpdate(num);
		ebBoardBean = ebDAO.getDetail(num);

		request.setAttribute("ebBoardBean", ebBoardBean);

		if (ebBoardBean == null) {
			System.out.println("상세보기 실패");
			return null;
		} else {
			System.out.println("상세보기 성공");
			forward.setRedirect(false);
			forward.setPath("template.jsp?page=/epilogueboard/ebview.jsp");
		}

		return forward;
	}
}
