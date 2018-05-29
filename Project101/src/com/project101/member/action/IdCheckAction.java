package com.project101.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.member.db.MemberDAO;

public class IdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		  ActionForward forward = new ActionForward();
	      request.setCharacterEncoding("UTF-8");
	      MemberDAO mdao = new MemberDAO();
	      String id = request.getParameter("id");
	      int result = mdao.idcheck(id);
	      
	      response.setContentType("text/html;charset=UTF-8");
	      PrintWriter out=response.getWriter();
	         out.println(result);
	      return null;
	}

}
