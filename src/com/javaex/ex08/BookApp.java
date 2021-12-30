package com.javaex.ex08;

import java.util.List;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {

		// 리스트 주소 정의
		List<BookVo> list;

		// 1)작가 dao
		AuthorDao authorDao = new AuthorDao();

		// 2)작가 추가
		AuthorVo atvo01 = new AuthorVo("이문열");
		authorDao.authorInsert(atvo01);

		AuthorVo atvo02 = new AuthorVo("박경리");
		authorDao.authorInsert(atvo02);

		AuthorVo atvo03 = new AuthorVo("이고잉");
		authorDao.authorInsert(atvo03);

		AuthorVo atvo04 = new AuthorVo("기안84");
		authorDao.authorInsert(atvo04);

		AuthorVo atvo05 = new AuthorVo("강풀");
		authorDao.authorInsert(atvo05);

		AuthorVo atvo06 = new AuthorVo("김영하");
		authorDao.authorInsert(atvo06);

		// 3)책 dao
		BookDao bookDao = new BookDao();

		// 4)책 데이터 추가
		BookVo bkvo01 = new BookVo("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		bookDao.bookInsert(bkvo01);

		BookVo bkvo02 = new BookVo("삼국지", "민음사", "2002-03-01", 1);
		bookDao.bookInsert(bkvo02);

		BookVo bkvo03 = new BookVo("토지", "마로니에북스", "2012-08-15", 2);
		bookDao.bookInsert(bkvo03);

		BookVo bkvo04 = new BookVo("자바프로그래밍 입문", "위키북스", "2015-04-01", 3);
		bookDao.bookInsert(bkvo04);

		BookVo bkvo05 = new BookVo("패션왕", "중앙북스(books)", "2012-02-22", 4);
		bookDao.bookInsert(bkvo05);

		BookVo bkvo06 = new BookVo("순정만화", "재미주의", "2011-08-03", 5);
		bookDao.bookInsert(bkvo06);

		BookVo bkvo07 = new BookVo("오직두사람", "문학동네", "2017-05-04", 6);
		bookDao.bookInsert(bkvo07);

		BookVo bkvo08 = new BookVo("26년", "재미주의", "2012-02-04", 5);
		bookDao.bookInsert(bkvo08);

		// 4-1) 스캐너로 입력받기
		Scanner sc = new Scanner(System.in);
		System.out.print("검색어를 입력하세요 :");
		String input = sc.nextLine();

		// 5) 책 검색메소드 만들어서
		list = bookDao.bookScanner(input);

		// 6)호출 출력
		System.out.println("-------------------------------");

		for (int i = 0; i < list.size(); i++) {
			BookVo vo = list.get(i);
			System.out.println(vo.getBookId() + ", " + vo.getTitle() + ", " + vo.getPubs() + ", " + vo.getPubdate()
					+ ", " + vo.getAuthorName());

		}
		
		System.out.println("-------------------------------");

		/*
		 * //Vo를 생성하여 각각 인스턴스화 시켜준다. //생성자로 추가 BookVo vo01 = new
		 * BookVo("북타이틀","펍스","22-01-01",2); bookDao.bookInsert(vo01);
		 * 
		 * System.out.println("-------------------------------");
		 * 
		 * list = bookDao.bookSelect();
		 * 
		 * for (int i = 0; i <list.size(); i++) { BookVo vo = list.get(i);
		 * System.out.println(vo.getBookId() + ", " + vo.getTitle() + ", " +
		 * vo.getPubs() + ", " + vo.getPubdate() + ", " + vo.getAuthorId() + ", " +
		 * vo.getAuthorName() + ", " + vo.getAuthorDesc());
		 * 
		 * } System.out.println("-------------------------------");
		 */

		sc.close();
	}

}
