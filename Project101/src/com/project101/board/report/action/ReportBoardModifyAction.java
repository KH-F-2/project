package com.project101.board.report.action;

import java.io.PrintWriter;

import javax.servlet.http.*;

import com.project101.board.report.db.*;

public class ReportBoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ActionForward forward = new ActionForward();
		
		
		int num = Integer.parseInt(request.getParameter("RB_NO").trim());
		
		ReportBoardDAO reportdao = new ReportBoardDAO();
		ReportBoardBean reportboard = new ReportBoardBean();
		HttpSession session = request.getSession();
		
		reportboard.setRB_NO(num);
		reportboard.setRB_TITLE(request.getParameter("RB_TITLE"));
	    reportboard.setRB_CONTENT(request.getParameter("RB_CONTENT"));
	    reportboard.setRB_PRICE(Integer.parseInt(request.getParameter("RB_PRICE").toString()));
		
	    System.out.println("rb_content==="+reportboard.getRB_CONTENT());
	    int result = reportdao.boardModify(reportboard);
	    PrintWriter out = response.getWriter();
	    if(result == 1) {
	    	
	    	out.println("<script> alert('게시판 수정 성공!');");
	    	out.println("location.href='./BoardDetail.report?RB_NO="+num+"';");
	    	out.println("</script>");
	    }else {
	    	
	    	out.println("<script> alert('게시판 수정 실패!'); history.back();</script>");
	    }
	    out.close();
	    
		return null;
	}

}
