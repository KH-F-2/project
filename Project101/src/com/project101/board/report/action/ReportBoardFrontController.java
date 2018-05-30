package com.project101.board.report.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.rb")
public class ReportBoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReportBoardFrontController() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/reportboard/rb_write.jsp");
			} else if (command.equals("/rbwriteaction.rb")) {
				action = new ReportBoardAddAction();
				forward = action.execute(request, response);
			} else if (command.equals("/rbview.rb")) {
				action = new ReportBoardDetailAction();
				forward = action.execute(request, response);
			} else if (command.equals("/rbdelete.rb")) {
				action = new ReportBoardDeleteAction();
				forward = action.execute(request, response);
			}

			else if (command.equals("/rbmodifyview.rb")) {
				action = new ReportBoardModifyView();
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
