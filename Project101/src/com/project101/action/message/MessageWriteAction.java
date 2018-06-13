package com.project101.action.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;

public class MessageWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
		String id = session.getAttribute("id").toString();
		int num = Integer.parseInt(request.getParameter("num"));
		String writer = request.getParameter("writer");
		request.setAttribute("id", id);
		request.setAttribute("num", num);
		request.setAttribute("writer", writer);
		
		forward.setPath("template.jsp?page=./messageboard/msmessagewrite.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
