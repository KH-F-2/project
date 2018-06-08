package com.project101.action.member;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project101.action.Action;
import com.project101.action.ActionForward;
import com.project101.bean.Member;
import com.project101.dao.MemberDAO;

public class JoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String domain = request.getParameter("domain");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String phone = request.getParameter("phone");
		String post = request.getParameter("post");
		String address = request.getParameter("address");
		String detailaddress = request.getParameter("detailaddress");
	
		
		Member m = new Member();
		
		m.setId(id);
		m.setEmail(email + "@" + domain);
		m.setPassword(password);
		m.setNickname(nickname);
		m.setPhone(phone);
		m.setPost(post);
		m.setAddress(address);
		m.setDetailaddress(detailaddress);
		
		MemberDAO mdao = new MemberDAO();
		
		int result = mdao.insert(m);
		System.out.println(result);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		if(result == 1) {
			String authNum = connectEmail(email + "@" + domain);
			HttpSession session = request.getSession();
			session.setAttribute("authNum", authNum);
			
			out.println("alert('성공');");
			out.println("location.href='main.me';");
		}else if(result== 0) {
			out.println("alert('실패');");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();
		return null;
	}
	
	public String connectEmail(String email){
		String to1 = email; // 인증위해 사용자가 입력한 이메일주소
		String host = "smtp.gmail.com"; // smtp 서버
		String subject = "Nice2MeetU인증메일"; // 보내는 제목 설정
		String fromName = "관리자"; // 보내는 이름 설정
		String from = "sldf919191@gmail.com"; // 보내는 사람(구글계정)
		String authNum = authNum(); // 인증번호 위한 난수 발생부분
		String content = "<h1>Nice2MeetU</h1><br><h1><a href='localhost:8088/Project101/emailconfirm.me?num=" + authNum + "&email=" + to1 + "'>인증하러 가기</a></h1>";
		
        // SMTP 이용하기 위해 설정해주는 설정값들
		try{
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", host);
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.port","465");
		props.put("mail.smtp.user",from);
		props.put("mail.smtp.auth","true");
		
		Session mailSession = Session.getInstance(props, new javax.mail.Authenticator(){
			    protected PasswordAuthentication getPasswordAuthentication(){
				    return new PasswordAuthentication
                                        ("sldf919191@gmail.com","wlsn6011^^"); // gmail계정
			}
		});
		
		Message msg = new MimeMessage(mailSession);
		InternetAddress []address1 = {new InternetAddress(to1)};
		msg.setFrom(new InternetAddress
                      (from, MimeUtility.encodeText(fromName,"utf-8","B")));
		msg.setRecipients(Message.RecipientType.TO, address1); // 받는사람 설정
		msg.setSubject(subject); // 제목설정
		msg.setSentDate(new java.util.Date()); // 보내는 날짜 설정
		msg.setContent(content,"text/html; charset=utf-8"); // 내용설정
		
		Transport.send(msg); // 메일보내기
		}catch(MessagingException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return authNum;
	}

    // 난수발생 function
	public static String authNum(){
		StringBuffer buffer=new StringBuffer();
		for(int i=0;i<=4;i++){
			int num=(int)(Math.random()*9+1);
			buffer.append(num);
		}
		return buffer.toString();
	}
}
