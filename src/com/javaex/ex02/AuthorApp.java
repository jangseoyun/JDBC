package com.javaex.ex02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		//dao 생성 
		AuthorDao authorDao = new AuthorDao();
		
		//작가등록
		authorDao.authorInsert("이문열","경북 영양");
		
		//작가등록
		authorDao.authorInsert("박경리","경상남도 통영");
		
		//작가등록
		authorDao.authorInsert("유시민","17대 국회의원");
		
		//데이터 불러오기
		//값을 담아줄 주소 설정 후 return값 넣어줌
		List<AuthorVo> List = authorDao.authorSelect();
		
		for(int i = 0; i<List.size(); i++) {
			AuthorVo vo = List.get(i);
			System.out.println(vo.getAuthorId()+", "+vo.getAuthorName()+", "+vo.getAhthorDesc());
		}
		
		//작가수정
		//authorDao.authorUpdate(2, "박경리(수정)", "경상남도 통영(수정)");
		
		//작가삭제
		//authorDao.authorDelete(1);

	}

}
