package com.project101.action.message;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.MessageBoardBean;
import com.project101.dao.MessageDAO;

public class MessageSendListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		List<MessageBoardBean> msBoardBean = new ArrayList<MessageBoardBean>();
		MessageDAO msDAO = new MessageDAO();
		int page= 1;
		int limit = 10;
		HttpSession session=request.getSession();
		String id = session.getAttribute("id").toString();
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);
		//총 리스트 수를 받아옵니다.
		int listcount = msDAO.getSendListCount(id);
		
		//리스트를 받아옵니다.
		msBoardBean = msDAO.getBoardList(page, limit, id);
		
		ActionForward forward = new ActionForward();
		
		int maxpage = (listcount+limit-1) / limit;
		System.out.println("총 페이지수 =" + maxpage);
		
		int startpage = ((page-1) / 10) * 10 + 1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 =" + startpage);
		
		//endpage : 현재 페이지 그룹에서 보여줄 마지막 페이지 수 ([10],[20],[30],...)		
		int endpage = startpage + 10 -1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 =" + endpage);
		
		if(endpage > maxpage) endpage = maxpage;
		
		request.setAttribute("page", page);//현재 페이지 수
		request.setAttribute("maxpage", maxpage);//최대 페이지 수
		//현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("startpage", startpage);
		
		//현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("endpage", endpage);
		
		request.setAttribute("listcount", listcount);//총 글의 수
		//해당 페이지의 글 목록을 갖고 있는 리스트
		request.setAttribute("msBoardBean", msBoardBean);
		
		forward.setRedirect(false);
		
		forward.setPath("template.jsp?page=./messageboard/msmessagesendlist.jsp");	
		return forward;
	}
}
