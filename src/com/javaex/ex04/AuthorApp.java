
//★Dao 만들기, AuthorVo 사용하기 

package com.javaex.ex04;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		//데이터를 묶어서 보내겠다.(케이스에 담아서 주는것)

		AuthorDao authorDao = new AuthorDao();
		
		//리스트 주소 정의
		List<AuthorVo> list ;
		
		//생성자를 이용해서 하나의 객체 형성
		AuthorVo vo01 = new AuthorVo("이문열","경북 영양");
		//dao -> insert 입력해줌
		authorDao.authorInsert(vo01);
		
		AuthorVo vo02 = new AuthorVo("박경리","경상남도 통영");
		authorDao.authorInsert(vo02);
		
		AuthorVo vo03 = new AuthorVo("유시민","17대 국회의원");
		authorDao.authorInsert(vo03);
		
		System.out.println("------------------------------");
		list = authorDao.authorSelect(); 
		
		for(int i = 0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAhthorDesc());
		}
		System.out.println("------------------------------");
		
		//작가수정
		//authorDao.authorUpdate(2, "박경리(수정)", "경상남도 통영(수정)");
	
		AuthorVo editVo = new AuthorVo(2, "박경리(수정)", "경상남도 통영(수정)");
		authorDao.authorUpdate(editVo);
	
		System.out.println("------------------------------");
		list = authorDao.authorSelect(); 
		
		for(int i = 0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAhthorDesc());
		}
		System.out.println("------------------------------");
		
		//작가삭제
		// 하나일 경우 묶어주지 않아도 됨, 단 2개 이상일 경우 묶어주는 것이 좋다.
		authorDao.authorDelete(1);
		
		System.out.println("------------------------------");
		list = authorDao.authorSelect(); 
		
		for(int i = 0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAhthorDesc());
		}
		System.out.println("------------------------------");
	
	}

}
