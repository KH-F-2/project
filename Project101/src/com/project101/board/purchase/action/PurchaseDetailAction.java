package com.project101.board.purchase.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.purchase.db.PurchaseBoardBean;
import com.project101.board.purchase.db.PurchaseBoardDAO;
import com.project101.comment.db.CommentBean;
import com.project101.comment.db.CommentDAO;

public class PurchaseDetailAction implements Action{
	
  @Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PurchaseBoardDAO buydao = new PurchaseBoardDAO();
		PurchaseBoardBean buydata = new PurchaseBoardBean();
		
		//글번호 파라미터 값을 num변수에 저장합니다.
		int num = Integer.parseInt(request.getParameter("num"));

		//내용을 확인할 글의 조회수를 증가시킵니다.
		buydao.setReadCountUpdate(num);
		
		//글의 내용을 DAO에서 읽은 후 얻은 결과를 buydata 객체에 저장합니다.
		buydata = buydao.getDetail(num);
		
		//DAO에서 글의 내용을 읽지 못했을 경우 null을 반환합니다.
		if(buydata == null) {
			System.out.println("상세보기 실패");
			return null;
		}else {		
		System.out.println("상세보기 성공");
		}
		
		////////////////// 댓글 보여주기 칸
		
		CommentDAO cmtdao = new CommentDAO();
		
		List<CommentBean> cmtlist = new ArrayList<CommentBean>();
		
		
		int page = 1;
		int limit = 10;
		
		if (request.getParameter("page") != null) {		
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 리플 페이지 = " + page);
		
		int listcount = cmtdao.getListCount(num);
		
		cmtlist = cmtdao.getCmtList(num, page, limit);
		
		int maxpage = (listcount + limit - 1) / limit;
		
		int startpage = ((page - 1) / 10) * 10 + 1;
		int endpage = startpage + 10 - 1;
		if (endpage > maxpage) {
			endpage = maxpage;
		}
		
		request.setAttribute("page", page);// 현재 페이지 수

		request.setAttribute("maxpage", maxpage);// 최대 페이지 수
		// 현재 페이지에 표시할 첫 페이지 수
	
		request.setAttribute("startpage", startpage);
	
		// 현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("endpage", endpage);

		request.setAttribute("listcount", listcount);// 총 글의 수
		
		System.out.println("크기"+cmtlist.size());
		if(cmtlist.size()>0) {
			request.setAttribute("cmtlist", cmtlist);
			}
		
		//////////////////
		
		//buydata 객체를 Request 객체에 저장합니다.
		request.setAttribute("buydata", buydata);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		//글 내용 보기 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("./buy/buy_board_view.jsp");
		
		return forward;
	}

}
