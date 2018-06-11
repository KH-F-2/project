package com.project101.bean;

import java.sql.Date;

public class MessageBoardBean {
	private int MS_NO;
	private String MS_SEND; //작성자
	private String MS_TO; //받는사람
	private Date MS_DATE; //보내는날짜
	private String MS_TITLE;//제목
	private String MS_CONTENT; //내용
	
	public MessageBoardBean(int MS_NO, String MS_SEND, String MS_TO, Date MS_DATE, String MS_TITLE, String MS_CONTENT) {
		this.MS_NO = MS_NO;
		this.MS_SEND = MS_SEND;
		this.MS_TO = MS_TO;
		this.MS_DATE = MS_DATE;
		this.MS_TITLE = MS_TITLE;
		this.MS_CONTENT = MS_CONTENT;
	}
	public MessageBoardBean() {
	}
	public int getMS_NO() {
		return MS_NO;
	}
	public void setMS_NO(int mS_NO) {
		MS_NO = mS_NO;
	}
	public String getMS_SEND() {
		return MS_SEND;
	}
	public void setMS_SEND(String mS_SEND) {
		MS_SEND = mS_SEND;
	}
	public String getMS_TO() {
		return MS_TO;
	}
	public void setMS_TO(String mS_TO) {
		MS_TO = mS_TO;
	}
	public Date getMS_DATE() {
		return MS_DATE;
	}
	public void setMS_DATE(Date mS_DATE) {
		MS_DATE = mS_DATE;
	}
	public String getMS_TITLE() {
		return MS_TITLE;
	}
	public void setMS_TITLE(String mS_TITLE) {
		MS_TITLE = mS_TITLE;
	}
	public String getMS_CONTENT() {
		return MS_CONTENT;
	}
	public void setMS_CONTENT(String mS_CONTENT) {
		MS_CONTENT = mS_CONTENT;
	}
	
}