package com.project101.action.comment;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.*;
import com.project101.bean.*;
import com.project101.dao.*;

public class CommentWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CommentDAO cmtdao = new CommentDAO();
		CommentBean cmtdata = new CommentBean();
		System.out.println("이거="+request.getParameter("num"));
		
		int comment_boardNO = Integer.parseInt(request.getParameter("num"));
		String comment_content = request.getParameter("comment_content");

		cmtdata.setCMT_NO(cmtdao.getSeq());			//댓글 번호
		cmtdata.setCMT_SUBJECT_NO(comment_boardNO);	//게시글 번호
		cmtdata.setCMT_CONTENT(comment_content); 	//댓글 내용
		
		/*boolean result = cmtdao.commentInsert(cmtdata);
		
		PrintWriter pw = response.getWriter();
		if(result) {
			pw.println(1);
		} else {
			pw.println(0);
		}
		pw.close();*/

		return null;
	}

}
