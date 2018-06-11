package com.project101.action.map;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.dao.MapDAO;
import com.project101.dao.SellBoardDAO;

public class GetMarkerProcessAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();

		double centerLat = Double.parseDouble(request.getParameter("centerLat"));
		double centerLng = Double.parseDouble(request.getParameter("centerLng"));
		
		SellBoardDAO sellDAO = new SellBoardDAO();
		
		JSONArray jsonArr = sellDAO.getBoardList(1, centerLat, centerLng);
		System.out.println("getmarkerProcessACtion : " + jsonArr);
		
		pw.print(jsonArr);
		
		return null;
	}

}
