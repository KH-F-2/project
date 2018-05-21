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

		if (command.equals("/BoardWrite.buy")) { // �ۼ�������
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/buy/buy_board_write.jsp");
			System.out.println("on Boardwrite aaa");
		} else if (command.equals("/BoardModifyView.buy")) { // ����������
			action = new PurchaseModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardDelete.buy")) { // ����������
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./buy/buy_board_delete.jsp");
			System.out.println("on delete ddd");
		}
		// �׶� �׶� �߰�

		if (forward != null) {
			if (forward.isRedirect()) { // �����̷�Ʈ �˴ϴ�.
				response.sendRedirect(forward.getPath());
			} else {// �������˴ϴ�.
				RequestDispatcher dispathcher = request.getRequestDispatcher(forward.getPath());
				dispathcher.forward(request, response);
			}

		} else if (command.equals("/PurchaseListAction.buy")) {
			action = new PurchaseListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// doProcess(request,response)�޼��带 �����Ͽ� ��û�� GET����̵� POST������� ���� �޼��忡�� ��û�� ó���ϵ���
	// ����

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}