package com.project101.action.member;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.Member;
import com.project101.dao.MemberDAO;
import com.project101.dao.SellBoardDAO;

public class MypageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		int page = 1; 
				
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		MemberDAO memberDAO = new MemberDAO();
		Member member = memberDAO.member_info(id);
		
		SellBoardDAO sellDAO = new SellBoardDAO();
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap = sellDAO.getMyList(id, page);
		
		JSONArray jsonArr = (JSONArray) resultMap.get("jsonArr");
		int listCount = (int) resultMap.get("listCount");
		
		int maxPage = (listCount + 10 - 1) / 10;
		int startPage = ((page - 1) / 10) * 10 + 1;
		int endPage = startPage +10 - 1;
		
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		request.setAttribute("member", member);

		request.setAttribute("jsonArr", jsonArr);
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("listCount", listCount);
		
		forward.setRedirect(false);

		if (request.getParameter("state") != null) {
			forward.setPath("/member/ajaxcontainer.jsp");
			System.out.println("ajax 실행");
		} else {
			forward.setPath("template.jsp?page=/member/mypage.jsp");
			System.out.println("ajax 미실행");
		}
		
		return forward;
	}
}
