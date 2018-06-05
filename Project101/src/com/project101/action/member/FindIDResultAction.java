package com.project101.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.dao.MemberDAO;

public class FindIDResultAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String nickname = request.getParameter("nickname");
		String phone	 = request.getParameter("phone");
		MemberDAO mdao = new MemberDAO();
		
		String result = mdao.findID(nickname,phone);
		System.out.println("findidresultaction : " + result);
		if(result != null) {
			
			out.write("입력하신정보와 일치하는 아이디는 " + result + "입니다.");
		} else {
			
			out.write("입력하신정보와 일치하는 아이디가 없습니다.");
		}
		out.close();
		return null;
	}

}
