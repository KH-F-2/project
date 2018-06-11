package com.project101.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.Member;
import com.project101.dao.MemberDAO;

public class ConfirmEmailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		String num = request.getParameter("num");
		String email = request.getParameter("email");
		
		HttpSession session = request.getSession();
		System.out.println("here : " + num);
		System.out.println("here1 : " + session.getAttribute("authNum"));
		
		if (num.equals(session.getAttribute("authNum"))) {
			
			MemberDAO mdao = new MemberDAO();
			int result = mdao.emailCheckUpdate(email);
			System.out.println("인증 결과 : " + result);
			session.removeAttribute("authNum");
			
			forward.setPath("main.map");
			forward.setRedirect(false);
			
			return forward;
			
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();			
			out.print("<script>alert('인증실패');</script>");
			
			return null;
		}

	}

}
