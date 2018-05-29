package com.project101.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.me")
public class FrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		System.out.println("requestURI = " + requestURI);

		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);

		String command = requestURI.substring(contextPath.length());
		System.out.println("command = " + command);

		ActionForward forward = null;
		Action action = null;

		try {

			if (command.equals("/main.me")) {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("template.jsp");
			} else if (command.equals("/signin.me")) {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("template.jsp?page=member/signin.jsp");
			} else if (command.equals("/signinprocess.me")) {
				action = new SignInProcessAction();
				forward = action.execute(request, response);
			} else if (command.equals("/signout.me")) {
				action = new SignOutProcessAction();
				forward = action.execute(request, response);
			} else if (command.equals("/signup.me")) {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/member/signup.jsp");
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
