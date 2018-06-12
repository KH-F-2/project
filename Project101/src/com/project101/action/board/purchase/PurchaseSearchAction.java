package com.project101.action.board.purchase;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.project101.action.Action;
import com.project101.action.ActionForward;

import com.project101.dao.PurchaseBoardDAO;

public class PurchaseSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JSONArray categoryList = new JSONArray();
		int category = Integer.parseInt(request.getParameter("category"));
		double centerLat = Double.parseDouble(request.getParameter("centerLat"));
		double centerLng = Double.parseDouble(request.getParameter("centerLng"));
		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
	
		
		
		int page = 1;
		categoryList = purchaseDAO.getSearchCategory(page, category);
		
		
		request.setAttribute("categoryList", categoryList);
		
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		forward.setPath("template.jsp?page=./sellboard/sblist2.jsp");
		return forward;
		
	}

}
