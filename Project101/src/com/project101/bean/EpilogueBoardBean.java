package com.project101.bean;

import java.util.Date;

public class EpilogueBoardBean {
	private int SB_NO;
	private String SB_WRITER;
	private String SB_TITLE;
	private String SB_CONTENT;
	private int SB_PRICE;
	private Date SB_DATE;
	private int SB_READCOUNT;
	private String SB_FILE;
	private int SB_GRADE; //평점
	
	public EpilogueBoardBean() {
		super();
	}

	public int getSB_NO() {
		return SB_NO;
	}

	public void setSB_NO(int sB_NO) {
		SB_NO = sB_NO;
	}

	public String getSB_WRITER() {
		return SB_WRITER;
	}

	public void setSB_WRITER(String sB_WRITER) {
		SB_WRITER = sB_WRITER;
	}

	public String getSB_TITLE() {
		return SB_TITLE;
	}

	public void setSB_TITLE(String sB_TITLE) {
		SB_TITLE = sB_TITLE;
	}

	public String getSB_CONTENT() {
		return SB_CONTENT;
	}

	public void setSB_CONTENT(String sB_CONTENT) {
		SB_CONTENT = sB_CONTENT;
	}

	public int getSB_PRICE() {
		return SB_PRICE;
	}

	public void setSB_PRICE(int sB_PRICE) {
		SB_PRICE = sB_PRICE;
	}

	public Date getSB_DATE() {
		return SB_DATE;
	}

	public void setSB_DATE(Date sB_DATE) {
		SB_DATE = sB_DATE;
	}

	public int getSB_READCOUNT() {
		return SB_READCOUNT;
	}

	public void setSB_READCOUNT(int sB_READCOUNT) {
		SB_READCOUNT = sB_READCOUNT;
	}

	public String getSB_FILE() {
		return SB_FILE;
	}

	public void setSB_FILE(String sB_FILE) {
		SB_FILE = sB_FILE;
	}

	public int getSB_GRADE() {
		return SB_GRADE;
	}

	public void setSB_GRADE(int sB_GRADE) {
		SB_GRADE = sB_GRADE;
	}		
	
}
