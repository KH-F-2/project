package com.project101.action.board.epilogue;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.EpilogueBoardBean;
import com.project101.dao.EpilogueBoardDAO;

public class EpilogueWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");

		String writer = request.getParameter("SB_WRITER");
		PrintWriter out = response.getWriter();

		EpilogueBoardDAO ebDAO = new EpilogueBoardDAO();
		EpilogueBoardBean ebBoardBean = new EpilogueBoardBean();

		String realFolder = "";
		String saveFolder = "epilupload";

		int fileSize = 5 * 1024 * 1024; // 업로드할 파일의 최대 사이즈 입니다.

		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder : " + realFolder);
		
		try {
			MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());

			ebBoardBean.setSB_WRITER(multi.getParameter("SB_WRITER"));
			ebBoardBean.setSB_TITLE(multi.getParameter("SB_TITLE"));
			ebBoardBean.setSB_CONTENT(multi.getParameter("SB_CONTENT"));
			ebBoardBean.setSB_PRICE(Integer.parseInt(multi.getParameter("SB_PRICE")));
			ebBoardBean.setSB_GRADE(Integer.parseInt(multi.getParameter("SB_GRADE")));
			/*
			 * SimpleDateFormat format=new SimpleDateFormat("MM-dd-yyyy");
			 * sellboard.setSB_BDATE(format.format(request.getParameter("SB_BDATE")));
			 */

			ebBoardBean.setSB_FILE(multi.getFilesystemName((String) multi.getFileNames().nextElement()));

			int result = ebDAO.boardInsert(ebBoardBean);
			
			if (result == 1) {
				out.println("<script> alert('게시판 등록 성공!'); location.href='./ebmain.eb';</script>");
			} else {
				out.println("<script> alert('게시판 등록 실패!'); history.back()';</script>");
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}