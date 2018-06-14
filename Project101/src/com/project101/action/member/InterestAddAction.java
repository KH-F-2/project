package com.project101.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.dao.InterestDAO;
import com.project101.dao.ReportBoardDAO;

public class InterestAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		InterestDAO interestDAO = new InterestDAO();
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		int content_num = Integer.parseInt(request.getParameter("content_num"));
		String board_name = request.getParameter("board_name");
		
		int result = interestDAO.countInterest(id);

		if (result == 5) {
			
			out.print(result);
			out.close();
			
			return null;
		} else {
			
			result = interestDAO.boardInsert(id, content_num, board_name);
			
			out.print(result);
			out.close();
			
			return null;
		}		
		
	}
}
