package com.project101.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.comment.db.CommentBean;
import com.project101.comment.db.CommentDAO;

public class CommentModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		CommentDAO cmtdao = new CommentDAO();
		CommentBean cmtdata = new CommentBean();  

		int num = Integer.parseInt(request.getParameter("cnum"));
		
		System.out.println("ㅇㅇㅇㅇㅇㅇ"+num);
		
		cmtdata = cmtdao.getDetail(num);
		
		if (cmtdata == null) {
			System.out.println("수정 이동 실패");
			return null;
		} else {
			System.out.println("수정 이동 완료");
		}
		request.setAttribute("cmtdata", cmtdata);

		forward.setRedirect(false);
		forward.setPath("./buy/buy_board_view.jsp");
		return forward;
		
		
		
		
	}

}
