package com.project101.board.purchase.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project101.board.purchase.db.PurchaseBoardBean;
import com.project101.board.purchase.db.PurchaseBoardDAO;

public class PurchaseWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PurchaseBoardDAO purchaseDAO = new PurchaseBoardDAO();
		PurchaseBoardBean boardBean = new PurchaseBoardBean();
		ActionForward forward = new ActionForward();

		String realFolder = "";
		String saveFolder = "boardupload";
		int fileSize = 5 * 1024 * 1024;
		boolean result = false;

		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder= " + realFolder);

		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());

			boardBean.setId(multi.getParameter("id"));
			boardBean.setTitle(multi.getParameter("title"));
			boardBean.setContent(multi.getParameter("content"));
			boardBean.setFile(multi.getFilesystemName((String) multi.getFileNames().nextElement()));

			result = purchaseDAO.purchaseInsert(boardBean);

			String fullPath = realFolder + "\\"
					+ multi.getFilesystemName(multi.getFilesystemName((String) multi.getFileNames().nextElement()));
			System.out.println("fullpath==" + fullPath);

			if (result == false) {
				System.out.println("게시판 등록 실패");
				return null;
			} else {
				System.out.println("게시판 등록 완료");
			}

			forward.setRedirect(true);
			forward.setPath("./pbmain.pb");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}
}