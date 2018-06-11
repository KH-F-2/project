package com.project101.action.map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.project101.action.Action;
import com.project101.action.ActionForward;

public class GetBoardListUsingCurrentPositionProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();

		JSONArray jsonArr = new JSONArray();
		
		try {
			JSONParser jsonParser = new JSONParser();
			jsonArr = (JSONArray) jsonParser.parse(request.getParameter("json"));
			System.out.println("여기까지왔다 : " + jsonArr);

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("jsonArr", jsonArr);
		
		forward.setRedirect(false);
		forward.setPath("maincontainer.jsp");

		return forward;
	}
}
