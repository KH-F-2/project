package com.project101.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.Member;
import com.project101.dao.MemberDAO;

public class MemberinfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		MemberDAO mdao = new MemberDAO();
		Member m = mdao.member_info(email);
		
		forward.setPath("template.jsp?page=/member/mypage.jsp");
		forward.setRedirect(false);
		request.setAttribute("memberinfo", m);
		
		return forward;
	}

}
