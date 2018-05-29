package com.project101.member.db;

import java.util.Date;

import oracle.sql.DATE;

public class Endhistory {
	private String EH_CATEGORY; //카테고리
	private String EH_SUBJECT;
	private int EH_PRICE;
	private Date EH_DATE;
	private String EH_NAME;
	private int EH_NUM;
	
	public Endhistory(String EH_CATEGORY, String EH_SUBJECT, int EH_PRICE, Date EH_DATE,String EH_NAME, int EH_NUM) {
		this.EH_CATEGORY = EH_CATEGORY;
		this.EH_SUBJECT = EH_SUBJECT;
		this.EH_PRICE = EH_PRICE;
		this.EH_DATE = EH_DATE;
		this.EH_NAME = EH_NAME;	
		this.EH_NUM = EH_NUM;
	}

	public Endhistory() {
	}

	public String getEH_CATEGORY() {
		return EH_CATEGORY;
	}

	public void setEH_CATEGORY(String eH_CATEGORY) {
		EH_CATEGORY = eH_CATEGORY;
	}

	public String getEH_SUBJECT() {
		return EH_SUBJECT;
	}

	public void setEH_SUBJECT(String eH_SUBJECT) {
		EH_SUBJECT = eH_SUBJECT;
	}

	public int getEH_PRICE() {
		return EH_PRICE;
	}

	public void setEH_PRICE(int eH_PRICE) {
		EH_PRICE = eH_PRICE;
	}

	public Date getEH_DATE() {
		return EH_DATE;
	}

	public void setEH_DATE(Date eH_DATE) {
		EH_DATE = eH_DATE;
	}

	public String getEH_NAME() {
		return EH_NAME;
	}

	public void setEH_NAME(String eH_NAME) {
		EH_NAME = eH_NAME;
	}

	public int getEH_NUM() {
		return EH_NUM;
	}

	public void setEH_NUM(int eH_NUM) {
		EH_NUM = eH_NUM;
	}			
}