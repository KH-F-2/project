package com.project101.board.epilogue.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.epilogue.db.EpilogueBoardBean;
import com.project101.board.epilogue.db.EpilogueBoardDAO;

public class EpilogueBoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");		
		ActionForward forward = new ActionForward();
		
		//공백제거 해줘야됨 ㅡㅡ
		int num = Integer.parseInt(request.getParameter("SB_NO").trim());
		
		EpilogueBoardDAO epildao = new EpilogueBoardDAO();
		EpilogueBoardBean epildata = new EpilogueBoardBean();
		
		//수정내용 설정
		epildata.setSB_NO(num);
		epildata.setSB_TITLE(request.getParameter("SB_TITLE"));
		epildata.setSB_CONTENT(request.getParameter("SB_CONTENT"));
		
		int result1 = epildao.boardModify(epildata);
		System.out.println("result1 : " + result1);
		if(result1 == 0) {
			System.out.println("게시판 수정 실패");
			return null;
		}
		System.out.println("게시판 수정 완료");
		
		forward.setRedirect(true);
		
		forward.setPath("./BoardDetail.epil?num=" + epildata.getSB_NO());
		
		return forward;
	}
}