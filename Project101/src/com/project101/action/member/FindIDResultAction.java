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
		String email	 = request.getParameter("email");
		MemberDAO mdao = new MemberDAO();
		
		String result = mdao.findID(nickname,email);
		System.out.println("findidresultaction : " + result);
		if(result != null) {
			
			out.write("�엯�젰�븯�떊�젙蹂댁� �씪移섑븯�뒗 �븘�씠�뵒�뒗 " + result + "�엯�땲�떎.");
		} else {
			
			out.write("�엯�젰�븯�떊�젙蹂댁� �씪移섑븯�뒗 �븘�씠�뵒媛� �뾾�뒿�땲�떎.");
		}
		out.close();
		return null;
	}

}
