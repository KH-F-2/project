package com.project101.action.board.sell;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.ImageBean;
import com.project101.bean.SellBoardBean;
import com.project101.bean.SellBoardPageBean;
import com.project101.dao.SellBoardDAO;

public class SellBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		ActionForward forward = new ActionForward();

		SellBoardDAO sellDAO = new SellBoardDAO();
		SellBoardPageBean boardPageBean = new SellBoardPageBean();
		List<SellBoardBean> boardBeanlist = new ArrayList<SellBoardBean>();
		List<ImageBean> imageBeanList = new ArrayList<ImageBean>();

		/*String searchWord = boardPageBean.getSearchWord();
		String searchItem = boardPageBean.getSearchItem();*/
		int page = boardPageBean.getPage();
		int limit = boardPageBean.getLimit();
		int listcount = sellDAO.getListCount();
		System.out.println("listcount : " + listcount);

		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 : " + page);

		/*if (request.getParameter("word") != null) {
			searchWord = request.getParameter("word");
			searchItem = request.getParameter("item");
		}

		if (!searchWord.equals("")) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			map = sellDAO.getSearchList(page, limit, searchWord, searchItem);
			listcount = (int) map.get("listcount");
			
			boardBeanlist = (List<SellBoardBean>) map.get("boardBeanList");
			boardPageBean.setSearchItem(searchItem);
			boardPageBean.setSearchWord(searchWord);
		} else {
			listcount = sellDAO.getListCount();
			boardBeanlist = sellDAO.getBoardList(page, limit);
		}*/
		boardBeanlist = sellDAO.getBoardList(page, limit);
		/*imageBeanList*/
		
		int maxpage = (listcount + limit - 1) / limit;
		int startpage = ((page - 1) / limit) * limit + 1;
		int endpage = startpage + limit - 1;
		
		if (endpage > maxpage) {
			endpage = maxpage;
		}

		boardPageBean.setBoardBeanList(boardBeanlist);
		boardPageBean.setLimit(limit);
		boardPageBean.setPage(page);
		boardPageBean.setListcount(listcount);
		boardPageBean.setMaxpage(maxpage);
		boardPageBean.setStartpage(startpage);
		boardPageBean.setEndpage(endpage);
		
		request.setAttribute("boardPageBean", boardPageBean);
		
		forward.setRedirect(false);
		forward.setPath("template.jsp?page=sellboard/sblist.jsp");

		return forward;
	}

}
