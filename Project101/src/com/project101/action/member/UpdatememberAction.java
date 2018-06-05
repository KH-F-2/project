package com.project101.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.Member;
import com.project101.dao.MemberDAO;

public class UpdatememberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String phone = request.getParameter("phone");
		String post = request.getParameter("post");
		String address = request.getParameter("address");
		String detailaddress = request.getParameter("detailaddress");
		
		
		Member m = new Member();
		
		m.setId(id);
		m.setEmail(email);
		m.setPassword(password);
		m.setNickname(nickname);
		m.setPhone(phone);
		m.setPost(post);
		m.setAddress(address);
		m.setDetailaddress(detailaddress);
	
		
		response.setCharacterEncoding("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		MemberDAO mdao = new MemberDAO();
		int result = mdao.update(m);
		out.println("<script>");
		
		if(result == 1 ) {
			out.println("alert(' �쉶�썝 �젙蹂닿� �닔�젙�릺�뿀�뒿�땲�떎.') ;");
			out.println("location.href='main.me'; ");
		}else {
			out.println("alert(' �떎�떆�엯�젰�빐二쇱꽭�슂 ' ); ");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();
		
		
		return null;
	}

}
