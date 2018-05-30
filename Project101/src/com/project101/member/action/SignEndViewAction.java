package com.project101.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.member.db.Endhistory;
import com.project101.member.db.EndhistoryDAO;

public class SignEndViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/htmlcharset=euc-kr");
		request.setCharacterEncoding("euc-kr");
		
		EndhistoryDAO ehdao = new EndhistoryDAO();
		Endhistory eh = new Endhistory();
		ActionForward forward = new ActionForward();
		
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("num = " + num);
		eh = ehdao.getDetail(num);
		
		request.setAttribute("eh", eh);
		
		if(eh == null) {
			System.out.println("상세보기 실패");
			return null;
		} else {
			System.out.println("상세보기 성공");
			forward.setRedirect(false);
			forward.setPath("./member/signendhistoryview.jsp");
		}
		return forward;
	}
}
