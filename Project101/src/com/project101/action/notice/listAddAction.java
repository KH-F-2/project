package com.project101.action.notice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.NoticeBean;
import com.project101.dao.NoticeDao;



public class listAddAction implements Action {

	

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		NoticeDao noticedao = new NoticeDao();
		List<NoticeBean> list = new ArrayList<NoticeBean>();
		int no = Integer.parseInt(request.getParameter("notice_category"));
		list = noticedao.getBoardList(no);
		request.setAttribute("list", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/noticeboard/notice_view.jsp");
		return forward;
	}

}
