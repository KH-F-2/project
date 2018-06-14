package com.project101.action.board.report;

import java.io.*;
import javax.servlet.http.*;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.ReportBoardBean;
import com.project101.dao.ReportBoardDAO;

public class ReportBoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		ReportBoardDAO reportDao = new ReportBoardDAO();
		HttpSession session = request.getSession();
		
		String rb_rp_id = request.getParameter("rb_rp_id");
		int rb_rp_no = Integer.parseInt(request.getParameter("rb_rp_no"));
		String rb_rp_board_name = request.getParameter("rb_rp_board_name");
		String rb_writer = (String) session.getAttribute("id");
		String rb_title = request.getParameter("rb_title");
		String rb_content = request.getParameter("rb_content");

		int result = reportDao.boardInsert(rb_rp_id, rb_rp_no, rb_rp_board_name, rb_writer, rb_title, rb_content);
		
		if (result == 1) {
			out.println("<script> alert('게시판 등록 성공!'); location.href='./rbmain.rb';</script>");
		} else {
			out.println("<script> alert('게시판 등록 실패!'); history.back();</script>");
		}
		out.close();

		return null;
	}

}
