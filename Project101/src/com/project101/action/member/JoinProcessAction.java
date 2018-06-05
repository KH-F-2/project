package com.project101.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.Member;
import com.project101.dao.MemberDAO;

public class JoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		String emailcheck = request.getParameter("emailconfirm_value");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String phone = request.getParameter("phone");
		String post = request.getParameter("post");
		String address = request.getParameter("address");
		String detailaddress = request.getParameter("detailaddress");
	
		
		Member m = new Member();
		
		m.setEmail(email);
		m.setEmailcheck(emailcheck);
		m.setPassword(password);
		m.setNickname(nickname);
		m.setPhone(phone);
		m.setPost(post);
		m.setAddress(address);
		m.setDetailaddress(detailaddress);
	
		
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
