package com.project101.comment.db;

import java.util.Date;

public class CommentBean {
	private int CMT_NO; // 댓글 번호
	private String CMT_WRITER; // 작성자
	private Date CMT_DATE; // 작성일
	private int CMT_SUBJECT_NO; // 게시글번호
	private String CMT_CONTENT; // 댓글 내용
	private int CMT_RE_REF; // 댓댓글 작성시 참조 글번호
	private int CMT_RE_LEV; // 댓댓글 깊이
	private int CMT_SEQ; // 댓글 순서
	
	private String CMT_VIEW_DATE; //보여주기용 DATE DATE형 String형 으로 포멧에 맞춰 출력
	
	public int getCMT_NO() {
		return CMT_NO;
	}
	public void setCMT_NO(int cMT_NO) {
		CMT_NO = cMT_NO;
	}
	public String getCMT_WRITER() {
		return CMT_WRITER;
	}
	public void setCMT_WRITER(String cMT_WRITER) {
		CMT_WRITER = cMT_WRITER;
	}
	public Date getCMT_DATE() {
		return CMT_DATE;
	}
	public void setCMT_DATE(Date cMT_DATE) {
		CMT_DATE = cMT_DATE;
	}
	public int getCMT_SUBJECT_NO() {
		return CMT_SUBJECT_NO;
	}
	public void setCMT_SUBJECT_NO(int cMT_SUBJECT_NO) {
		CMT_SUBJECT_NO = cMT_SUBJECT_NO;
	}
	public String getCMT_CONTENT() {
		return CMT_CONTENT;
	}
	public void setCMT_CONTENT(String cMT_CONTENT) {
		CMT_CONTENT = cMT_CONTENT;
	}
	public int getCMT_RE_REF() {
		return CMT_RE_REF;
	}
	public void setCMT_RE_REF(int cMT_RE_REF) {
		CMT_RE_REF = cMT_RE_REF;
	}
	public int getCMT_RE_LEV() {
		return CMT_RE_LEV;
	}
	public void setCMT_RE_LEV(int cMT_RE_LEV) {
		CMT_RE_LEV = cMT_RE_LEV;
	}
	public int getCMT_SEQ() {
		return CMT_SEQ;
	}
	public void setCMT_SEQ(int cMT_SEQ) {
		CMT_SEQ = cMT_SEQ;
	}
	public String getCMT_VIEW_DATE() {
		return CMT_VIEW_DATE;
	}
	public void setCMT_VIEW_DATE(String cMT_VIEW_DATE) {
		CMT_VIEW_DATE = cMT_VIEW_DATE;
	}

	


}