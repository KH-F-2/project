package com.project101.board.epilogue.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.epilogue.db.EpilogueBoardBean;
import com.project101.board.epilogue.db.EpilogueBoardDAO;

public class EpilogueBoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ActionForward forward = new ActionForward();
		int num = Integer.parseInt(request.getParameter("num"));
		EpilogueBoardDAO ebDAO = new EpilogueBoardDAO();
		EpilogueBoardBean ebBoardBean = new EpilogueBoardBean();
		PrintWriter out = response.getWriter();
		boolean result = false;		
		
		ebBoardBean.setSB_NO(num);
		
		result = ebDAO.boardDelete(ebBoardBean);
		
		if (result == false) {
			out.println("<script>alert('삭제 실패');history.back();</script>");
		} else {
			out.println("<script>alert('삭제되었습니다.'); location ='./ebmain.eb';</script>");
		}
				
		out.close();
		
		forward.setRedirect(false);
		
		return null;
	}
}
