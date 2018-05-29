package com.project101.notice.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.notice")
public class FrontController extends HttpServlet {
       
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
       
   /* RequestDispatcher dispatcher = request.getRequestDispatcher("/180502/8.member/loginForm.jsp");


        dispatcher.forward(request, response);*/
    String RequestURI = request.getRequestURI();
    System.out.println("RequestURI = "+RequestURI);
    
    //getContextPath() : 컨텍스트 경로가 반환됩니다.
    //contextPath는 "/JspProject"가 반환됩니다.
    String contextPath=request.getContextPath();
    System.out.println("contextPath = "+contextPath);
    
    //RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자로부터 마지막 위치 문자까지 추출합니다.
    //command는 "/login.net" 반환합니다.
    String command=RequestURI.substring(contextPath.length());
    System.out.println("command = " + command);
   
   
    //초기화
    ActionForward forward = null;
    Action action = null;
    if(command.equals("/noticeboard/notice_board_list.notice")) {
        action = new listAddAction();
        try {
           forward = action.execute(request, response);
        }catch(Exception e) {
           e.printStackTrace();
        }
     }
     if(forward != null) {
        if(forward.isRedirect()){//리다이렉트됩니다.
           response.sendRedirect(forward.getPath());
        }else {//포워딩됩니다.
           RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
              dispatcher.forward(request,response);
        }
     }
     
 }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      doProcess(request,response);
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doProcess(request,response);
   }

}