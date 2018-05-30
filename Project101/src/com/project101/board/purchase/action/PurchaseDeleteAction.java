package com.project101.board.purchase.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.purchase.db.PurchaseBoardDAO;

public class PurchaseDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//글번호와 작성자를 가져온다.
		int num = Integer.parseInt(request.getParameter("num"));
		//로그인 연동 이후 수정
		//String id = request.getParameter("PB_WRITER");
		boolean result = false;
		
		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
			
		result = purchaseDAO.purchaseDelete(num);
		
		if(result == false) {
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
		
			out.println("<script>");
			out.println("alert('삭제 되었습니다')");
			out.println("location.href='./pbmain.pb';");
			out.println("</script>");	
			out.close();
				
			System.out.println("삭제 성공");
		} else {
			
			System.out.println("삭제 실패");
		}
		
		return null;
	}

}
