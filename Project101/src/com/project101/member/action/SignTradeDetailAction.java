package com.project101.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.member.db.Trade;
import com.project101.member.db.TradeDAO;

public class SignTradeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			TradeDAO trdao = new TradeDAO();
			Trade tr = new Trade();
		
				//글번호 파라미터 값을 num변수에 저장합니다.
				int num = Integer.parseInt(request.getParameter("num"));
				System.out.println("NUM : " + num);
					
				//글의 내용을 DAO에서 읽은 후 얻은 결과를 tr 객체에 저장합니다.
				tr = trdao.getDetail(num);
				
				//DAO에서 글의 내용을 읽지 못했을 경우 null을 반환합니다.
				if(tr == null) {
					System.out.println("상세보기 실패");
					return null;
				}		
				System.out.println("상세보기 성공");
				
				//tr 객체를 Request 객체에 저장합니다.
				request.setAttribute("tr", tr);
				ActionForward forward = new ActionForward();
				forward.setRedirect(false);
				
				//글 내용 보기 페이지로 이동하기 위해 경로를 설정합니다.
				forward.setPath("./member/signtradeview.jsp");
				
				return forward;
	}
}