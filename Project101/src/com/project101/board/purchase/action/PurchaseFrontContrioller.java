package com.project101.board.purchase.action;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("*.buy")
public class PurchaseFrontContrioller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public PurchaseFrontContrioller() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		System.out.println("BRequestURI = " + requestURI);

		String contextPath = request.getContextPath();
		System.out.println("BcontextPath = " + contextPath);

		String command = requestURI.substring(contextPath.length());
		System.out.println("Bcommand = " + command);

		Action action = null;
		ActionForward forward = null;
	

		if (command.equals("/BoardWrite.buy")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/buy/buy_board_write.jsp");
			System.out.println("on Boardwrite aaa");
		}
		
		if (forward != null) {
			if (forward.isRedirect()) { // 리다이렉트 됩니다.
				response.sendRedirect(forward.getPath());
			} else {// 포워딩됩니다.
				RequestDispatcher dispathcher = request.getRequestDispatcher(forward.getPath());
				dispathcher.forward(request, response);
			}

		}
	}

	// doProcess(request,response)메서드를 구현하여 요청이 GET방식이든 POST방식으든 같은 메서드에서 요청을 처라하도록
	// 만듬

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
