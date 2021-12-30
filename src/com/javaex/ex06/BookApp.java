package com.javaex.ex06;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		//dao 메모리에 생성
		BookDao bookDao = new BookDao();
		
		//리스트 주소 정의
		List<BookVo> list;
		
		//Vo를 생성하여 각각 인스턴스화 시켜준다.
		//생성자로 추가
		BookVo vo01 = new BookVo("북타이틀","펍스","22-01-01",2);
		bookDao.bookInsert(vo01);
		
		System.out.println("-------------------------------");
	
		list = bookDao.bookSelect();
		
		for (int i = 0; i <list.size(); i++) {
			BookVo vo = list.get(i);
			System.out.println(vo.getBookId() + ", " + vo.getTitle() + ", " + vo.getPubs() + ", " + vo.getPubdate()
					+ ", " + vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());

		}
		
		System.out.println("-------------------------------");

	}

}
