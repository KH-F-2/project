package com.project101.board.sell.action;

import java.io.PrintWriter;

import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project101.board.sell.db.SellBoardBean;
import com.project101.board.sell.db.SellBoardDAO;

public class SellBoardAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
			
		SellBoardDAO selldao=new SellBoardDAO();
		ActionForward forward=new ActionForward();
		SellBoardBean sellboard=new SellBoardBean();
		
		/*String realFolder="";
		String saveFolder="boardupload";
		
		int fileSize=10*1024*1024;
		
		// 실제 저장 경로 지정
		realFolder=request.getSession().getServletContext().getRealPath(saveFolder);
		
		System.out.println("SellBoardAddAction - realFolder : "+realFolder);
		boolean result=false;
		
		try{
			MultipartRequest multi=new MultipartRequest(request, realFolder, fileSize, "euc-kr", new DefaultFileRenamePolicy());
			
			sellboard.setSB_WRITER(multi.getParameter("SB_WRITER"));
			sellboard.setSB_TITLE(multi.getParameter("SB_WRITER"));
			sellboard.setSB_CATEGORY(Integer.parseInt(multi.getParameter("SB_CATEGORY")));
			sellboard.setSB_CONTENT(multi.getParameter("SB_CONTENT"));
			sellboard.setSB_PRICE(Integer.parseInt(multi.getParameter("SB_PRICE")));
			sellboard.setSB_LATITUDE(Integer.parseInt(multi.getParameter("SB_LATITUDE")));
			sellboard.setSB_LOGITUDE(Integer.parseInt(multi.getParameter("SB_LOGITUDE")));
			sellboard.setSB_PICTURE(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			
			//result=selldao.boardInsert(sellboard);
			if(result) {
				forward.setRedirect(true);
				forward.setPath("./BoardList.sell");
				return forward;
			}else {
				out.println("<script> alert('입력 실패!'); history.back();</script>");
			}
			out.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}*/
		
		return null;
	}

}
