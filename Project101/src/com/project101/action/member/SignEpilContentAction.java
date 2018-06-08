package com.project101.action.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.Epil;
import com.project101.dao.EpilDAO;

public class SignEpilContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			ActionForward forward = new ActionForward();
	      request.setCharacterEncoding("utf-8");
	      EpilDAO epDAO = new EpilDAO();
	      HttpSession session = request.getSession();
	      String name = session.getAttribute("id").toString().trim(); 
	      System.out.println("name = " + name);
	      String writer = request.getParameter("writer");
	      System.out.println("zzzz : " + writer);
	      
		  ArrayList<Epil> list = epDAO.getEpilList(writer);
		  request.setAttribute("list", list);
		  request.setAttribute("writer", writer);
	      forward.setRedirect(false);
	      forward.setPath("./member/signepilcontent.jsp");	     
	      
	      return forward;
	}
}
