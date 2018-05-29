package com.project101.member.db;

import java.util.Date;

public class Trade {
	private int TR_NUM;
	private String TR_CATEGORY; //카테고리
	private String TR_SUBJECT;
	private int TR_PRICE;
	private Date TR_DATE;
	private String TR_NAME;
	private String TR_CONTENT;
	
	public Trade(int TR_NUM, String TR_CATEGORY, String TR_SUBJECT, int TR_PRICE, Date TR_DATE,String TR_NAME, String TR_CONTENT) {
		this.TR_NUM = TR_NUM;
		this.TR_CATEGORY = TR_CATEGORY;
		this.TR_SUBJECT = TR_SUBJECT;
		this.TR_PRICE = TR_PRICE;
		this.TR_DATE = TR_DATE;
		this.TR_NAME = TR_NAME;	
		this.TR_CONTENT=TR_CONTENT;
	}

	public Trade() {
	}

	public int getTR_NUM() {
		return TR_NUM;
	}

	public void setTR_NUM(int tR_NUM) {
		TR_NUM = tR_NUM;
	}

	public String getTR_CATEGORY() {
		return TR_CATEGORY;
	}

	public void setTR_CATEGORY(String tR_CATEGORY) {
		TR_CATEGORY = tR_CATEGORY;
	}

	public String getTR_SUBJECT() {
		return TR_SUBJECT;
	}

	public void setTR_SUBJECT(String tR_SUBJECT) {
		TR_SUBJECT = tR_SUBJECT;
	}

	public int getTR_PRICE() {
		return TR_PRICE;
	}

	public void setTR_PRICE(int tR_PRICE) {
		TR_PRICE = tR_PRICE;
	}

	public Date getTR_DATE() {
		return TR_DATE;
	}

	public void setTR_DATE(Date tR_DATE) {
		TR_DATE = tR_DATE;
	}

	public String getTR_NAME() {
		return TR_NAME;
	}

	public void setTR_NAME(String tR_NAME) {
		TR_NAME = tR_NAME;
	}

	public String getTR_CONTENT() {
		return TR_CONTENT;
	}

	public void setTR_CONTENT(String tR_CONTENT) {
		TR_CONTENT = tR_CONTENT;
	}
	
	
			
}