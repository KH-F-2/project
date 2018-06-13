package com.project101.action.notice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.NoticeBean;
import com.project101.dao.NoticeDao;

public class NoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		NoticeDao noticeDAO = new NoticeDao();
		List<NoticeBean> list = new ArrayList<NoticeBean>();
		
		int no = Integer.parseInt(request.getParameter("notice_category"));
		
		list = noticeDAO.getBoardList(no);
		
		request.setAttribute("list", list);
		
		forward.setRedirect(false);
		forward.setPath("/noticeboard/notice_view.jsp");

		return forward;
	}

}
