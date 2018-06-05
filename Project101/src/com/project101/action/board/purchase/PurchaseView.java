package com.project101.action.board.purchase;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.CommentBean;
import com.project101.bean.PurchaseBoardBean;
import com.project101.dao.CommentDAO;
import com.project101.dao.PurchaseBoardDAO;


public class PurchaseView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
		PurchaseBoardBean boardBean = new PurchaseBoardBean();

		// 글번호 파라미터 값을 num변수에 저장합니다.
		int num = Integer.parseInt(request.getParameter("CMT_SUBJECT_NO"));
	
		// 내용을 확인할 글의 조회수를 증가시킵니다.
		purchaseDAO.setReadCountUpdate(num);
	
		// 글의 내용을 DAO에서 읽은 후 얻은 결과를 buydata 객체에 저장합니다.
		boardBean = purchaseDAO.getDetail(num);
	
		
		// DAO에서 글의 내용을 읽지 못했을 경우 null을 반환합니다.
		if (boardBean == null) {
			System.out.println("상세보기 실패");
			return null;
		} else {
			System.out.println("상세보기 성공");
		}

		////////////////// 댓글 보여주기 칸
		CommentDAO cmtDAO = new CommentDAO();
		List<CommentBean> cmtList = new ArrayList<CommentBean>();
		
	
		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {

			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 리플 페이지 = " + page);

		int listCount = cmtDAO.getListCount(num);
		cmtList = cmtDAO.getCmtList(num, page, limit);

		int maxPage = (listCount + limit - 1) / limit;
		int startPage = ((page - 1) / 10) * 10 + 1;
		int endPage = startPage + 10 - 1;

		if (endPage > maxPage) {

			endPage = maxPage;
		}

		request.setAttribute("page", page);// 현재 페이지 수
		request.setAttribute("maxPage", maxPage);// 최대 페이지 수
		// 현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("startPage", startPage);
		// 현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("endPage", endPage);
		request.setAttribute("listCount", listCount);// 총 글의 수

		System.out.println("크기" + cmtList.size());
		if (cmtList.size() > 0) {

			request.setAttribute("cmtList", cmtList);
		}

		// buydata 객체를 Request 객체에 저장합니다.
		request.setAttribute("boardBean", boardBean);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);

		// 글 내용 보기 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("template.jsp?page=./purchaseboard/pbview.jsp");

		return forward;
	}

}
