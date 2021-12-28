package com.javaex.ex01;

public class BookAllVo {
	// 필드
	private int authorId;
	private String authorName;
	private String authorDesc;
	private int bookId;
	private String bookTitle;
	private String bookPubs;
	private String bookDate;
	private int bookAtId;

	// 생성자
	public BookAllVo(int authorId, String authorName, String authorDesc, int bookId, String bookTitle, String bookPubs,
			String bookDate, int bookAtId) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookPubs = bookPubs;
		this.bookDate = bookDate;
		this.bookAtId = bookAtId;
	}

	// 메소드 g,s
	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorDesc() {
		return authorDesc;
	}

	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}

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

	public int getBookAtId() {
		return bookAtId;
	}

	public void setBookAtId(int bookAtId) {
		this.bookAtId = bookAtId;
	}

	// 메소드 일반
	@Override
	public String toString() {
		return "BookAllVo [authorId=" + authorId + ", authorName=" + authorName + ", authorDesc=" + authorDesc
				+ ", bookId=" + bookId + ", bookTitle=" + bookTitle + ", bookPubs=" + bookPubs + ", bookDate="
				+ bookDate + ", bookAtId=" + bookAtId + "]";
	}
}
