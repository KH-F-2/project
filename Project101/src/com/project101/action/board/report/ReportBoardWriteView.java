package com.project101.action.board.report;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.ImageBean;
import com.project101.dao.ImageDAO;
import com.project101.dao.PurchaseBoardDAO;
import com.project101.dao.SellBoardDAO;

public class ReportBoardWriteView implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		String writer = request.getParameter("writer");
		String board_no = request.getParameter("board_no");
		String board_name = request.getParameter("board_name");
		
		request.setAttribute("writer", writer);
		request.setAttribute("board_no", board_no);
		request.setAttribute("board_name", board_name);

		forward.setPath("template.jsp?page=/reportboard/rb_write.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
