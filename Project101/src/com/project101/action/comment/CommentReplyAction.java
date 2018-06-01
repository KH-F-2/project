package com.project101.action.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.*;
import com.project101.bean.*;
import com.project101.dao.*;

public class CommentReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=euc-kr");
		request.setCharacterEncoding("euc-kr");

		CommentDAO commentDAO = new CommentDAO();
		CommentBean commentBean = new CommentBean();
		
		ActionForward forward = new ActionForward();
		
		int CMT_NO = Integer.parseInt(request.getParameter("CMT_NO"));
		String board_name = request.getParameter("CMT_BOARD_NAME");
		
		commentBean = commentDAO.getCommentDetail(CMT_NO, board_name);
		
		String CMT_WRITER = request.getParameter("writer");
		String CMT_CONTENT = request.getParameter("content");
		
		
		
		board.setBOARD_NUM(Integer.parseInt(request.getParameter("BOARD_NUM")));
		board.setBOARD_RE_REF(Integer.parseInt(request.getParameter("BOARD_RE_REF")));
		board.setBOARD_RE_LEV(Integer.parseInt(request.getParameter("BOARD_RE_LEV")));
		board.setBOARD_RE_SEQ(Integer.parseInt(request.getParameter("BOARD_RE_SEQ")));
		board.setBOARD_NAME(request.getParameter("BOARD_NAME"));
		board.setBOARD_PASS(request.getParameter("BOARD_PASS"));
		board.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
		board.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));

		int result = bdao.boardReply(board);

		if (result == 0) {
			PrintWriter out = response.getWriter();
			out.println("<script> alert('답글 입력 실패!'); history.back();</script>");
		}
		System.out.println("답글 달기 성공!");
		forward.setRedirect(true);
		forward.setPath("./BoardList.bo");
		// forward.setPath("./BoardDetailAction.bo?num="+result);

		return forward;
		
		
		
		int comment_boardNO = Integer.parseInt(request.getParameter("num"));
		int comment_cmtNO = Integer.parseInt(request.getParameter("cmtnum"));
		
		String comment_content = request.getParameter("comment_content");
		
		cmtBean.setCMT_NO(cmtDAO.getSeq());			//댓글 번호
		cmtBean.setCMT_SUBJECT_NO(comment_boardNO);	//게시글 번호
		cmtBean = cmtDAO.getDetail(comment_cmtNO);
		cmtBean.setCMT_CONTENT(comment_content); 	//댓글 내용
		
		boolean result = cmtDAO.cmtReplyInsert(cmtBean, comment_boardNO, comment_cmtNO, board_name);
		
		PrintWriter pw = response.getWriter();
		
		if(result) {
			pw.print(1);
		} else {
			pw.print(0);
		}
		pw.close();
		return null;
	}

}


public boolean cmtReplyInsert(CommentBean cmtBean, int comment_boardNO, int comment_cmtNO, String board_name) {

	int num = 0;
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement("select max(CMT_NO) from COMMENTS");
		rset = pstmt.executeQuery();

		if (rset.next()) {
			num = rset.getInt(1) + 1; // 최대값보다 1만큼 큰 값 지정
		} else {
			num = 1;
		}
		
	
		rset.close();
		pstmt.close();
		
		pstmt = conn.prepareStatement("update COMMENTS set CMT_RE_SEQ = CMT_RE_SEQ +1 where CMT_RE_REF = ? and CMT_RE_SEQ > ?");
		pstmt.setInt(1, cmtBean.getCMT_RE_REF());
		pstmt.setInt(2, cmtBean.getCMT_RE_SEQ());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();

		pstmt = conn.prepareStatement("insert into COMMENTS "
				+ "(CMT_NO, CMT_SUBJECT_NO, CMT_WRITER, CMT_DATE, CMT_CONTENT, CMT_RE_REF, CMT_RE_LEV, CMT_RE_SEQ, CMT_BOARD_NAME) "
				+ "values(?,?,?,sysdate,?,?,?,?,?)");

		pstmt.setInt(1, num);
		pstmt.setInt(2, cmtBean.getCMT_SUBJECT_NO());
		pstmt.setString(3, cmtBean.getCMT_WRITER());
		pstmt.setString(4, cmtBean.getCMT_CONTENT());
		if(cmtBean.getCMT_RE_SEQ()==0) {
		pstmt.setInt(5, comment_cmtNO);
		}else {
			pstmt.setInt(5, cmtBean.getCMT_RE_REF());
			
		}
		pstmt.setInt(6, cmtBean.getCMT_RE_LEV() + 1);
		pstmt.setInt(7, cmtBean.getCMT_RE_SEQ() + 1);
		pstmt.setString(8, board_name);

		result = pstmt.executeUpdate();
		
	
		if (result == 0) {
			return false;
		} else {
			return true;
		}

	} catch (Exception e) {
		System.out.println("cmtReplyInsert에러 : " + e);
		e.printStackTrace();
	} finally {
		if (rset != null) {
			try {
				rset.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}

	}

	return true;
}
