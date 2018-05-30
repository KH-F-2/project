package com.project101.board.epilogue.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.epilogue.db.EpilogueBoardBean;
import com.project101.board.epilogue.db.EpilogueBoardDAO;

public class EpilogueBoardListAction implements Action{
	
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
        
        // 현재 페이지 번호 만들기
        int page = 1;
        if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 : " + page);
        // 검색조건과 검색내용을 가져온다.
        String opt = request.getParameter("opt");
        String condition = request.getParameter("condition");
        int start = page*10-9;
        // 검색조건과 내용을 Map에 담는다.
        HashMap<String, Object> listOpt = new HashMap<String, Object>();
        listOpt.put("opt", opt); //검색조건
        listOpt.put("condition", condition); //내용
        listOpt.put("start", start);
        
        EpilogueBoardDAO ebDAO = EpilogueBoardDAO.getInstance();
        int listCount = ebDAO.getBoardListCount(listOpt);
        System.out.println("listcount : " + listCount);
        ArrayList<EpilogueBoardBean> list =  ebDAO.getBoardList(listOpt);
        
        // 한 화면에 10개의 게시글을 보여지게함
        // 페이지 번호는 총 5개, 이후로는 [다음]으로 표시
        
        // 전체 페이지 수
        int maxPage = (int)(listCount/10.0 + 0.9);
        //시작 페이지 번호
        int startPage = (int)(page/5.0 + 0.8) * 5 - 4;
        //마지막 페이지 번호
        int endPage = startPage + 4;
        if(endPage > maxPage)    endPage = maxPage;
        
        // 4개 페이지번호 저장
        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        
        // 글의 총 수와 글목록 저장
        //request.setAttribute("listCount", listCount);
        request.setAttribute("list", list);
        System.out.println("list : " + list);
        
        // 단순 조회이므로 forward()사용 (= DB의 상태변화 없으므로) 
        forward.setRedirect(false);
        forward.setPath("template.jsp?page=/epilogueboard/ebmain.jsp");
        
        return forward;
	}
}
