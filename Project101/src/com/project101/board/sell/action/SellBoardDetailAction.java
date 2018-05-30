package com.project101.board.sell.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.sell.db.CommentBean;
import com.project101.board.sell.db.CommentDAO;
import com.project101.board.sell.db.ImageBean;
import com.project101.board.sell.db.ImageDAO;
import com.project101.board.sell.db.SellBoardBean;
import com.project101.board.sell.db.SellBoardDAO;

public class SellBoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=euc-kr");
		request.setCharacterEncoding("euc-kr");
		
		ActionForward forward = new ActionForward();
		SellBoardBean boardBean = new SellBoardBean();
		SellBoardDAO sellDAO = new SellBoardDAO();
		ImageDAO imageDAO = new ImageDAO();
		CommentDAO commentDAO = new CommentDAO();
		
		List<ImageBean> imageBeanList = new ArrayList<ImageBean>();
		List<CommentBean> commentBeanList = new ArrayList<CommentBean>();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		imageBeanList = imageDAO.getImage(num);
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
