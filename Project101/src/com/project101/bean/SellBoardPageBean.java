package com.project101.bean;

import java.util.List;

public class SellBoardPageBean {
	private int page = 1;
	private int limit = 10;
	private int listcount;
	private int maxpage;
	private int startpage;
	private int endpage;
	private List<SellBoardBean> boardList;
	private String searchItem = "";
	private String searchWord = "";

	public SellBoardPageBean() {
		super();
	}

	public String getSearchItem() {
		return searchItem;
	}

	public void setSearchItem(String searchItem) {
		this.searchItem = searchItem;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public List<SellBoardBean> getboardList() {
		return boardList;
	}

	public void setboardList(List<SellBoardBean> boardList) {
		this.boardList = boardList;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getListcount() {
		return listcount;
	}

	public void setListcount(int listcount) {
		this.listcount = listcount;
	}

	public int getMaxpage() {
		return maxpage;
	}

	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
	}

	public int getStartpage() {
		return startpage;
	}

	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}

	public int getEndpage() {
		return endpage;
	}

	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}

}
