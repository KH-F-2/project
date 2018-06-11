package com.project101.action.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.MessageBoardBean;
import com.project101.dao.MessageDAO;

public class MessageSendDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		MessageBoardBean msBoardBean = new MessageBoardBean();
		MessageDAO msDAO = new MessageDAO();
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		String id = session.getAttribute("id").toString();
		
		int MS_NO = Integer.parseInt(request.getParameter("num"));
		System.out.println("MS_NO :" + Integer.parseInt(request.getParameter("num")));
		msBoardBean = msDAO.getDetail(MS_NO);
		
		if(msBoardBean == null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
		
		request.setAttribute("ms", msBoardBean);
		
		forward.setRedirect(false);
		forward.setPath("./messageboard/msmessagesenddetail.jsp");
		
		return forward;
	}
}
