package com.project101.board.sell.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.sell.db.ImageBean;
import com.project101.board.sell.db.ImageDAO;
import com.project101.board.sell.db.SellBoardBean;
import com.project101.board.sell.db.SellBoardDAO;

public class SellBoardModifyView implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int num=Integer.parseInt(request.getParameter("num"));
		SellBoardDAO selldao=new SellBoardDAO();
		SellBoardBean sellboard=new SellBoardBean();
		List<ImageBean> imagelist=new ArrayList<ImageBean>();
		ImageDAO imagedao=new ImageDAO();
		
		sellboard=selldao.getDetail(num);
		imagelist=imagedao.getImage(num);
		for(ImageBean image : imagelist) {
			String url=image.getIMAGE_URL();
			url=url.replace("500x", "x100");
			image.setIMAGE_URL(url);
		}
		
		if(sellboard==null) {
			System.out.println("수정 페이지 이동 실패");
			return null;
		}
		System.out.println("수정 페이지 이동 완료");
		
		System.out.println("imagelist size : "+imagelist.size());
		request.setAttribute("sellboard", sellboard);
		request.setAttribute("imagelist", imagelist);
		
		forward.setPath("/sellboard/sell_board_modify.jsp");
		forward.setRedirect(false);
		
	    return forward;
	}

}
