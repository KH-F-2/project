package com.project101.action.board.report;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;

@WebServlet("*.rb")
public class ReportBoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReportBoardFrontController() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		System.out.println("URI주소 : " + requestURI);
		String contextPath = request.getContextPath();
		System.out.println("context주소 : " + contextPath);
		System.out.println("프로젝트 주소길이 : " + contextPath.length());
		String command = requestURI.substring(contextPath.length());
		System.out.println("가상주소뽑기 : " + command);
		Action action = null;
		ActionForward forward = null;
		
		try {
			if (command.equals("/rbmain.rb")) {
				action = new ReportBoardListAction();
				forward = action.execute(request, response);

			} else if (command.equals("/rbwrite.rb")) {
				action = new ReportBoardWriteView();
				forward = action.execute(request, response);

			} else if (command.equals("/rbwriteaction.rb")) {
				action = new ReportBoardWriteAction();
				forward = action.execute(request, response);

			} else if (command.equals("/rbview.rb")) {
				action = new ReportBoardView();
				forward = action.execute(request, response);

			} else if (command.equals("/rbdelete.rb")) {
				action = new ReportBoardDeleteAction();
				forward = action.execute(request, response);

			} else if (command.equals("/rbmodifyview.rb")) {
				action = new ReportBoardModify();
				forward = action.execute(request, response);

			} else if (command.equals("/rbmodifyaction.rb")) {
				action = new ReportBoardModifyAction();
				forward = action.execute(request, response);
			}

			if (forward != null) {
				if (forward.isRedirect()) {
					response.sendRedirect(forward.getPath());
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
					dispatcher.forward(request, response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
