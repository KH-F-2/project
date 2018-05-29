package com.project101.board.sell.action;

import java.io.PrintWriter;
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
		ActionForward forward=new ActionForward();
		HttpSession session = request.getSession();
			
		CommentBean comment=new CommentBean();
		CommentDAO commentdao=new CommentDAO();
		comment.setBOARD_NO(Integer.parseInt(request.getParameter("SB_NO")));
		comment.setCOMMENT_WRITER(session.getAttribute("id").toString());
		comment.setCOMMENT_CONTENT(request.getParameter("content"));
		
		int comment_no=commentdao.commentInsert(comment);
		
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		comment.setCOMMENT_DATE(sdf.format(date));
		comment.setCOMMENT_NO(comment_no);
		request.setAttribute("comment", comment);
		
		forward.setPath("sellboard/sell_board_commentlist.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
