package com.project101.action.message;

import javax.servlet.http.HttpServletRequest;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.MessageBoardBean;
import com.project101.dao.MessageDAO;

public class MessageAddAction2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		MessageBoardBean msBoardBean = new MessageBoardBean();
		MessageDAO msDAO = new MessageDAO();
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		msBoardBean.setMS_SEND(session.getAttribute("id").toString());
		System.out.println(request.getParameter("MS_NO"));
		msBoardBean.setMS_TO(request.getParameter("MS_TO"));
		msBoardBean.setMS_TITLE(request.getParameter("MS_TITLE"));
		msBoardBean.setMS_CONTENT(request.getParameter("MS_CONTENT"));		
		request.setAttribute("to", request.getParameter("MS_TO"));
		int result = msDAO.msInsert2(msBoardBean);
		
		request.setAttribute("result", result);
		
		forward.setRedirect(true);
		forward.setPath("template.jsp?page=./msmessagesendlist.ms");
		
		return forward;
	}
}