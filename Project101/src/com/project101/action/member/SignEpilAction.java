package com.project101.action.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.Epil;
import com.project101.dao.EpilDAO;

public class SignEpilAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionForward forward = new ActionForward();
      response.setContentType("text/html;charset=UTF-8");
      request.setCharacterEncoding("UTF-8");

      EpilDAO epDAO = new EpilDAO();
      int num = Integer.parseInt(request.getParameter("num"));
      String board_name = request.getParameter("board_name");
      System.out.println(board_name + " 여기!  " + num);
      
      String name = epDAO.getEpilName(num, board_name);
      
      request.setAttribute("name", name);

      forward.setPath("./member/signepil.jsp");
      forward.setRedirect(false);
      return forward;

   }
}