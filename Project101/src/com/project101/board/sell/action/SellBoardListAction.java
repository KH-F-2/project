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
      List<SellBoardBean> boardlist=new ArrayList<SellBoardBean>();
      ActionForward forward=new ActionForward();
      
      int page=1;
      int limit=10;
      
      if(request.getParameter("page")!=null) {
         page=Integer.parseInt(request.getParameter("page"));
      }
      System.out.println("넘어온 페이지 : "+page);
      
      int listcount=selldao.getListCount();
      System.out.println("listcount : "+listcount);
      
      boardlist=selldao.getBoardList(page, limit);
      
      int maxpage=(listcount+limit-1)/limit;
      int startpage=((page-1)/limit)*limit+1;
      int endpage=startpage+limit-1;
      if(endpage>maxpage) endpage=maxpage;
      
      request.setAttribute("boardlist", boardlist);
      request.setAttribute("page", page);
      request.setAttribute("maxpage", maxpage);
      request.setAttribute("startpage", startpage);
      request.setAttribute("endpage", endpage);
      request.setAttribute("listcount", listcount);
      
      //forward.setPath("./board/qna_board_list.jsp");
      forward.setRedirect(false);
      forward.setPath("sellboard/sell_board_list.jsp");
      
      return forward;
   }


}