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
		
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("num : "+num);
		ActionForward forward = new ActionForward();
		EpilogueBoardDAO epildao = new EpilogueBoardDAO();
		EpilogueBoardBean epildata = new EpilogueBoardBean();
		
		epildata = epildao.getDetail(num);
		
		if(epildata==null) {
			System.out.println("(수정)상세보기 실패");
			return null;
		}
		System.out.println("(수정)상세보기 성공");
			
		request.setAttribute("epildata", epildata);		
		forward.setRedirect(false);
		
		forward.setPath("./epilogueboard/epilogue_board_modify.jsp");	
		return forward;
	}
}