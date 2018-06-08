package com.project101.action.board.purchase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.ImageBean;
import com.project101.dao.ImageDAO;
import com.project101.dao.PurchaseBoardDAO;

public class PurchaseSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JSONArray categoryList = new JSONArray();
		int category = Integer.parseInt(request.getParameter("category"));
		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
	
		
		int num = 1;
		int page = 1;
		categoryList = purchaseDAO.getSearchCategory(page, category);
		
		
		request.setAttribute("categoryList", categoryList);
		
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		forward.setPath("template.jsp?page=./search/searchpage.jsp");
		return forward;
		
	}

}
