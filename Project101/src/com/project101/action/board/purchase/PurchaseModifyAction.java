package com.project101.action.board.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.ImageBean;
import com.project101.bean.PurchaseBoardBean;
import com.project101.dao.ImageDAO;
import com.project101.dao.PurchaseBoardDAO;

public class PurchaseModifyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward forward = new ActionForward();
		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
		PurchaseBoardBean boardBean = new PurchaseBoardBean();
		request.setCharacterEncoding("utf-8");
		ImageDAO imageDAO = new ImageDAO();
		ImageBean imageBean = new ImageBean();
		String tableName =  "PURCHASE_BOARD";
		
		
		//글번호와 작성자를 가져온다.
		int num = Integer.parseInt(request.getParameter("PB_NO"));

		
		int result = 0;
		
		boardBean.setPB_NO(num);
		boardBean.setPB_TITLE(request.getParameter("TITLE"));
		boardBean.setPB_CONTENT(request.getParameter("CONTENT"));
		boardBean.setPB_PRICE(Integer.parseInt(request.getParameter("PRICE")));
		boardBean.setPB_CATEGORY(Integer.parseInt(request.getParameter("CATEGORY")));
		boardBean.setPB_HASHTAG(request.getParameter("HASHTAG"));
		boardBean.setPB_LAT(Double.parseDouble(request.getParameter("markerLat")));  
		boardBean.setPB_LNG(Double.parseDouble(request.getParameter("markerLng")));
		
		System.out.println("1");
		result = purchaseDAO.purchaseModify(boardBean, num);
		System.out.println("2");
		result = imageDAO.imageModify(imageBean, tableName);
		System.out.println("3");

		//글쓴이 인지 확인하기 위해 저장된 번호와 작성자를 확인합니다.
		//로그인 연동 이후 수정
		//수정 내용을 설정합니다.		
		//DAO에서 수정 메서드 호출하여 수정합니다.
		  imageBean.setBOARD_NO(num);
		  imageBean.setIMAGE_URL(request.getParameter("img_hidden"));
		  System.out.println("gjgjgjgj"+request.getParameter("img_hidden"));
		  imageDAO.imageModify(imageBean, tableName);
		  System.out.println("4");
		
		//수정에 실패한 경우
		if(result == 0) {
			System.out.println("게시판 수정 실패");
			return null;
		} else {
			//수정 성공의 경우
			System.out.println("게시판 수정 완료");
		}
		
		forward.setRedirect(false);
		//수정한 글 내용을 보여주기 위해 글 내용 보기 페이지로 이동하기 위해 경로를 설정함		
		forward.setPath("./pbview.pb?PB_NO=" + boardBean.getPB_NO()); //경로설정 변경 해야됨.
    
		return forward;
	}
}
