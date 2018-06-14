package com.project101.action.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.CommentBean;
import com.project101.dao.CommentDAO;

public class CommentReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		CommentDAO commentDAO = new CommentDAO();
		CommentBean commentBean = new CommentBean();
		
		int CMT_NO = Integer.parseInt(request.getParameter("CMT_NO"));
		String board_name = request.getParameter("CMT_BOARD_NAME");
		
		commentBean = commentDAO.getCommentDetail(CMT_NO, board_name);
		
		String CMT_WRITER = session.getAttribute("id").toString();
		String CMT_CONTENT = request.getParameter("CMT_CONTENT");
		String url = request.getParameter("url");
		int CMT_SUBJECT_NO = Integer.parseInt(request.getParameter("CMT_SUBJECT_NO"));
		/*
		 	CMT_NO,DATE : 자동
		 	SUBJECT_NO : 부모댓글과 같은 값
		 	REF,LEV,SEQ : 부모댓글 값 이용
		 	WRITER : session
		*/
		commentBean.setCMT_CONTENT(CMT_CONTENT);
		commentBean.setCMT_WRITER(CMT_WRITER);
		
		
		boolean result = commentDAO.commentReply(commentBean, board_name);
		if (result == false) {
			return null;
		}

		forward.setRedirect(false);
		forward.setPath(url + CMT_SUBJECT_NO + "&board_name=" + board_name);

		return forward;
	}

}


