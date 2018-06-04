package com.project101.bean;

public class CommentBean2 {
	private int COMMENT_NO;
	private int BOARD_NO;
	private String BOARD_NAME = "SELL_BOARD";
	private String COMMENT_WRITER;
	private String COMMENT_CONTENT;
	private String COMMENT_DATE;

	public CommentBean2() {
		super();
	}

	public int getCOMMENT_NO() {
		return COMMENT_NO;
	}

	public void setCOMMENT_NO(int cOMMENT_NO) {
		COMMENT_NO = cOMMENT_NO;
	}

	public int getBOARD_NO() {
		return BOARD_NO;
	}

	public void setBOARD_NO(int bOARD_NO) {
		BOARD_NO = bOARD_NO;
	}

	public String getBOARD_NAME() {
		return BOARD_NAME;
	}

	public void setBOARD_NAME(String bOARD_NAME) {
		BOARD_NAME = bOARD_NAME;
	}

	public String getCOMMENT_CONTENT() {
		return COMMENT_CONTENT;
	}

	public void setCOMMENT_CONTENT(String cOMMENT_CONTENT) {
		COMMENT_CONTENT = cOMMENT_CONTENT;
	}

	public String getCOMMENT_WRITER() {
		return COMMENT_WRITER;
	}

	public void setCOMMENT_WRITER(String cOMMENT_WRITER) {
		COMMENT_WRITER = cOMMENT_WRITER;
	}

	public String getCOMMENT_DATE() {
		return COMMENT_DATE;
	}

	public void setCOMMENT_DATE(String cOMMENT_DATE) {
		COMMENT_DATE = cOMMENT_DATE;
	}

}
