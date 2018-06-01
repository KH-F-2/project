package com.project101.action.comment;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.dao.CommentDAO;

public class CommentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int cmtNum = Integer.parseInt(request.getParameter("cmtNum"));
		int num = Integer.parseInt(request.getParameter("num"));
		String board_name = request.getParameter("CMT_BOARD_NAME");
		String URL = request.getParameter("URL");
		
		int result;

		CommentDAO cmtdao = new CommentDAO();

		result = cmtdao.cmtDelete(cmtNum, board_name);

		if (result == 1) {

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();

			out.println("<script>");
			out.println("alert('댓글삭제 되었습니다')");
			out.println("location.href='"+URL+num+"'");
			out.println("</script>");
			out.close();

			System.out.println("댓글삭제 성공");
		} else {

			System.out.println("댓글삭제 실패");
		}
		return null;

	
	}

}
