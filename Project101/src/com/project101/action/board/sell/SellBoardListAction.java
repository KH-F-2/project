package com.project101.action.board.sell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.ImageBean;
import com.project101.bean.SellBoardBean;
import com.project101.bean.SellBoardPageBean;
import com.project101.dao.ImageDAO;
import com.project101.dao.SellBoardDAO;

public class SellBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		ActionForward forward = new ActionForward();

		SellBoardPageBean boardPageBean = new SellBoardPageBean();
		SellBoardDAO sellDAO = new SellBoardDAO();
		JSONArray arr = new JSONArray();

		int page = boardPageBean.getPage();
		int limit = boardPageBean.getLimit();
		int listcount = 0;
		
		double centerLat = Double.parseDouble(request.getParameter("centerLat"));
		double centerLng = Double.parseDouble(request.getParameter("centerLng"));

		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 : " + page);

		
		
		arr = sellDAO.getBoardList(page, limit);
		
		int maxpage = (listcount + limit - 1) / limit;
		int startpage = ((page - 1) / limit) * limit + 1;
		int endpage = startpage + limit - 1;
		
		if (endpage > maxpage) {
			endpage = maxpage;
		}


		request.setAttribute("centerLat", centerLat);
		request.setAttribute("centerLng", centerLng);
		request.setAttribute("arr", arr);
		
		forward.setRedirect(false);
		forward.setPath("template.jsp?page=sellboard/sblist2.jsp");

		return forward;
	}

}
