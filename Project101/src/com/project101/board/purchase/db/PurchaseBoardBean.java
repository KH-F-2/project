package com.project101.board.purchase.db;

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
	private int date;
	private int pb_readcount;
	public PurchaseBoardBean() {
		
	}
	
	public PurchaseBoardBean(String id, String password, String writer, int age, String gender,
			String email, String content, String title, int num, String subject, String file,
				int date, int pb_readcount) {
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
		this.pb_readcount = pb_readcount;
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

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getPb_readcount() {
		return pb_readcount;
	}

	public void setPb_readcount(int pb_readcount) {
		this.pb_readcount = pb_readcount;
	}
}
