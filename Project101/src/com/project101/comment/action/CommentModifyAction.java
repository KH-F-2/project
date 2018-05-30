package com.project101.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.comment.db.CommentBean;
import com.project101.comment.db.CommentDAO;

public class CommentModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		CommentDAO cmtDAO = new CommentDAO();
		CommentBean cmtBean = new CommentBean();  

		int num = Integer.parseInt(request.getParameter("cnum"));
		
		System.out.println("ㅇㅇㅇㅇㅇㅇ" + num);
		
		cmtBean = cmtDAO.getDetail(num);
		
		if (cmtBean == null) {
			System.out.println("수정 이동 실패");
			return null;
		} else {
			System.out.println("수정 이동 완료");
		
			request.setAttribute("cmtBean", cmtBean);
			
			forward.setRedirect(false);
			forward.setPath("template.jsp?page=./purchaseboard/pbview.jsp");
			return forward;
		}
				
	}

}
