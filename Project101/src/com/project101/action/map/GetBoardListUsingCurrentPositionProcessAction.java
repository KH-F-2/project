package com.project101.action.map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.dao.PurchaseBoardDAO;
import com.project101.dao.SellBoardDAO;

public class GetBoardListUsingCurrentPositionProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		SellBoardDAO sellDAO = new SellBoardDAO();
		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
		JSONArray jsonArr = new JSONArray();
		
		try {
			JSONParser jsonParser = new JSONParser();
			System.out.println("여기요 : " + request.getParameter("json"));
			jsonArr = (JSONArray) jsonParser.parse(request.getParameter("json"));
			
			for (int i = 0; i < jsonArr.size(); i++) {
				System.out.print("jsonArr 조회 결과 출력 : ");
				System.out.println(jsonArr.get(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		int totalContents = sellDAO.getNextBoardNo();
		totalContents += purchaseDAO.getMaxCount();
		
		request.setAttribute("totalContents", totalContents);		
		request.setAttribute("jsonArr", jsonArr);
		
		forward.setRedirect(false);
		
		if (request.getParameter("state") != null) {
			forward.setPath("sellboard/ajaxcontainer.jsp");
		} else {
			forward.setPath("maincontainer.jsp");
		}
		
		return forward;
	}
}
