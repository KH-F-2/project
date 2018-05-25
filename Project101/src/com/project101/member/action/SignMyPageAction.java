package com.project101.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.member.db.Member;
import com.project101.member.db.MemberDAO;

public class SignMyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
	      request.setCharacterEncoding("utf-8");
	      String id = request.getParameter("id");
	      MemberDAO memberDAO = new MemberDAO();
	      Member member = memberDAO.member_info(id);
	      
	      forward.setPath("./member/signinfo.jsp");
	      forward.setRedirect(false);
	      	request.setAttribute("signinfo", member);
	      	return forward;
	}

}
