package com.project101.action.board.sell;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.project101.board.sell.db.ImageBean;
import com.project101.board.sell.db.ImageDAO;
import com.project101.board.sell.db.SellBoardBean;
import com.project101.board.sell.db.SellBoardDAO;

public class SellBoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		SellBoardDAO sellDAO = new SellBoardDAO();
		JSONObject boardBean = new JSONObject();
		List<ImageBean> imageBeanList = new ArrayList<ImageBean>();
		ImageDAO imageDAO = new ImageDAO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		String tableName = "SELL_BOARD";

		boardBean = sellDAO.getDetail(num);
		imageBeanList = imageDAO.getImage(num, tableName);
		
		for (ImageBean image : imageBeanList) {
			String url = image.getIMAGE_URL();
			url = url.replace("500x", "x100");
			image.setIMAGE_URL(url);
		}

		if (boardBean == null) {
			System.out.println("수정 페이지 이동 실패");
			return null;
		}
		System.out.println("수정 페이지 이동 완료");

		request.setAttribute("boardBean", boardBean);
		request.setAttribute("imageBeanList", imageBeanList);

		forward.setPath("template.jsp?page=/sellboard/sbmodify.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
