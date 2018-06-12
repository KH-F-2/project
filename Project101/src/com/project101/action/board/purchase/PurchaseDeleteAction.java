package com.project101.action.board.purchase;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.dao.ImageDAO;
import com.project101.dao.PurchaseBoardDAO;

public class PurchaseDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//글번호와 작성자를 가져온다.
		int num = Integer.parseInt(request.getParameter("PB_NO"));
		//로그인 연동 이후 수정
		//String id = request.getParameter("PB_WRITER");
		System.out.println("delete : " + num);
		int result;
		
		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
		ImageDAO imageDAO = new ImageDAO();
		int board_no = num;
		String tableName = "PURCHASE_BOARD";
			
		result = purchaseDAO.purchaseDelete(num);
		result = imageDAO.imageDelete(board_no, tableName);
		System.out.println(result);
		if(result == 1) {
			
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
