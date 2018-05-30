package com.project101.board.epilogue.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.epilogue.db.EpilogueBoardBean;
import com.project101.board.epilogue.db.EpilogueBoardDAO;

public class EpilogueBoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int num = Integer.parseInt(request.getParameter("num").trim());
		ActionForward forward = new ActionForward();
		EpilogueBoardDAO ebDAO = new EpilogueBoardDAO();
		EpilogueBoardBean ebBoardBean = new EpilogueBoardBean();
		
		ebBoardBean = ebDAO.getDetail(num);
		
		if (ebBoardBean==null) {
			System.out.println("(수정)상세보기 실패");
			return null;
		}
		System.out.println("(수정)상세보기 성공");
			
		request.setAttribute("ebBoardBean", ebBoardBean);		
		forward.setRedirect(false);
		
		forward.setPath("./epilogueboard/ebmodify.jsp");	
		return forward;
	}
}