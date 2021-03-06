package com.project101.action.board.sell;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.*;
import com.project101.bean.*;
import com.project101.dao.*;

public class SellBoardCommentAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
			
		CommentBean commentBean = new CommentBean();
		CommentDAO commentDAO = new CommentDAO();
		
		int CMT_SUBJECT_NO = Integer.parseInt(request.getParameter("SB_NO"));
		String CMT_BOARD_NAME = "SELL_BOARD";
		
		commentBean.setCMT_SUBJECT_NO(CMT_SUBJECT_NO);
		commentBean.setCMT_WRITER(session.getAttribute("id").toString());
		commentBean.setCMT_CONTENT(request.getParameter("content"));
		
		int result = commentDAO.commentInsert(commentBean, CMT_BOARD_NAME);
		
		if(result == 0) {
			System.out.println("SellBoardCommentAddAction fail!");
			return null;
		}
		/*Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		commentBean.setCOMMENT_DATE(sdf.format(date));
		commentBean.setCOMMENT_NO(COMMENT_NO);*/
		
		//request.setAttribute("commentBean", commentBean);
		
		forward.setPath("sbview.sb?ajax=" + CMT_SUBJECT_NO);
		forward.setRedirect(false);
		
		return forward;
	}

}
