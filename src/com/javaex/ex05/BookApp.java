package com.javaex.ex05;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {

		// dao 생성
		BookDao bookDao = new BookDao();

		// 리스트 담을 주소 선언
		List<BookVo> bookList;

		// book 등록
		//#1
		bookDao.bookInsert("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);

		// 출력
		System.out.println("-------------------------------");
		bookList = bookDao.bookSelect();

		for (int i = 0; i < bookList.size(); i++) {
			BookVo vo = bookList.get(i);
			System.out.println(vo.getBookId() + ", " + vo.getTitle() + ", " + vo.getPubs() + ", " + vo.getPubdate()
					+ ", " + vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());

		}

		System.out.println("-------------------------------");

		// book 수정
		//bookDao.bookUpdate(0, null, null, null, 0);

		// book 삭제
		bookDao.bookDelete(8);
		
		System.out.println("-------------------------------");
		bookList = bookDao.bookSelect();

		for (int i = 0; i < bookList.size(); i++) {
			BookVo vo = bookList.get(i);
			System.out.println(vo.getBookId() + ", " + vo.getTitle() + ", " + vo.getPubs() + ", " + vo.getPubdate()
					+ ", " + vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());

		}

		System.out.println("-------------------------------");

	}

}
