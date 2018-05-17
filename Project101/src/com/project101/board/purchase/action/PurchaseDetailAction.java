package com.project101.board.purchase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.purchase.db.PurchaseBoardBean;
import com.project101.board.purchase.db.PurchaseBoardDAO;

public class PurchaseDetailAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PurchaseBoardDAO buydao = new PurchaseBoardDAO();
		PurchaseBoardBean buydata = new PurchaseBoardBean();
		
		//글번호 파라미터 값을 num변수에 저장합니다.
		int num = Integer.parseInt(request.getParameter("PB_NO"));
			
		//내용을 확인할 글의 조회수를 증가시킵니다.
		buydao.setReadCountUpdate(num);
		
		//글의 내용을 DAO에서 읽은 후 얻은 결과를 buydata 객체에 저장합니다.
		buydata = buydao.getDetail(num);
		
		//DAO에서 글의 내용을 읽지 못했을 경우 null을 반환합니다.
		if(buydata == null) {
			System.out.println("상세보기 실패");
			return null;
		}		
		System.out.println("상세보기 성공");
		
		//buydata 객체를 Request 객체에 저장합니다.
		request.setAttribute("buydata", buydata);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		//글 내용 보기 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("./board/qna_board_view.jsp");
		
		return forward;
	}
}
