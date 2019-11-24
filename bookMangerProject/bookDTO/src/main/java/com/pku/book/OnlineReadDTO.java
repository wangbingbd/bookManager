package com.pku.book;

import java.io.Serializable;

public class OnlineReadDTO implements Serializable{

	private static final long serialVersionUID = 8528073879474437916L;
	
	/**
	 * 0 当前页 1 上一页  2 下一页  3 首页   4 尾页
	 */
	private Integer forword;
	
	private String bookId; //图书主键

	public Integer getForword() {
		return forword;
	}

	public void setForword(Integer forword) {
		this.forword = forword;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	@Override
	public String toString() {
		return "OnlineReadDTO [forword=" + forword + ", bookId=" + bookId + "]";
	}
}

