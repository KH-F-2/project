package com.project101.board.sell.db;

import java.util.Date;

public class SellBoardBean {
	private int SB_NO;
	private String SB_WRITER;
	private Date SB_REDITDATE;
	private String SB_TITLE;
	private int SB_CATEGORY;
	private String SB_CONTENT;
	private int SB_PRICE;
	private int SB_LATITUDE;
	private int SB_LOGITUDE;
	private String SB_PICTURE;
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

	public Date getSB_REDITDATE() {
		return SB_REDITDATE;
	}

	public void setSB_REDITDATE(Date sB_REDITDATE) {
		SB_REDITDATE = sB_REDITDATE;
	}

	public String getSB_TITLE() {
		return SB_TITLE;
	}

	public void setSB_TITLE(String sB_TITLE) {
		SB_TITLE = sB_TITLE;
	}

	public int getSB_CATEGORY() {
		return SB_CATEGORY;
	}

	public void setSB_CATEGORY(int sB_CATEGORY) {
		SB_CATEGORY = sB_CATEGORY;
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

	public int getSB_LATITUDE() {
		return SB_LATITUDE;
	}

	public void setSB_LATITUDE(int sB_LATITUDE) {
		SB_LATITUDE = sB_LATITUDE;
	}

	public int getSB_LOGITUDE() {
		return SB_LOGITUDE;
	}

	public void setSB_LOGITUDE(int sB_LOGITUDE) {
		SB_LOGITUDE = sB_LOGITUDE;
	}

	public String getSB_PICTURE() {
		return SB_PICTURE;
	}

	public void setSB_PICTURE(String sB_PICTURE) {
		SB_PICTURE = sB_PICTURE;
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
