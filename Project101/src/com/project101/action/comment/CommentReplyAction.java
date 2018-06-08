package com.project101.action.comment;

<<<<<<< HEAD
=======
import java.io.PrintWriter;

>>>>>>> younsik
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

<<<<<<< HEAD
import com.project101.action.*;
import com.project101.bean.*;
import com.project101.dao.*;

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
		forward.setPath(url + CMT_SUBJECT_NO);
		
		return forward;
	}

}


=======
import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.CommentBean;
import com.project101.dao.CommentDAO;


public class CommentReplyAction implements Action {

	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		CommentDAO cmtDAO = new CommentDAO();
		CommentBean cmtBean = new CommentBean();
		HttpSession session = request.getSession();
		
		int comment_boardNO = Integer.parseInt(request.getParameter("CMT_SUBJECT_NO"));
		int comment_cmtNO = Integer.parseInt(request.getParameter("CMT_NO"));
		String board_name = request.getParameter("CMT_BOARD_NAME");
		String comment_content = request.getParameter("CMT_CONTENT");
		
		cmtBean.setCMT_NO(cmtDAO.getSeq());			//댓글 번호
		cmtBean.setCMT_SUBJECT_NO(comment_boardNO);	//게시글 번호
		cmtBean = cmtDAO.getDetail(comment_cmtNO);
		cmtBean.setCMT_CONTENT(comment_content); 	//댓글 내용
		cmtBean.setCMT_WRITER(session.getAttribute("id").toString());
		
		boolean result = cmtDAO.cmtReplyInsert(cmtBean, comment_boardNO, comment_cmtNO, board_name);
		
		PrintWriter pw = response.getWriter();
		
		if(result) {
			pw.print(1);
		} else {
			pw.print(0);
		}
		pw.close();
		return null;
		
		

		
	}

}
>>>>>>> younsik
