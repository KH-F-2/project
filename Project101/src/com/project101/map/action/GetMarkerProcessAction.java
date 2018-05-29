package com.project101.map.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.project101.map.db.MapDAO;

public class GetMarkerProcessAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();

		double startLat = Double.parseDouble(request.getParameter("startLat"));
		double startLng = Double.parseDouble(request.getParameter("startLng"));
		double endLat = Double.parseDouble(request.getParameter("endLat"));
		double endLng = Double.parseDouble(request.getParameter("endLng"));

		MapDAO mapDAO = new MapDAO();

		JSONArray jsonArr = new JSONArray();
		jsonArr = mapDAO.getMarkers(startLat, startLng, endLat, endLng);
		System.out.println(jsonArr);
       
		
		pw.print(jsonArr);
		return null;

	}

}
