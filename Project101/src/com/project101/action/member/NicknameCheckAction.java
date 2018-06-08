package com.project101.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.dao.MemberDAO;

public class NicknameCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  ActionForward forward = new ActionForward();
	      request.setCharacterEncoding("UTF-8");
	      MemberDAO mdao = new MemberDAO();
	      String nickname = request.getParameter("nickname");
	      int result = mdao.nicknamecheck(nickname);
	      
	      response.setContentType("text/html;charset=UTF-8");
	      PrintWriter out=response.getWriter();
	         out.println(result);
		return null;
	}

}
