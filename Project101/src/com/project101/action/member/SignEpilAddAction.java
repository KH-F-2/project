package com.project101.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.Epil;
import com.project101.dao.EpilDAO;

public class SignEpilAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out=response.getWriter();
		
		EpilDAO epDAO = new EpilDAO();
		Epil epBoardBean = new Epil();
		String name = request.getParameter("name");
		
		HttpSession session = request.getSession();
		epBoardBean.setEP_NAME(name);
		epBoardBean.setEP_WRITER(session.getAttribute("id").toString());
		epBoardBean.setEP_CONTENT(request.getParameter("content"));
		epBoardBean.setEP_STAR(Integer.parseInt(request.getParameter("star")));
		
		int result = epDAO.epInsert(epBoardBean);
		
		if(result == 1 ) {
			out.println("<script> alert('후기 작성 완료'); location.href='mypage.me?id=" + name + "';</script>");
		}else {
			out.println("<script> alert('후기 작성 실패'); history.back()';</script>");
		}
		
		return forward;
	}
}
