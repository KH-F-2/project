package com.project101.action.board.sell;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.CommentBean;
import com.project101.bean.ImageBean;
import com.project101.bean.SellBoardPageBean;
import com.project101.dao.CommentDAO;
import com.project101.dao.ImageDAO;
import com.project101.dao.SellBoardDAO;

public class SellBoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		JSONObject boardBean = new JSONObject();
		SellBoardDAO sellDAO = new SellBoardDAO();
		ImageDAO imageDAO = new ImageDAO();
		CommentDAO commentDAO = new CommentDAO();
		SellBoardPageBean boardPageBean = new SellBoardPageBean();
		
		
		List<ImageBean> imageBeanList = new ArrayList<ImageBean>();
		ImageBean imageBean = new ImageBean();
		List<CommentBean> commentBeanList = new ArrayList<CommentBean>();
		
		int num = 0;
		if(request.getParameter("ajax") != null) {
			num = Integer.parseInt(request.getParameter("ajax"));
		} else {
			num = Integer.parseInt(request.getParameter("num"));
		}
		int page = boardPageBean.getPage();
		int limit = boardPageBean.getLimit();
		int listcount = commentDAO.getCommentListCount(num);
		String BOARD_NAME = "SELL_BOARD";
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		
		imageBean = imageDAO.getImage(num, BOARD_NAME);
		commentBeanList = commentDAO.getCmtList(num, page, limit, BOARD_NAME);
		
		String[] url = imageBean.getIMAGE_URL().split(" ");
		for (String imageurl : url) {
			ImageBean imageBean2 = new ImageBean();
			imageBean2.setIMAGE_URL(imageurl);
			imageBeanList.add(imageBean2);
		}
		
		int maxpage = (listcount + limit - 1) / limit;
		int startpage = ((page - 1) / limit) * limit + 1;
		int endpage = startpage + limit - 1;
		
		if (endpage > maxpage) {
			endpage = maxpage;
		}
		
		boardPageBean.setCommentBeanList(commentBeanList);
		boardPageBean.setLimit(limit);
		boardPageBean.setPage(page);
		boardPageBean.setListcount(listcount);
		boardPageBean.setMaxpage(maxpage);
		boardPageBean.setStartpage(startpage);
		boardPageBean.setEndpage(endpage);
		
		sellDAO.setReadCountUpdate(num);
		boardBean = sellDAO.getDetail(num);
		System.out.println("in detail "+boardBean.get("SB_TITLE")+" "+request.getParameter("page")
				+" page : "+boardPageBean.getPage()+" ajax : "+request.getParameter("ajax"));
		
		request.setAttribute("boardBean", boardBean);
		request.setAttribute("imageBeanList", imageBeanList);
		request.setAttribute("boardPageBean", boardPageBean);
		
		
		forward.setRedirect(false);
		if (request.getParameter("ajax") != null) {
			forward.setPath("sellboard/sbcommentlist.jsp");
		}
		else {
			forward.setPath("template.jsp?page=/sellboard/sbview.jsp");
		}
		return forward;
	}

}
