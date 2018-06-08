package com.project101.bean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.dao.EpilDAO;

public class SignEpilContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			ActionForward forward = new ActionForward();
	      request.setCharacterEncoding("utf-8");
	      EpilDAO epDAO = new EpilDAO();
	      String name = request.getParameter("name"); 
	      
		  ArrayList<Epil> list = epDAO.getEpilList(name);
		  request.setAttribute("list", list);
	      
	      forward.setRedirect(false);
	      forward.setPath("./member/signepilcontent.jsp");	     
	      
	      return forward;
	}
}
