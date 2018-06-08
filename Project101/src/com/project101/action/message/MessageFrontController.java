package com.project101.action.message;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;

@WebServlet("*.ms")
public class MessageFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MessageFrontController() {
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
			if (command.equals("/msmessagewrite.ms")) {
				action = new MessageWriteAction();
				forward=  action.execute(request, response);			
			}
			else if (command.equals("/msmessageaddaction.ms")) {
				action = new MessageAddAction();
				forward = action.execute(request, response);
			}
			else if (command.equals("/msmessagesendlist.ms")) {
				action = new MessageSendListAction();
				forward = action.execute(request, response);
			}
			else if (command.equals("/msmessagesenddetail.ms")) {
				action = new MessageSendDetailAction();
				forward = action.execute(request, response);
			}
			else if (command.equals("/msmessagetolist.ms")) {
				action = new MessageToListAction();
				forward = action.execute(request, response);
			}			
			else if (command.equals("/msmessagetodetail.ms")) {
				action = new MessageToDetailAction();
				forward = action.execute(request, response);
			}
			else if (command.equals("/msmessagewrite2.ms")) {
				action = new MessageWriteAction2();
				forward = action.execute(request, response);
			}
			else if (command.equals("/msmessageaddaction2.ms")) {
				action = new MessageAddAction2();
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
