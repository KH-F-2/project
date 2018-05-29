package com.project101.board.purchase.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project101.board.purchase.db.PurchaseBoardBean;
import com.project101.board.purchase.db.PurchaseBoardDAO;

public class PurchaseAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		PurchaseBoardDAO buydao = new PurchaseBoardDAO();
		PurchaseBoardBean boarddata = new PurchaseBoardBean();
		ActionForward forward = new ActionForward();
		
		String realFolder="";
		
		String saveFolder="boardupload";
		
		int fileSize=5*1024*1024;
		
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		
		System.out.println("realFolder= " + realFolder);
		boolean result = false;
		
		try {
			MultipartRequest multi=null;
			multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
			
			boarddata.setId(multi.getParameter("id"));
			boarddata.setTitle(multi.getParameter("title"));
			boarddata.setContent(multi.getParameter("content"));
			boarddata.setFile(multi.getFilesystemName((String)multi.getFileNames().nextElement()));			
			result=buydao.PurchaseInsert(boarddata);

			String fullpath=realFolder+"\\"+multi.getFilesystemName(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			
			System.out.println("fullpath=="+fullpath);
			
			if(result==false) {
				System.out.println("게시판 등록 실패");
				return null;
			} else {
				System.out.println("게시판 등록 완료");
			}
			

			forward.setRedirect(true);
			forward.setPath("./PurchaseListAction.buy");
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return forward;
		
	}
	}