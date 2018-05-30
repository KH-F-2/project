package com.project101.board.purchase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.purchase.db.PurchaseBoardBean;
import com.project101.board.purchase.db.PurchaseBoardDAO;

public class PurchaseModifyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		//글번호와 작성자를 가져온다.
		int num = Integer.parseInt(request.getParameter("PB_NO"));
		
		//로그인 연동 이후 수정
		//String id = request.getParameter("PB_WRITER");
		boolean result = false;
		
		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
		PurchaseBoardBean boardBean = new PurchaseBoardBean();
		
		//글쓴이 인지 확인하기 위해 저장된 번호와 작성자를 확인합니다.
		//로그인 연동 이후 수정
		//boolean idcheck = buydao.isBuyWriter(num, id);
		//수정 내용을 설정합니다.
		
		boardBean.setNum(num);
		boardBean.setContent(request.getParameter("PB_CONTENT"));
		boardBean.setTitle(request.getParameter("PB_TITLE"));
		
		//DAO에서 수정 메서드 호출하여 수정합니다.
		result = purchaseDAO.purchaseModify(boardBean);

		//수정에 실패한 경우
		if(result == false) {
			
			System.out.println("게시판 수정 실패");
			return null;
		} else {
			//수정 성공의 경우
			System.out.println("게시판 수정 완료");
		}
		
		forward.setRedirect(true);
		//수정한 글 내용을 보여주기 위해 글 내용 보기 페이지로 이동하기 위해 경로를 설정함		
		forward.setPath("./pbview.pb?num=" + boardBean.getNum()); //경로설정 변경 해야됨.
    
		return forward;
	}
}
