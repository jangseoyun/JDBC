package com.javaex.ex03;

public class AuthorVo {
	
	//필드
	private int authorId;
	private String authorName;
	private String ahthorDesc;

	
	//생성자
	public AuthorVo() {};

	public AuthorVo(String authorName, String ahthorDesc) {
		super();
		this.authorName = authorName;
		this.ahthorDesc = ahthorDesc;
	}

	public AuthorVo(int authorId, String authorName, String ahthorDesc) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.ahthorDesc = ahthorDesc;
	}
	//메소드g,s

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

	public String getAhthorDesc() {
		return ahthorDesc;
	}

	public void setAhthorDesc(String ahthorDesc) {
		this.ahthorDesc = ahthorDesc;
	}

	//메소드 일반
	@Override
	public String toString() {
		return "AuthorVo [authorId=" + authorId + ", authorName=" + authorName + ", ahthorDesc=" + ahthorDesc + "]";
	}
}
