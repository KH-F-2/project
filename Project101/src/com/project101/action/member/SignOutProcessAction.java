package com.project101.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;

public class SignOutProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		session.invalidate();
	
		PrintWriter pw = response.getWriter();
		pw.println("<script>document.location.href='main.me';</script>");
		pw.close();

		return null;
	}

}
