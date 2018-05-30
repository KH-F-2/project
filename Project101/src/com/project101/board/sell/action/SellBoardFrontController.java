package com.project101.board.sell.action;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("*.sb")
public class SellBoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SellBoardFrontController() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
		System.out.println("URI주소 : "+requestURI);
		String contextPath = request.getContextPath();
		System.out.println("context주소 : "+contextPath);
		System.out.println("프로젝트 주소길이 : "+contextPath.length());
		String command = requestURI.substring(contextPath.length());
		System.out.println("가상주소뽑기 : "+command);
		
		Action action = null;
		ActionForward forward = null;
		
		try {
			if(command.equals("/sbmain.sb")) {
				action = new SellBoardListAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("/sbwriteview.sb")) {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("template.jsp?page=/sellboard/sbwrite.jsp");
			}
			else if(command.equals("/sbwriteaction.sb")) {
				action = new SellBoardAddAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("/sbview.sb")) {
				action = new SellBoardDetailAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("/sbdelete.sb")) {
				action = new SellBoardDeleteAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("/sbmodifyview.sb")) {
				action = new SellBoardModifyView();
				forward = action.execute(request, response);
			}
			else if(command.equals("/sbmodifyaction.sb")) {
				action = new SellBoardModifyAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("/sbcommentaddaction.sb")) {
				action = new SellBoardCommentAddAction();
				forward = action.execute(request, response);
			}
				
			
			if(forward!=null) {
				if(forward.isRedirect()) {
					response.sendRedirect(forward.getPath());
				}
				else {
					RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
					dispatcher.forward(request, response);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
