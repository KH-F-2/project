package com.project101.action.board.epilogue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project101.action.Action;
import com.project101.action.ActionForward;

public class EpilogueFileDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = request.getParameter("filename");
		System.out.println("fileName = " + fileName);

		String savePath = "epilupload";

		// 서블릿의 실행 환경 정보를 담고 있는 객체를 리턴합니다.
		// (application 내장 객체를 리턴합니다.)
		ServletContext context = request.getServletContext();
		String sDownloadPath = context.getRealPath(savePath);

		String sFilePath = sDownloadPath + "/" + fileName;
		System.out.println(sFilePath);

		byte b[] = new byte[4096];

		String sMimeType = context.getMimeType(sFilePath);
		System.out.println("sMimeType>>>" + sMimeType);

		if (sMimeType == null)
			sMimeType = "application/octet-stream";

		response.setContentType(sMimeType);

		// 이 부분이 한글 파일명이 깨지는 것을 방지해 줍니다.
		String sEncoding = new String(fileName.getBytes("euc-kr"), "ISO-8859-1"); // ISO-8859-1은 톰캣이 가지고 있는 기본값
		System.out.println(sEncoding);

		response.setHeader("Content-Disposition", "attachment; filename= " + sEncoding);

		ServletOutputStream so = response.getOutputStream();
		try {
			// 웹 브라우저로의 출력 스트림 생성합니다.
			BufferedOutputStream out2 = new BufferedOutputStream(so);
			// sFilePath로 지정한 파일에 대한 입력 스트림을 생성합니다.
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(sFilePath));
			int numRead;
			// read(b,0,b.length) : 바이트 배열 b의 0부터 b.length
			// 크기 만큼 읽어옵니다.
			while ((numRead = in.read(b, 0, b.length)) != -1) {// 일
				// 바이트 배열 b의 0번 부터 numRead크기 만큼 브라우저로 출력
				out2.write(b, 0, numRead);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
