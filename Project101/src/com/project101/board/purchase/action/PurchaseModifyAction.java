package com.project101.board.purchase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PurchaseModifyAction implements Action  {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//dao 를 통한 수정Action 부분
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		// 글 목록 페이지로 이동하기 위해 경로를 설정합니다.
		//결과 형식 :  forward.setPath("./BoardDetailAction.bo?num=" + boarddata.getBOARD_NUM());
		//연습 형식
		forward.setPath("./buy/buy_board_modify.jsp");
		System.out.println("on modify aaa");
		return forward;
	}
	

}
