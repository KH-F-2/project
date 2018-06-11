package com.project101.action.board.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.ImageBean;
import com.project101.bean.PurchaseBoardBean;
import com.project101.dao.ImageDAO;
import com.project101.dao.PurchaseBoardDAO;

public class PurchaseWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
		PurchaseBoardBean purchaseBoardBean = new PurchaseBoardBean();
		ImageDAO imageDAO = new ImageDAO();
		ImageBean imageBean = new ImageBean();
		ActionForward forward = new ActionForward();

		int result;

		try {
			request.setCharacterEncoding("UTF-8");
		/*	String id = request.getParameter("PB_WRITER");
			String title = request.getParameter("PB_TITLE");
			String content = request.getParameter("PB_CONTENT");
			//정해지면 봉인 헤제
			//Double lat = Double.parseDouble(request.getParameter("PB_LAT"));
			//Double lng = Double.parseDouble(request.getParameter("PB_LNG"));
			int price = Integer.parseInt(request.getParameter("PB_PRICE"));
			int category = Integer.parseInt(request.getParameter("PB_CATEGORY"));
			String hashtag = request.getParameter("PB_HASHTAG");*/
			
			HttpSession session = request.getSession();
			
			purchaseBoardBean.setPB_WRITER(session.getAttribute("id").toString());
			purchaseBoardBean.setPB_TITLE(request.getParameter("TITLE"));
			purchaseBoardBean.setPB_CONTENT(request.getParameter("CONTENT"));
			purchaseBoardBean.setPB_CATEGORY(Integer.parseInt(request.getParameter("CATEGORY")));
			purchaseBoardBean.setPB_PRICE(Integer.parseInt(request.getParameter("PRICE")));
			purchaseBoardBean.setPB_HASHTAG(request.getParameter("HASHTAG"));
			purchaseBoardBean.setPB_LAT(Double.parseDouble(request.getParameter("markerLat")));  
			purchaseBoardBean.setPB_LNG(Double.parseDouble(request.getParameter("markerLng")));

			result = purchaseDAO.purchaseInsert(purchaseBoardBean);

			if (result == 0) {
				System.out.println("게시판 등록 실패");
				return null;
			} else {
				System.out.println("게시판 등록 완료");
			}

			forward.setRedirect(true);
			forward.setPath("./pbmain.pb");
			
			///////이미지/////////////
			
			int BOARD_NO = purchaseDAO.getMaxCount();
			String tableName = "PURCHASE_BOARD";
		    String url = request.getParameter("img_hidden");
		    
		    imageBean.setBOARD_NO(BOARD_NO);
		    imageBean.setIMAGE_URL(url);
		   
		      int result2 = imageDAO.imageInsert(imageBean, tableName);
		      if (result2 == 0) {
		         System.out.println("image insert fail!");
		      }

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}
}