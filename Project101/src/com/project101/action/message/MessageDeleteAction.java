package com.project101.action.message;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.MessageBoardBean;
import com.project101.dao.MessageDAO;

public class MessageDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			ActionForward forward = new ActionForward();
			PrintWriter out = response.getWriter();
			boolean result = false;
			
			MessageDAO ebDAO = new MessageDAO();
			MessageBoardBean boardBean = new MessageBoardBean();
			String MS_SEND = request.getParameter("MS_SEND");
			System.out.println("MS_SEND :" + MS_SEND );
/*			int num = Integer.parseInt(request.getParameter("num"));*/
			boardBean.setMS_SEND(MS_SEND);

			result = ebDAO.boardDelete(boardBean);

			if (result == false) {
				out.println("<script>alert('삭제 실패');history.back();</script>");
			} else {
				out.println("<script>alert('삭제되었습니다.'); location ='./msmessagetolist.ms';</script>");
			}

			out.close();

			forward.setRedirect(false);

			return null;
		}
	}
