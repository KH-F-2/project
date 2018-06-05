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
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String phone = request.getParameter("phone");
		MemberDAO mdao = new MemberDAO();
		
		String result = mdao.findPW(email, nickname, phone);
		System.out.println("findPwresultaction : " + result);
		if ( result != null) {
			
			out.write("입력하신 정보와 일치하는 비밀번호는 " +result+ "입니다.");
		}else {
			out.write("입력하신 정보와 일치하는 정보가 없습니다.");
		}
		return null;
	}

}
