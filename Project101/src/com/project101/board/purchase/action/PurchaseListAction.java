package com.project101.board.purchase.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.board.purchase.db.PurchaseBoardBean;
import com.project101.board.purchase.db.PurchaseBoardDAO;

public class PurchaseListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PurchaseBoardDAO buydao = new PurchaseBoardDAO();
				List<PurchaseBoardBean> buylist = new ArrayList<PurchaseBoardBean>();
				
				int page =1;
				int limit = 10;
				
				if(request.getParameter("page")!=null) {
					page = Integer.parseInt(request.getParameter("page"));
				}
				System.out.println("�Ѿ�� ������ = " + page);
						
				//�� ����Ʈ ���� �޾ƿɴϴ�.
				int listcount = buydao.getListCount();
				
				//����Ʈ�� �޾ƿɴϴ�.
				buylist = buydao.getBuyList(page, limit);
				
				//�� ������ ��
				//db�� ����� �� ����Ʈ�� ���� 0�̸� �� �������� 0������
				//�� ����Ʈ�� ���� (1~10)�̸� 1������ (11~20) �̸� 2������ (21~30)�̸� 3������
				int maxpage = (listcount+limit-1) / limit;
				System.out.println("�� �������� =" + maxpage);
				
				/*
				 * startpage : ���� ������ �׷쿡�� �� ó���� ǥ�õ� ������ ���� �ǹ��մϴ�.
				 * 				([1], [11], [21] ��...)
				 * ������ �������� 30���� ���[1][2][3]...[30]���� �� ǥ���ϱ⿡�� �ʹ� ���� ������ ����
				 * �� ���������� 10������ �������� �̵��� �� �ְ� ǥ���մϴ�.
				 * ��) ������ �׷��� �Ʒ��� ���� ���
				 * [1][2][3][4][5][6][7][8][9][10]
				 * ������ �׷��� ���� �������� startpage��
				 * ������ �������� engpage�� ���մϴ�.
				 * 
				 * ���� 1~10�������� ������ ��Ÿ������ ������ �׷���
				 * [1][2][3]..[10]�� ǥ�õǰ�
				 * 11~20�������� ������ ��Ÿ������ ������ �׷���
				 * [11][12][13]..[20]���� ǥ�õ˴ϴ�.
				 */
				int startpage = ((page-1) / 10) * 10 + 1;
				System.out.println("���� �������� ������ ���� ������ �� =" + startpage);
				
				//endpage : ���� ������ �׷쿡�� ������ ������ ������ �� ([10],[20],[30],...)		
				int endpage = startpage + 10 -1;
				System.out.println("���� �������� ������ ������ ������ �� =" + endpage);
				
				/*������ �׷��� ������ ������ ���� �ִ� ������ ���Դϴ�.
				 * ���� ������ ������ �׷��� [21]~[30]�� ���
				 * ����������(startpage=21)�� ������������(endpage=30)
				 * ������ �ִ� ������(maxpage)�� 25��� [21]~[25]������ ǥ�õǵ��� �մϴ�. 
				 */
				if(endpage > maxpage) endpage = maxpage;
				
				request.setAttribute("page", page);//���� ������ ��
				request.setAttribute("maxpage", maxpage);//�ִ� ������ ��
				//���� �������� ǥ���� ù ������ ��
				request.setAttribute("startpage", startpage);
				
				//���� �������� ǥ���� �� ������ ��
				request.setAttribute("endpage", endpage);
				
				request.setAttribute("listcount", listcount);//�� ���� ��
				
				//�ش� �������� �� ����� ���� �ִ� ����Ʈ
				request.setAttribute("buylist", buylist);
				
				ActionForward forward =new ActionForward();
				forward.setRedirect(false);
				
				//�� ��� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
				forward.setPath("./buy/buy_board_main.jsp"); //���� �ؾߵ�		
				return forward;//BoardForntController.java�� ���ϵ˴ϴ�.		
			}
	}


