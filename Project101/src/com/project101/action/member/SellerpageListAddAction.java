package com.project101.action.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.SellBoardBean;
import com.project101.bean.SellBoardPageBean;
<<<<<<< HEAD:Project101/src/com/project101/action/member/listAddAction2.java
import com.project101.dao.MemberDAO;
import com.project101.dao.SellBoardDAO;
=======
>>>>>>> origin/yeunju:Project101/src/com/project101/action/member/SellerpageListAddAction.java

import com.project101.dao.SellBoardDAO;



public class SellerpageListAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
        
		SellBoardDAO sellerdao = new SellBoardDAO();
		
		SellBoardPageBean boardpage=new SellBoardPageBean();
		ActionForward forward = new ActionForward();
		
		String writer = request.getParameter("writer");
		System.out.println("id=" + writer);
		int listcount = sellerdao.getListCount(writer);
		List<SellBoardBean> list = new ArrayList<SellBoardBean>();
		
		 // 한 화면에 10개의 게시글을 보여지게함
        // 페이지 번호는 총 5개, 이후로는 [다음]으로 표시
        
		int page = boardpage.getPage();
		int limit = boardpage.getLimit();
		
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 : "+page);
		
        // 전체 페이지 수
        int maxpage = (listcount + limit -1)/limit;
        int startpage = ((page-1)/limit)*limit+1;
        int endpage = startpage + limit -1;
        //마지막 페이지 번호
        int endPage = startpage + 4;
        if(endpage > maxpage)   {
        	endpage = maxpage;
        }
        
        // 4개 페이지번호 저장
        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxpage);
        request.setAttribute("startPage", startpage);
        request.setAttribute("endPage", endPage);
        System.out.println("maxpage : " +maxpage+"a"+startpage+"d"+endPage);
        
        // 글의 총 수와 글목록 저장
        //request.setAttribute("listCount", listCount);
        request.setAttribute("list", list);
        System.out.println("list : " + list);
        list = sellerdao.getBoardList(page,limit,writer);
        request.setAttribute("list", list);
        
        
        boardpage.setLimit(limit);
		boardpage.setPage(page);
		boardpage.setListcount(listcount);
		System.out.println(listcount);
		boardpage.setMaxpage(maxpage);
		boardpage.setStartpage(startpage);
		boardpage.setEndpage(endpage);
		request.setAttribute("boardpage", boardpage);
		request.setAttribute("writer", writer);
		
		//dao에서 IMAGE_URL을 구해옵니다.
	    //String getimageurl = dao.geturl();
		ArrayList<SellBoardBean> getimage = new ArrayList<SellBoardBean>();
		javax.servlet.http.HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		getimage = sellerdao.getimage(writer);
		request.setAttribute("getimage", getimage);
		forward.setRedirect(false);
		forward.setPath("template.jsp?page=/sellboard/sellerpage_main.jsp");
		return forward;
	}

}
