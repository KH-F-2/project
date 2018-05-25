package com.project101.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.member.db.Member;
import com.project101.member.db.MemberDAO;

public class joinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String id= request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String school = request.getParameter("school");
		String major = request.getParameter("major");
		String college = request.getParameter("college");
		
		Member m = new Member();
		m.setId(id);
		m.setPassword(pass);
		m.setName(name);
		m.setEmail(email);
		m.setTel(tel);
		m.setAddress(address);
		m.setSchool(school);
		m.setMajor(major);
		m.setCollege(college);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		MemberDAO mdao = new MemberDAO();
		int result = mdao.insert(m);
		System.out.println(result);
		out.println("<script>");
		if(result == 1) {
			out.println("alert('회원가입을 축하합니다');");
			out.println("location.href='main.mem';");
		}else if(result== -1) {
			out.println("alert('다시입력해주세요~');");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();
		return null;
	}

}
