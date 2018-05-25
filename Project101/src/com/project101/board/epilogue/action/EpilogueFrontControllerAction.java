package com.project101.board.epilogue.action;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("*.epil")
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
			if(command.equals("/BoardList.epil")) {
				action=new EpilogueBoardListAction();
				forward=action.execute(request, response);
			}
			else if(command.equals("/BoardWrite.epil")) {
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/epilogueboard/epilogueboard_board_write.jsp");
			}
			else if(command.equals("/BoardAddAction.epil")) {
				action=new EpilogueBoardAddAction();
				forward=action.execute(request, response);
			}else if(command.equals("/BoardDetail.epil")) {
				action = new EpilogueBoardDetailAction();
				forward = action.execute(request, response);
			}else if(command.equals("/BoardDelete.epil")) {
				action = new EpilogueBoardDeleteAction();
				forward = action.execute(request, response);
			}else if(command.equals("/BoardModifyAction.epil")) {
				action = new EpilogueBoardModifyAction();
				forward = action.execute(request, response);
			}else if(command.equals("/BoardModifyView.epil")) {
				action = new EpilogueBoardModifyView();
				forward = action.execute(request, response);
			}else if (command.equals("/BoardFileDown.epil")) {
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
