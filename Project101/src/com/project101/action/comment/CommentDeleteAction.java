package com.project101.action.comment;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.dao.CommentDAO;

public class CommentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		CommentDAO commentDAO = new CommentDAO();
		
		int cmt_no= Integer.parseInt(request.getParameter("CMT_NO"));
		int CMT_SUBJECT_NO = Integer.parseInt(request.getParameter("CMT_SUBJECT_NO"));
		String board_name = request.getParameter("CMT_BOARD_NAME");
		String url = request.getParameter("url");
		
		int result = commentDAO.commentDelete(cmt_no, board_name);
		if(result == 0) {
			System.out.println("CommentDeleteAction.java : commentDelete fail");
		}

		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath(url + CMT_SUBJECT_NO + "&board_name=" + board_name);
		return forward;
	}

}
