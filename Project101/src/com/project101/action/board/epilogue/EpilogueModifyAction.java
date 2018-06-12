package com.project101.action.board.epilogue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.EpilogueBoardBean;
import com.project101.dao.EpilogueBoardDAO;

public class EpilogueModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		ActionForward forward = new ActionForward();
		System.out.println("ㅡㅡㅡㅡ");
		
		// 공백제거 필수
		int num = Integer.parseInt(request.getParameter("SB_NO").trim());
		System.out.println("num = " + num);
		
		EpilogueBoardDAO ebDAO = new EpilogueBoardDAO();
		EpilogueBoardBean ebBoardBean = new EpilogueBoardBean();

		// 수정내용 설정
		ebBoardBean.setSB_NO(num);
		ebBoardBean.setSB_TITLE(request.getParameter("SB_TITLE"));
		ebBoardBean.setSB_CONTENT(request.getParameter("SB_CONTENT"));

		int result1 = ebDAO.boardModify(ebBoardBean);
		
		if (result1 == 0) {
			System.out.println("게시판 수정 실패");
			return null;
		}
		System.out.println("게시판 수정 완료");

		forward.setRedirect(true);

		forward.setPath("./ebview.eb?num=" + ebBoardBean.getSB_NO());

		return forward;
	}
}