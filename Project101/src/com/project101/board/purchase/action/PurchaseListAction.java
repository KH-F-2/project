package com.project101.board.purchase.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.purchase.db.PurchaseBoardBean;
import com.project101.board.purchase.db.PurchaseBoardDAO;

public class PurchaseListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
		List<PurchaseBoardBean> boardBeanList = new ArrayList<PurchaseBoardBean>();

		int page = 1;
		int limit = 10;
		String keyword = "";
		String searchOption = "";
		String option = request.getParameter("searchOption");  //선택 옵션 가져오기
		System.out.println("action opt=" + option);		//선택 옵션 출력해보기				
		String condition = request.getParameter("keyword");	//선택 텍스트 가져오기
		System.out.println("action con=" + condition);		//선택 텍스트 출력해솝기

		if (request.getParameter("page") != null) {		
			
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);

		/////////////////  선언
		if (condition != null) {
			keyword = request.getParameter("keyword");
		}
		System.out.println("검색창에 = " + keyword);

		if (option != null) {
			searchOption =  request.getParameter("searchOption");
		}
			
		HashMap<String, Object> listOption = new HashMap<String, Object>();
		listOption.put("option", option);
		listOption.put("condition", condition);
		listOption.put("start", page * 10 - 9);

		// 총 리스트 수를 받아옵니다.
		int listCount = purchaseDAO.getListCount();
		//  검색 결과값의 수를 구하는 메소드
		int searchResultCount = purchaseDAO.getSearchResultCount(listOption, keyword, searchOption);  
		//결과값의 페이지 수
		int searchResultPage = (searchResultCount + limit - 1) / limit;
		
		// 리스트를 받아옵니다.
		boardBeanList = purchaseDAO.getBoardBeandList(listOption, page, limit, keyword, searchOption);
		
		// 총 페이지 수
		// db에 저장된 총 리스트의 수가 0이면 총 페이지수 0페이지
		// 총 리스트의 수가 (1~10)이면 1페이지 (11~20) 이면 2페이지 (21~30)이면 3페이지
		int maxPage = (listCount + limit - 1) / limit;
		System.out.println("총 페이지수 =" + maxPage);

		/*
		 * startpage : 현재 페이지 그룹에서 맨 처음에 표시될 페이지 수를 의미합니다. ([1], [11], [21] 등...) 보여줄
		 * 페이지가 30개일 경우[1][2][3]...[30]까지 다 표시하기에는 너무 많기 때문에 보통 한 페이지에는 10페이지 정도까지 이동할 수
		 * 있게 표시합니다. 예) 페이지 그룹이 아래와 같은 경우 [1][2][3][4][5][6][7][8][9][10] 페이지 그룹의 시작
		 * 페이지는 startpage에 마지막 페이지는 engpage에 구합니다.
		 * 
		 * 예로 1~10페이지의 내용을 나타낼때는 페이지 그룹은 [1][2][3]..[10]로 표시되고 11~20페이지의 내용을 나타낼때는 페이지
		 * 그룹은 [11][12][13]..[20]까지 표시됩니다.
		 */
		int startPage = ((page - 1) / 10) * 10 + 1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 =" + startPage);
		
		// endpage : 현재 페이지 그룹에서 보여줄 마지막 페이지 수 ([10],[20],[30],...)
		int endPage = startPage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 =" + endPage);

		/*
		 * 마지막 그룹의 마지막 페이지 값은 최대 페이지 값입니다. 예로 마지막 페이지 그룹이 [21]~[30]인 경우
		 * 시작페이지(startpage=21)와 마지막페이지(endpage=30) 이지만 최대 페이지(maxpage)가 25라면
		 * [21]~[25]까지만 표시되도록 합니다.
		 */
		if (endPage > maxPage) {
			
			endPage = maxPage;
		}
		
		request.setAttribute("page", page);// 현재 페이지 수
		request.setAttribute("maxPage", maxPage);// 최대 페이지 수
		// 현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("startPage", startPage);
		// 현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("endPage", endPage);
		request.setAttribute("listCount", listCount);// 총 글의 수
		// 해당 페이지의 글 목록을 갖고 있는 리스트
		request.setAttribute("boardBeanList", boardBeanList);
		/////////////////// 위에서 선언한 값 가져가기
		request.setAttribute("keyword", keyword);
		request.setAttribute("searchOption", searchOption);
		request.setAttribute("searchResultCount", searchResultCount);
		request.setAttribute("searchResultPage", searchResultPage);
		System.out.println("searchResultCount="+searchResultCount);
		System.out.println("searchResultPage="+searchResultPage);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		// 글 목록 페이지로 이동하기 위해 경로를 설정합니다.
		forward.setPath("template.jsp?page=./purchaseboard/pblist.jsp"); // 수정 해야됨
		
		return forward;// BoardForntController.java로 리턴됩니다.
	}
}
