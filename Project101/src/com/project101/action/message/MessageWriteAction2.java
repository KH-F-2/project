package com.project101.action.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;

public class MessageWriteAction2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
		String id = session.getAttribute("id").toString();
		request.setAttribute("id", id);
		forward.setPath("./messageboard/msmessagewrite2.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
