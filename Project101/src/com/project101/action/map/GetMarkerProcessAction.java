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

//		double startLat = Double.parseDouble(request.getParameter("startLat"));
//		double startLng = Double.parseDouble(request.getParameter("startLng"));
//		double endLat = Double.parseDouble(request.getParameter("endLat"));
//		double endLng = Double.parseDouble(request.getParameter("endLng"));

		double centerLat = Double.parseDouble(request.getParameter("centerLat"));
		double centerLng = Double.parseDouble(request.getParameter("centerLng"));
		
//		MapDAO mapDAO = new MapDAO();
		SellBoardDAO sellDAO = new SellBoardDAO();
		
//		JSONArray jsonArr = mapDAO.getMarkers(startLat, startLng, endLat, endLng);
		JSONArray jsonArr = sellDAO.getBoardList(1, centerLat, centerLng);
		System.out.println("getmarkerProcessACtion : " + jsonArr);
       
		
		pw.print(jsonArr);
		
		return null;
	}

}
