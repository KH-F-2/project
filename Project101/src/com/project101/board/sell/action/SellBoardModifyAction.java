package com.project101.board.sell.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project101.board.sell.db.SellBoardBean;
import com.project101.board.sell.db.SellBoardDAO;

public class SellBoardModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		int num = Integer.parseInt(request.getParameter("SB_NO"));
		String id = request.getParameter("SB_WRITER");
		
		boolean result = false;
		
		SellBoardDAO selldao = new SellBoardDAO();
		SellBoardBean selldata = new SellBoardBean();
		
		
		
		String realFolder = "";
		
		//WebContent아래에 폴더 생성하기
		String saveFolder = "boardupload";
		//업로드 할 파일의 최대 사이즈
		int filesize = 5 * 1024 * 1024;
		
		//실제 저장 경로를 지정
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request,realFolder, filesize, "utf-8", new DefaultFileRenamePolicy());
			SellBoardBean boarddata = new SellBoardBean();
			
			selldata.setSB_NO(num);
			selldata.setSB_CONTENT(request.getParameter("SB_CONTENT"));
			selldata.setSB_TITLE(request.getParameter("SB_TITLE"));
			selldata.setSB_CATEGORY(Integer.parseInt(request.getParameter("SB_CATEGORY")));
			selldata.setSB_LATITUDE(Integer.parseInt(request.getParameter("SB_LATITUDE")));
			selldata.setSB_LOGITUDE(Integer.parseInt(request.getParameter("SB_LOGITUDE")));
			selldata.setSB_DATE(new Date(request.getParameter("SB_DATA")));
			selldata.setSB_PRICE(Integer.parseInt(request.getParameter("SB_PRICE")));	
			result = selldao.sellModify(selldata);
			
			PrintWriter out = response.getWriter();
			if(result == false) {
				System.out.println("판매 게시판 수정 실패입니다.");
				out.println("<script>alert('실패했습니다.');history.back();</script>");
				return null;
			} else if(result == true) {
				System.out.println("판매 게시판 수정 완료입니다.");
				out.println("<script>alert('성공했습니다..');</script>");
				forward.setRedirect(false);
				forward.setPath("/BoardDetail.sell");
			} 
			} catch(Exception e) {
				e.printStackTrace();
		
			}
		
		
		return forward;
	}

}
