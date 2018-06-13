package com.project101.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.Member;
import com.project101.dao.MemberDAO;

public class ModifyInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		MemberDAO mdao = new MemberDAO();
		Member m = mdao.member_info(id);
		
		forward.setPath("template.jsp?page=/member/modifyinfo.jsp");
		forward.setRedirect(false);
		request.setAttribute("memberinfo", m);
		
		return forward;
	}

}
