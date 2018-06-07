package com.project101.action.board.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.PurchaseBoardBean;
import com.project101.dao.PurchaseBoardDAO;

public class PurchaseModifyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		//글번호와 작성자를 가져온다.
		int num = Integer.parseInt(request.getParameter("PB_NO"));
		int categoryNO = Integer.parseInt(request.getParameter("PB_CATEGORY"));
	
		//로그인 연동 이후 수정
		//String id = request.getParameter("PB_WRITER");
		boolean result = false;
		
		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
		PurchaseBoardBean boardBean = new PurchaseBoardBean();
		
		//글쓴이 인지 확인하기 위해 저장된 번호와 작성자를 확인합니다.
		//로그인 연동 이후 수정
		//수정 내용을 설정합니다.
		
		boardBean.setPB_NO(num);
		boardBean.setPB_CONTENT(request.getParameter("PB_CONTENT"));
		boardBean.setPB_TITLE(request.getParameter("PB_TITLE"));
		boardBean.setPB_HASHTAG(request.getParameter("PB_HASHTAG"));
		boardBean.setPB_CATEGORY(categoryNO);

		

		
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
		forward.setPath("./pbview.pb?CMT_SUBJECT_NO=" + boardBean.getPB_NO()); //경로설정 변경 해야됨.
    
		return forward;
	}
}
