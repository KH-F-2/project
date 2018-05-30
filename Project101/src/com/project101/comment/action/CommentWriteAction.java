package com.project101.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.comment.db.CommentBean;
import com.project101.comment.db.CommentDAO;

public class CommentWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CommentDAO cmtDAO = new CommentDAO();
		CommentBean cmtBean = new CommentBean();
		System.out.println("이거=" + request.getParameter("num"));
		
		int comment_boardNO = Integer.parseInt(request.getParameter("num"));
		String comment_content = request.getParameter("comment_content");

		cmtBean.setCMT_NO(cmtDAO.getSeq());			//댓글 번호
		cmtBean.setCMT_SUBJECT_NO(comment_boardNO);	//게시글 번호
		cmtBean.setCMT_CONTENT(comment_content); 	//댓글 내용
		
		boolean result = cmtDAO.cmtInsert(cmtBean);
		
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
