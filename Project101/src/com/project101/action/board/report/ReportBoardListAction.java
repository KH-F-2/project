package com.project101.action.board.report;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.ReportBoardBean;
import com.project101.dao.ReportBoardDAO;

public class ReportBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		ReportBoardDAO reportDao = new ReportBoardDAO();
		List<ReportBoardBean> reportList = new ArrayList<ReportBoardBean>();
		ActionForward forward = new ActionForward();

		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {

			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 : " + page);

		int listcount = reportDao.getListCount();
		System.out.println("listcount : " + listcount);

		reportList = reportDao.getBoardList(page, limit);

		int maxpage = (listcount + limit - 1) / limit;
		int startpage = ((page - 1) / limit) * limit + 1;
		int endpage = startpage + limit - 1;
		if (endpage > maxpage) {
			endpage = maxpage;
		}

		request.setAttribute("reportList", reportList);
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);

		forward.setRedirect(false);
		forward.setPath("template.jsp?page=reportboard/rb_main.jsp");

		return forward;
	}

}
