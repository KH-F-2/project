package com.project101.action.board.sell;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.CommentBean2;
import com.project101.bean.ImageBean;
import com.project101.bean.SellBoardBean;
import com.project101.dao.CommentDAO2;
import com.project101.dao.ImageDAO;
import com.project101.dao.SellBoardDAO;

public class SellBoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=euc-kr");
		request.setCharacterEncoding("euc-kr");
		
		ActionForward forward = new ActionForward();
		SellBoardBean boardBean = new SellBoardBean();
		SellBoardDAO sellDAO = new SellBoardDAO();
		ImageDAO imageDAO = new ImageDAO();
		CommentDAO2 commentDAO = new CommentDAO2();
		
		List<ImageBean> imageBeanList = new ArrayList<ImageBean>();
		List<CommentBean2> commentBeanList = new ArrayList<CommentBean2>();
		
		int num = Integer.parseInt(request.getParameter("num"));
		String tableName = "SELL_BOARD";
		System.out.println("sbDetailAction : " +num);
		imageBeanList = imageDAO.getImage(num, tableName);
		commentBeanList = commentDAO.getCommentList(num);
		sellDAO.setReadCountUpdate(num);
		boardBean = sellDAO.getDetail(num);
		
		request.setAttribute("boardBean", boardBean);
		request.setAttribute("imageBeanList", imageBeanList);
		request.setAttribute("commentBeanList", commentBeanList);
		
		if(boardBean == null) {
			System.out.println("상세보기 실패!");
			return null;
		}
		else {
			System.out.println("상세보기 성공!");
			forward.setRedirect(false);
			forward.setPath("template.jsp?page=sellboard/sbview.jsp");
		}
		
		forward.setRedirect(false);
		forward.setPath("template.jsp?page=/sellboard/sbview.jsp");
		return forward;
	}

}
