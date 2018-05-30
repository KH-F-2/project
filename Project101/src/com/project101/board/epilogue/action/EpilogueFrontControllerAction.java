package com.project101.board.epilogue.action;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("*.eb")
public class EpilogueFrontControllerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EpilogueFrontControllerAction() {
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
		
		Action action=null;
		ActionForward forward=null;
		try {
			if(command.equals("/ebmain.eb")) {
				action=new EpilogueBoardListAction();
				forward=action.execute(request, response);
			}
			else if(command.equals("/ebwrite.eb")) {
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/epilogueboard/ebwrite.jsp");
			}
			else if(command.equals("/ebwriteaction.eb")) {
				action=new EpilogueBoardAddAction();
				forward=action.execute(request, response);
			}else if(command.equals("/ebview.eb")) {
				action = new EpilogueBoardDetailAction();
				forward = action.execute(request, response);
			}else if(command.equals("/ebdelete.eb")) {
				action = new EpilogueBoardDeleteAction();
				forward = action.execute(request, response);
			}else if(command.equals("/ebmodify.eb")) {
				action = new EpilogueBoardModifyAction();
				forward = action.execute(request, response);
			}else if(command.equals("/ebmodifyview.eb")) {
				action = new EpilogueBoardModifyView();
				forward = action.execute(request, response);
			}else if (command.equals("/ebfiledown.eb")) {
				action = new EpilBoardFileDownAction();
				try {
					forward=action.execute(request, response);
				}catch (Exception e) {
					e.printStackTrace();
				}
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
