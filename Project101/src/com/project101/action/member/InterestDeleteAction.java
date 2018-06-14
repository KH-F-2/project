package com.project101.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.EpilogueBoardBean;
import com.project101.dao.EpilogueBoardDAO;
import com.project101.dao.InterestDAO;

public class InterestDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		int result = 0;

		InterestDAO interestDAO = new InterestDAO();
		
		int content_num = Integer.parseInt(request.getParameter("content_num"));
		String board_name = request.getParameter("board_name");

		result = interestDAO.deleteInterest(content_num, board_name);

		out.println(result);

		out.close();

		return null;
	}

}
