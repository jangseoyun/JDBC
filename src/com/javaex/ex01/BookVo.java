package com.javaex.ex01;

public class BookVo {
	
	//필드
	private int bookId;
	private String bookTitle;
	private String bookPubs;
	private String bookDate;
	private int bookAtid;
	
	//생성자
	public BookVo(int bookId, String bookTitle, String bookPubs, String bookDate, int bookAtid) {
		super();
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookPubs = bookPubs;
		this.bookDate = bookDate;
		this.bookAtid = bookAtid;
	}

	//메소드 g,s
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookPubs() {
		return bookPubs;
	}

	public void setBookPubs(String bookPubs) {
		this.bookPubs = bookPubs;
	}

	public String getBookDate() {
		return bookDate;
	}

	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}

	public int getBookAtid() {
		return bookAtid;
	}

	public void setBookAtid(int bookAtid) {
		this.bookAtid = bookAtid;
	}
	
	//메소드 일반
	@Override
	public String toString() {
		return "BookVo [bookId=" + bookId + ", bookTitle=" + bookTitle + ", bookPubs=" + bookPubs + ", bookDate="
				+ bookDate + ", bookAtid=" + bookAtid + "]";
	}
}
