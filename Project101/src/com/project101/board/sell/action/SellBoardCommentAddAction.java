package com.project101.board.sell.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.board.sell.db.CommentBean;
import com.project101.board.sell.db.CommentDAO;

public class SellBoardCommentAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
			
		CommentBean commentBean = new CommentBean();
		CommentDAO commentDAO = new CommentDAO();
		
		commentBean.setBOARD_NO(Integer.parseInt(request.getParameter("SB_NO")));
		commentBean.setCOMMENT_WRITER(session.getAttribute("id").toString());
		commentBean.setCOMMENT_CONTENT(request.getParameter("content"));
		
		int COMMENT_NO = commentDAO.commentInsert(commentBean);
		
		if(COMMENT_NO == 0) {
			System.out.println("SellBoardCommentAddAction fail!");
			return null;
		}
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		commentBean.setCOMMENT_DATE(sdf.format(date));
		commentBean.setCOMMENT_NO(COMMENT_NO);
		
		request.setAttribute("commentBean", commentBean);
		
		forward.setPath("sellboard/sbcommentlist.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
