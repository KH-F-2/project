package com.project101.board.report.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.project101.board.report.db.*;


public class ReportBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		ReportBoardDAO reportdao = new ReportBoardDAO();
		List<ReportBoardBean> boardlist = new ArrayList<ReportBoardBean>();
		ActionForward forward = new ActionForward();
		
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page")!=null) {
			
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 : " + page);
		
		int listcount = reportdao.getListCount();
		System.out.println("listcount : " + listcount);
		
		boardlist = reportdao.getBoardList(page, limit);
		
		int maxpage = (listcount + limit -1)/limit;
		int startpage=((page-1)/limit)*limit+1;
	    int endpage=startpage+limit-1;
	    if(endpage>maxpage) {
	    	endpage=maxpage;
	    }
	      
	    request.setAttribute("boardlist", boardlist);
	    request.setAttribute("page", page);
	    request.setAttribute("maxpage", maxpage);
	    request.setAttribute("startpage", startpage);
	    request.setAttribute("endpage", endpage);
	    request.setAttribute("listcount", listcount);
		
	    forward.setRedirect(false);
	    forward.setPath("reportboard/report_board_list.jsp");
	    
		return forward;
	}

}
