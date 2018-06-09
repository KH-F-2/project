package com.project101.action.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.SellBoardBean;
import com.project101.dao.SellBoardDAO;

public class SellerpageWriterAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		SellBoardDAO sellerdao = new SellBoardDAO();
		HttpSession session=request.getSession();
		String id = request.getParameter("writer");
		request.setAttribute("writer", id);
		ArrayList<SellBoardBean> getimage = new ArrayList<SellBoardBean>();
		getimage = sellerdao.getimage(id);
		request.setAttribute("getimage", getimage);
		forward.setRedirect(false);
		
		
		forward.setPath("sellboard/sellerpagelist.jsp");
	   	return forward;
	}

}
