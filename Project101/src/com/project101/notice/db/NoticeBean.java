package com.project101.notice.db;

public class NoticeBean {

	private int notice_no;
	private String notice_Q;
	private String notice_A;
	private int notice_category;
	
	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}
	public String getNotice_Q() {
		return notice_Q;
	}
	public void setNotice_Q(String notice_Q) {
		this.notice_Q = notice_Q;
	}
	public String getNotice_A() {
		return notice_A;
	}
	public void setNotice_A(String notice_A) {
		this.notice_A = notice_A;
	}
	public int getNotice_category() {
		return notice_category;
	}
	public void setNotice_category(int notice_category) {
		this.notice_category = notice_category;
	}
}
