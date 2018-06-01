package com.project101.action.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.*;
import com.project101.bean.*;
import com.project101.dao.*;

public class CommentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		CommentDAO commentDAO = new CommentDAO();
		
		int cmt_no= Integer.parseInt(request.getParameter("CMT_NO"));
		int board_no = Integer.parseInt(request.getParameter("BOARD_NO"));
		String board_name = request.getParameter("CMT_BOARD_NAME");
		String url = request.getParameter("URL");
		
		int result = commentDAO.commentDelete(cmt_no, board_name);
		if(result == 0) {
			System.out.println("CommentDeleteAction.java : commentDelete fail");
		}

		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath(url + board_no);
		return forward;
	}

}
