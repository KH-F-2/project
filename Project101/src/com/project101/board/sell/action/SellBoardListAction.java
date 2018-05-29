package com.project101.board.sell.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.sell.db.SellBoardBean;
import com.project101.board.sell.db.SellBoardDAO;
import com.project101.board.sell.db.SellBoardPageBean;

public class SellBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		SellBoardDAO selldao=new SellBoardDAO();
		SellBoardPageBean boardpage=new SellBoardPageBean();
		ActionForward forward=new ActionForward();
		String search_word=boardpage.getSearchWord();
		String search_item=boardpage.getSearchItem();
		
		int page=boardpage.getPage();
		int limit=boardpage.getLimit();
		int listcount=0;
		
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 : "+page);
		
		if(request.getParameter("word")!=null) {
			search_word=request.getParameter("word");
			search_item=request.getParameter("item");
		}
		
		if(!search_word.equals("")) {
			Map<String, Object> map=new HashMap<String, Object>();
			map=selldao.getSearchList(page, limit, search_word, search_item);
			listcount=(int) map.get("listcount");
			boardpage.setboardList((List<SellBoardBean>) map.get("boardlist"));
			boardpage.setSearchItem(search_item);
			boardpage.setSearchWord(search_word);
		}else {
			listcount=selldao.getListCount();
			boardpage.setboardList(selldao.getBoardList(page, limit));
		}
		System.out.println("listcount : "+listcount);
		
		
		int maxpage=(listcount+limit-1)/limit;
		int startpage=((page-1)/limit)*limit+1;
		int endpage=startpage+limit-1;
		if(endpage>maxpage) endpage=maxpage;
		
		
		boardpage.setLimit(limit);
		boardpage.setPage(page);
		boardpage.setListcount(listcount);
		boardpage.setMaxpage(maxpage);
		boardpage.setStartpage(startpage);
		boardpage.setEndpage(endpage);
		request.setAttribute("boardpage", boardpage);
		forward.setRedirect(false);
		forward.setPath("sellboard/sell_board_list.jsp");
		//forward.setPath("./member/template.jsp?page=/sellboard/sell_board_list");
		
		return forward;
	}

}
