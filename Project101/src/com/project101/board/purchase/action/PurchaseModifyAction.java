package com.project101.board.purchase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PurchaseModifyAction implements Action  {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//dao �� ���� ����Action �κ�
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		// �� ��� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
		//��� ���� :  forward.setPath("./BoardDetailAction.bo?num=" + boarddata.getBOARD_NUM());
		//���� ����
		forward.setPath("./buy/buy_board_modify.jsp");
		System.out.println("on modify aaa");
		return forward;
	}
	

}
