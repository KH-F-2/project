package com.project101.action.comment;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.*;
import com.project101.bean.*;
import com.project101.dao.*;

public class CommentWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
<<<<<<< HEAD
		CommentDAO cmtdao = new CommentDAO();
		CommentBean cmtdata = new CommentBean();
		System.out.println("이거="+request.getParameter("num"));
		
		int comment_boardNO = Integer.parseInt(request.getParameter("num"));
		String comment_content = request.getParameter("comment_content");

		cmtdata.setCMT_NO(cmtdao.getSeq());			//댓글 번호
		cmtdata.setCMT_SUBJECT_NO(comment_boardNO);	//게시글 번호
		cmtdata.setCMT_CONTENT(comment_content); 	//댓글 내용
		
		/*boolean result = cmtdao.commentInsert(cmtdata);
=======
		CommentDAO cmtDAO = new CommentDAO();
		CommentBean cmtBean = new CommentBean();
		HttpSession session = request.getSession(); //세션 값 가져오기
		
	
		int comment_boardNO = Integer.parseInt(request.getParameter("CMT_SUBJECT_NO"));
		
		String comment_content = request.getParameter("CMT_CONTENT");
		String board_name = request.getParameter("CMT_BOARD_NAME");
		
		cmtBean.setCMT_NO(cmtDAO.getSeq());			//댓글 번호
		cmtBean.setCMT_SUBJECT_NO(comment_boardNO);	//게시글 번호
		cmtBean.setCMT_CONTENT(comment_content); 	//댓글 내용
		
		cmtBean.setCMT_WRITER(session.getAttribute("id").toString()); //세션 값 등록
		
		
		boolean result = cmtDAO.cmtInsert(cmtBean, board_name);
>>>>>>> younsik
		
		PrintWriter pw = response.getWriter();
		if(result) {
<<<<<<< HEAD
			pw.println(1);
=======
		
			pw.print(1);
		
>>>>>>> younsik
		} else {
			pw.println(0);
		}
<<<<<<< HEAD
		pw.close();*/

=======
		pw.close();
		
>>>>>>> younsik
		return null;
		
		
	}

}
