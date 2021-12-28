package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelect {
	//책 데이터 가져오기
	public static void main(String[] args) {
		
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
			System.out.println("접속성공");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  book_id, ";
			query += "         title, ";
			query += "         pubs, ";
			query += " 		   to_char(pub_date, 'yy-mm-dd') pub_date, ";
			query += "         author_id ";
			query += " from book ";
			
			//문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩 없음. ?물음표가 없기 때문에
			
			//실행
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("title");
				String bookPubs = rs.getString("pubs");
				String bookPubdate = rs.getString("pub_date");
				int bookAt_Id = rs.getInt("author_id");
				
				//vo 메모리 생성 후 생성자를 통해서 넣기
				BookVo vo = new BookVo(bookId,bookTitle,bookPubs,bookPubdate,bookAt_Id);
				
				//각각의 vo를 리스트 배열에 순차적으로 넣기
				bookList.add(vo);
				
				//System.out.println(bookId + ", " + bookTitle + ", " + bookPubs + ", " + bookPubdate + ", " + bookAt_Id );
			}
			//출력하기
			for(int i = 0; i<bookList.size(); i++) {
				BookVo bookvo = bookList.get(i);
				System.out.println(bookvo.getBookId()+", "+ bookvo.getBookTitle()+", "+bookvo.getBookPubs()+", "+bookvo.getBookDate()+", "+bookvo.getBookAtid() );
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

	}

}
