package com.project101.action.map;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.SellBoardPageBean;
import com.project101.dao.SellBoardDAO;

public class ChangeCategoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();

		SellBoardDAO sellDAO = new SellBoardDAO();
		SellBoardPageBean boardPageBean = new SellBoardPageBean();
		JSONArray boardBeanlist = new JSONArray();

		int category = 0;
		String searchWord = boardPageBean.getSearchWord();
		String searchItem = boardPageBean.getSearchItem();

		int page = boardPageBean.getPage();
		int limit = boardPageBean.getLimit();
		int listcount = 0;
		
		double centerLat = Double.parseDouble(request.getParameter("centerLat"));
		double centerLng = Double.parseDouble(request.getParameter("centerLng"));
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 : " + page);

		if (request.getParameter("word") != null) {
			category = Integer.parseInt(request.getParameter("category"));
			searchWord = request.getParameter("word");
			searchItem = request.getParameter("item");
		}
		
		if (!searchWord.equals("")) {
			
			Map<String, Object> map = sellDAO.getSearchList(page, category, searchWord, searchItem, centerLat, centerLng);
			
			listcount = (int) map.get("listcount");
			
			boardBeanlist = (JSONArray) map.get("jsonArr");
			boardPageBean.setSearchItem(searchItem);
			boardPageBean.setSearchWord(searchWord);
			
		} else {
			
			if (category != 0) {
				Map<String, Object> map = sellDAO.getSearchList(page, category, searchWord, searchItem, centerLat, centerLng);
				
				listcount = (int) map.get("listcount");
				
				boardBeanlist = (JSONArray) map.get("jsonArr");
				boardPageBean.setSearchItem(searchItem);
				boardPageBean.setSearchWord(searchWord);
			} else {				
				listcount = sellDAO.getListCount();
				boardBeanlist = sellDAO.getBoardList(page, centerLat, centerLng);
			}
		}
		System.out.println("listcount : " + listcount);

		
		

		
		int maxpage = (listcount + limit - 1) / limit;
		int startpage = ((page - 1) / limit) * limit + 1;
		int endpage = startpage + limit - 1;
		
		if (endpage > maxpage) {
			endpage = maxpage;
		}
		
		
		request.setAttribute("boardBeanlist", boardBeanlist);
		
		request.setAttribute("listcount", listcount);
		request.setAttribute("centerLat", Double.parseDouble(request.getParameter("centerLat")));
		request.setAttribute("centerLng", Double.parseDouble(request.getParameter("centerLng")));


		forward.setRedirect(false);
		if (request.getParameter("state") != null) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter pw = response.getWriter();
			
			pw.print(boardBeanlist);
			
			return null;
		} else {
			
			forward.setPath("template.jsp?page=sellboard/sblist2.jsp");
		}

		return forward;
	}

}
