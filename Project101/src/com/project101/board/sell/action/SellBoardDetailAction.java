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
		SellBoardDAO selldao=new SellBoardDAO();
		ActionForward forward=new ActionForward();
		SellBoardBean sellboard=new SellBoardBean();
		List<ImageBean> imagelist=new ArrayList<ImageBean>();
		ImageDAO imagedao=new ImageDAO();
		List<CommentBean> commentlist=new ArrayList<CommentBean>();
		CommentDAO commentdao=new CommentDAO();
		
		int num=Integer.parseInt(request.getParameter("num"));
		
		imagelist=imagedao.getImage(num);
		commentlist=commentdao.getCommentList(num);
		selldao.setReadCountUpdate(num);
		sellboard=selldao.getDetail(num);
		
		request.setAttribute("sellboard", sellboard);
		request.setAttribute("imagelist", imagelist);
		request.setAttribute("commentlist", commentlist);
		
		if(sellboard==null) {
			System.out.println("상세보기 실패!");
			return null;
		}else {
			System.out.println("상세보기 성공!");
			forward.setRedirect(false);
			forward.setPath("sellboard/sell_board_view.jsp");
		}
		
		forward.setRedirect(false);
		forward.setPath("/sellboard/sell_board_view.jsp");
		return forward;
	}

}
