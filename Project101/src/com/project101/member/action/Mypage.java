package com.project101.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.member.db.Endhistory;
import com.project101.member.db.EndhistoryDAO;

public class Mypage implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/htmlcharset=euc-kr");
		request.setCharacterEncoding("euc-kr");

		ActionForward forward = new ActionForward();

		forward.setRedirect(false);
		forward.setPath("template.jsp?page=./member/mypage.jsp");

		return forward;
	}

}
