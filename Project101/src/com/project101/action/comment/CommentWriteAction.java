package com.project101.action.comment;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.CommentBean;
import com.project101.dao.CommentDAO;

public class CommentWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
