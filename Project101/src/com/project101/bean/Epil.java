package com.project101.bean;

import java.sql.Date;

public class Epil {
	private int EP_NO;
	private String EP_NAME; //판매자
	private String EP_WRITER; //작성자
	private String EP_CONTENT; //내용
	private Date EP_DATE; //날짜
	private int EP_STAR;//별점
	
	public Epil(int EP_NO, String EP_NAME, String EP_WRITER, String EP_CONTENT, Date EP_DATE, int EP_STAR) {
		this.EP_NO=EP_NO;
		this.EP_NAME=EP_NAME;
		this.EP_WRITER=EP_WRITER;
		this.EP_CONTENT=EP_CONTENT;
		this.EP_DATE=EP_DATE;
		this.EP_STAR=EP_STAR;
		
	}

	public Epil() {
	}

	public int getEP_NO() {
		return EP_NO;
	}

	public void setEP_NO(int eP_NO) {
		EP_NO = eP_NO;
	}

	public String getEP_NAME() {
		return EP_NAME;
	}

	public void setEP_NAME(String eP_NAME) {
		EP_NAME = eP_NAME;
	}

	public String getEP_WRITER() {
		return EP_WRITER;
	}

	public void setEP_WRITER(String eP_WRITER) {
		EP_WRITER = eP_WRITER;
	}

	public String getEP_CONTENT() {
		return EP_CONTENT;
	}

	public void setEP_CONTENT(String eP_CONTENT) {
		EP_CONTENT = eP_CONTENT;
	}

	public Date getEP_DATE() {
		return EP_DATE;
	}

	public void setEP_DATE(Date eP_DATE) {
		EP_DATE = eP_DATE;
	}

	public int getEP_STAR() {
		return EP_STAR;
	}

	public void setEP_STAR(int eP_STAR) {
		EP_STAR = eP_STAR;
	}		
}