package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	// ★practice 1 : book Dao 만들기

	// 필드 x

	// 생성자
	public BookDao() {}

	// 메소드 g,s x

	// 메소드 일반
	// [insert 데이터 등록]-----------------------
	public void bookInsert(String title, String pubs, String pubDate, int authorId) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 불러오기
			String query = "";
			query += " insert into book ";
			query += " values(seq_book_id.nextval,?,?,?,?) ";

			// 문자열 쿼리문으로 변경
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 등록되었습니다.(북)");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

	}

	// [update 데이터 수정]-----------------------
	public void bookUpdate(int bookId, String title, String pubs, String pubDate, int authorId) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 받아오기
			String query = "";
			query += " update book ";
			query += " set title = ?, ";
			query += "     pubs = ?, ";
			query += "     pub_date = ?, ";
			query += "     author_id = ? ";
			query += " where book_id = ? ";

			// 문자열 쿼리문으로 변경하기
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);
			pstmt.setInt(5, bookId);

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 수정되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}

	// [delete 데이터 삭제]-----------------------
	public void bookDelete(int index) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 불러오기
			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";

			// 문자열 쿼리문으로 변경
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setInt(1, index);

			// 4.결과처리
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

	}

	// [select 데이터 출력]-----------------------
	// 주의) return값, 리스트 생성, 결과처리
	public List<BookVo> bookSelect() {
		//리스트 생성
		List<BookVo> bookList = new ArrayList <BookVo>(); 

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//문자열 생성
			String query = "";
			query += " select  bk.book_id, ";
			query += "         bk.title, ";
			query += "         bk.pubs, ";
			query += "         to_char(bk.pub_date, 'yyyy-mm-dd') pub_date, ";
			query += "         bk.author_id, ";
			query += "         at.author_name, ";
			query += "         at.author_desc ";
			query += " from author at, book bk ";
			query += " where at.author_id = bk.author_id ";
			query += " order by book_id asc ";
			
			//문자열 쿼리문으로 변경
			pstmt = conn.prepareStatement(query);
			
			//바인딩 없음 (데이터출력)
			
			//실행
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String bkTitle = rs.getString("title");
				String bkPubs = rs.getString("pubs");
				String bkPubDate = rs.getString("pub_date");
				int bkAuthorId = rs.getInt("author_id");
				String atAuthorName = rs.getString("author_name");
				String atAuthorDesc = rs.getString("author_desc");
				
				//객체 생성
				BookVo vo = new BookVo(bookId,bkTitle,bkPubs,bkPubDate,bkAuthorId,atAuthorName,atAuthorDesc);
				
				//리스트에 순차적으로 넣기
				bookList.add(vo);
			}
			

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
		return bookList;

	}

	
	
	
	
}
