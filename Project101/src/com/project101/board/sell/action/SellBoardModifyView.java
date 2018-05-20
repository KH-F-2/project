package com.project101.board.sell.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SellBoardModifyView implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		//글 내용 불러오기 실패한 경우
		if(selldata == null) {
			
			System.out.println("수정 상세보기 실패했습니다.");
			return null;
		} else if {
			
			System.out.println("수정 상세보기 성공했습니다.");
		}
		
		//수정 폼 페이지로 이동할 때 글 내용을 부여주기 때문에 boarddata 객체를 request 객체에 저장합니다.
		request.setAttribute("selldata", selldata);
		forward.setRedirect(false);
		
		//글 수정 폼 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("/");
		
		return null;
	}

}
