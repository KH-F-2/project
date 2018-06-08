package com.project101.bean;

import java.sql.Date;

public class SellBoardBean {
	private int SB_NO;
	private String SB_WRITER;
	private Date SB_PURCHASE_DATE;
	private String SB_TITLE;
	private String SB_CONTENT;
	private int SB_PRICE;
	private Date SB_DATE;
	private int SB_READCOUNT;
	private int SB_LAT;
	private int SB_LNG;
	private int SB_STATE;
	private int SB_CATEGORY;
	private String SB_HASHTAG;
	private String IMAGE_URL;

	public String getIMAGE_URL() {
		return IMAGE_URL;
	}

	public void setIMAGE_URL(String iMAGE_URL) {
		IMAGE_URL = iMAGE_URL;
	}

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

	public Date getSB_PURCHASE_DATE() {
		return SB_PURCHASE_DATE;
	}

	public void setSB_PURCHASE_DATE(Date sB_PURCHASE_DATE) {
		SB_PURCHASE_DATE = sB_PURCHASE_DATE;
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

	public int getSB_LAT() {
		return SB_LAT;
	}

	public void setSB_LAT(int sB_LAT) {
		SB_LAT = sB_LAT;
	}

	public int getSB_LNG() {
		return SB_LNG;
	}

	public void setSB_LNG(int sB_LNG) {
		SB_LNG = sB_LNG;
	}

	public int getSB_STATE() {
		return SB_STATE;
	}

	public void setSB_STATE(int sB_STATE) {
		SB_STATE = sB_STATE;
	}
	
	public int getSB_CATEGORY() {
		return SB_CATEGORY;
	}

	public void setSB_CATEGORY(int sB_CATEGORY) {
		SB_CATEGORY = sB_CATEGORY;
	}

	public String getSB_HASHTAG() {
		return SB_HASHTAG;
	}

	public void setSB_HASHTAG(String sB_HASHTAG) {
		SB_HASHTAG = sB_HASHTAG;
	}

	
}
