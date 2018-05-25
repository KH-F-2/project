package com.project101.board.purchase.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.buy")
public class PurchaseFrontController extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		System.out.println("requestURI = " + RequestURI);
		// getContextPath() : 컨텍스트 경로가 반환됩니다.
		// contextPath는 "/JspProject"가 반환됩니다.
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		// RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터
		// 마지막 위치 문자까지 추출합니다.
		// command는 "/login.net" 반환됩니다.
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);

		// 초기화
		ActionForward forward = null;
		Action action = null;

		// 수정하기
		if (command.equals("/PurchaseModifyAction.buy")) {
			action = new PurchaseModifyAction();// 다형성에 의한 업캐스팅
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 상세보기
		} else if (command.equals("/PurchaseDetailAction.buy")) {
			action = new PurchaseDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 리스트목록
		} else if (command.equals("/PurchaseListAction.buy")) {
			action = new PurchaseListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardWrite.buy")) { 
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/buy/buy_board_write.jsp");
			System.out.println("on Boardwrite aaa");
		} else if (command.equals("/PurchaseModifyView.buy")) { // 수정보기
			action = new PurchaseModifyView();
			try {
				forward = action.execute(request, response);
				System.out.println("on PurchaseModifyView vvv");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/PurchaseModifyAction.buy")) { // 수정
			action = new PurchaseModifyAction();
			try {
				System.out.println("on PurchaseModifyView vavava");
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/PurchaseAddAction.buy")) { // 작성
			action = new PurchaseAddAction();
			try {
				forward = action.execute(request, response);
				System.out.println("on addAction.buy");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/PurchaseDeleteAction.buy")) { // 삭제
			action = new PurchaseDeleteAction();
			try {
				forward = action.execute(request, response);
				System.out.println("on PurchaseDeleteAction.buy");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}    	

		if (forward != null) {
			if (forward.isRedirect()) { // 리다이렉트
				response.sendRedirect(forward.getPath());
			} else { // 포워딩
				RequestDispatcher dispathcher = request.getRequestDispatcher(forward.getPath());
				dispathcher.forward(request, response);
			}

		}
	}

	// doProcess(request,response)메서드를 구현하여 요청이 GET방식이든 POST방식으든 같은 메서드에서 요청을 처라

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
}
