package com.project101.bean;

import java.sql.Date;

public class SellBoardBean {
	private int SB_NO;
	private String SB_WRITER;
	private Date SB_PDATE;
	private Date SB_MDATE;
	private String SB_TITLE;
	private String SB_CONTENT;
	private int SB_PRICE;
	private Date SB_DATE;
	private int SB_READCOUNT;

	public SellBoardBean() {
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

	public Date getSB_PDATE() {
		return SB_PDATE;
	}

	public void setSB_PDATE(Date sB_PDATE) {
		SB_PDATE = sB_PDATE;
	}

	public Date getSB_MDATE() {
		return SB_MDATE;
	}

	public void setSB_MDATE(Date sB_MDATE) {
		SB_MDATE = sB_MDATE;
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

}
