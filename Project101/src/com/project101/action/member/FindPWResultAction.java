package com.project101.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.dao.MemberDAO;

public class FindPWResultAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		MemberDAO mdao = new MemberDAO();
		
		String result = mdao.findPW(id, email);
		System.out.println("findPwresultaction : " + result);
		if ( result != null) {
			
			out.write("입력하신정보의비밀번호는 " +result+ "입니다.");
		}else {
			out.write("일치하는정보가없습니다.");
		}
		return null;
	}

}
