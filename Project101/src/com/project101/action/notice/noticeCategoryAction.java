package com.project101.action.notice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.NoticeBean;
import com.project101.dao.NoticeDao;





public class noticeCategoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
        
		NoticeDao noticedao = new NoticeDao();
		List<NoticeBean> list = new ArrayList<NoticeBean>();
		NoticeBean noticebean = new NoticeBean();
		ActionForward forward = new ActionForward();
		
		String noticeCategory = request.getParameter("notice_category");
		System.out.println("noticeCategory=" + noticeCategory);
		
		list=noticedao.getBoardList(Integer.parseInt(noticeCategory));
	
        request.setAttribute("noticeCategory", list);
        
		forward.setRedirect(false);
		forward.setPath("/noticeboard/notice_view.jsp");
		return forward;
	}

}
