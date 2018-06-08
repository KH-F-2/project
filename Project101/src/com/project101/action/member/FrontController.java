package com.project101.action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.SignEpilAction;

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

			if (command.equals("/signin.me")) {
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
				forward.setPath("template.jsp?page=/member/signup.jsp");

			} else if (command.equals("/mypage.me")) {
				action = new MemberinfoAction();
				forward = action.execute(request, response);

			} else if (command.equals("/joinProcess.me")) {
				action = new JoinProcessAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (command.equals("/idcheck.me")) {
				action = new IdCheckAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (command.equals("/nicknamecheck.me")) {
				action = new NicknameCheckAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (command.equals("/sellerpage_main.map")) {
				action = new SellerpageListAddAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/find.me")) {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/member/find.jsp");

			} else if (command.equals("/findIDResult.me")) {
				action = new FindIDResultAction();
				try {
					forward = action.execute(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/findPWResult.me")) {
				action = new FindPWResultAction();
				try {
					forward = action.execute(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/mypage.me")) {
				action = new MemberinfoAction();
				try {
					forward = action.execute(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			else if (command.equals("/Updatemember.me")) {
				action = new UpdatememberAction();
				try {
					forward = action.execute(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/emailconfirm.me")) {
				action = new ConfirmEmailAction();
				try {
					forward = action.execute(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/signepil.me")) {
				action = new SignEpilAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/sellerpage_main_ajax.me")) {
				action = new SellerpageWriterAction();
				forward = action.execute(request, response);
			} else if (command.equals("/sellerpage_main2_ajax.me")) {
				action = new SellerpageWriterAction2();
				forward = action.execute(request, response);
			} else if (command.equals("/signepil.me")) {
				action = new SignEpilAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/signaddaction.me")) {
				action = new SignEpilAddAction();
				forward = action.execute(request, response);
			} else if (command.equals("/signepilwriter.me")) {
				action = new SignEpilWriterAction();
				forward = action.execute(request, response);
			} else if (command.equals("/signepilcontent.me")) {
				action = new SignEpilContentAction();
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
