package com.project101.board.purchase.db;

import java.util.Date;

public class PurchaseBoardBean {
	private String id;
	private String password;
	private String writer;
	private int age;
	private String gender;
	private String email;
	private String content;
	private String title;
	private int num;
	private String subject;
	private String file;
	private Date date;
	private int readcount;
	public PurchaseBoardBean() {
		
	}
	
	public PurchaseBoardBean(String id, String password, String writer, int age, String gender,
			String email, String content, String title, int num, String subject, String file,
				Date date, int readcount) {
		this.id = id;
		this.password = password;
		this.writer = writer;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.content= content;
		this.title = title;
		this.num = num;
		this.subject = subject;
		this.file = file;
		this.date = date;
		this.readcount = readcount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getwriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getreadcount() {
		return readcount;
	}

	public void setreadcount(int readcount) {
		this.readcount = readcount;
	}
}