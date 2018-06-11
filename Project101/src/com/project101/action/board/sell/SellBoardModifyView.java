package com.project101.action.board.sell;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.ImageBean;
import com.project101.dao.ImageDAO;
import com.project101.dao.SellBoardDAO;

public class SellBoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		
		SellBoardDAO sellDAO = new SellBoardDAO();
		JSONObject boardBean = new JSONObject();
		List<ImageBean> imageBeanList = new ArrayList<ImageBean>();
		ImageDAO imageDAO = new ImageDAO();
		ImageBean imageBean = new ImageBean();
		
		int num = Integer.parseInt(request.getParameter("num"));
		String tableName = "SELL_BOARD";

		boardBean = sellDAO.getDetail(num);
		
		imageBean = imageDAO.getImage(num, tableName);
		
		String[] url = imageBean.getIMAGE_URL().split(" ");
		for (String imageurl : url) {
			ImageBean imageBean2 = new ImageBean();
			imageBean2.setIMAGE_URL(imageurl);
			imageBeanList.add(imageBean2);
		}
		
		for (ImageBean image : imageBeanList) {
			String url2 = image.getIMAGE_URL();
			url2 = url2.replace("500x", "x100");
			image.setIMAGE_URL(url2);
		}

		if (boardBean == null) {
			System.out.println("수정 페이지 이동 실패");
			return null;
		}
		System.out.println("수정 페이지 이동 완료");

		request.setAttribute("boardBean", boardBean);
		request.setAttribute("imageBeanList", imageBeanList);
		request.setAttribute("imageBean", imageBean);
		System.out.println(imageBean.getIMAGE_URL());

		forward.setPath("template.jsp?page=/sellboard/boardmodify.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
