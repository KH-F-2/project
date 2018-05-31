package com.project101.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;


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
