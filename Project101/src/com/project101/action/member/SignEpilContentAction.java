package com.project101.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;

public class SignEpilContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
	      request.setCharacterEncoding("utf-8");
	      String content = request.getParameter("review");
	      String star = request.getParameter("star");
	      System.out.println("star :" + star);

	      request.setAttribute("epil", content);
	      request.setAttribute("star", star);
	      
	      forward.setRedirect(false);
	      forward.setPath("./member/signepilcontent.jsp");	     
	      
	      return forward;
	}
}
