package com.project101.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.dao.MemberDAO;
import com.project101.dao.MessageDAO;
import com.project101.dao.NoticeDao;

public class SignInProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.isId(id, password);
		System.out.println("결과는 : " + result);
		
		if (result == 1) {
			HttpSession session = request.getSession();
			// 로그인 성공
			
			MessageDAO messageDAO = new MessageDAO();
			int receivedMessage = messageDAO.getToListCount(id);
			
			if (receivedMessage != 0) {
				session.setAttribute("receivedMessage", receivedMessage);
			}
			
			session.setAttribute("id", id);
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(result);
		pw.close();
		
		return null;
		
	}

}
