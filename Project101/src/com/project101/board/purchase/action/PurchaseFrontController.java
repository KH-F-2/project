package com.project101.board.purchase.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PurchaseFrontController extends javax.servlet.http.HttpServlet {

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
		
		//수정하기
		if(command.equals("/PurchaseModifyAction.buy")) {
			action = new PurchaseModifyAction();//다형성에 의한 업캐스팅
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
				}
			}else if(command.equals("/PurchaseDetailAction.buy")) {
				action = new PurchaseDetailAction();
				try {
					forward=action.execute(request, response);
				}catch (Exception e) {
					e.printStackTrace();
				}
			//리스트목록
			}else if(command.equals("/PurchaseListAction.buy")) {
				action = new PurchaseListAction();
				try {
					forward = action.execute(request, response);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
	}
}
