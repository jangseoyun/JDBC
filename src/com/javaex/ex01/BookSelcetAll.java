package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelcetAll {

	public static void main(String[] args) {
		// 책+작가 데이터 가져오기
		
		//리스트 생성
		List<BookAllVo> bookallList = new ArrayList <BookAllVo>();
		
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
			//문자열 생성
			String query = "";
			
			query += " select  at.author_id, ";
			query += "         at.author_name, ";
			query += "         at.author_desc, ";
			query += "         bk.book_id, ";
			query += "         bk.title, ";
			query += "         bk.pubs, ";
			query += " 		   to_char(bk.pub_date, 'yy-mm-dd') pub_date, ";
			query += "         bk.author_id ";
			query += " from author at, book bk ";
			query += " where at.author_id = bk.author_id ";
			
			//문자열 쿼리문으로 변경
			pstmt = conn.prepareStatement(query);
			
			//바인딩 (물음표가 없기 때문에 바인딩 없음)
			
			//
			rs =  pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String bookDesc = rs.getString("author_desc");
				
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("title");
				String bookPubs = rs.getString("pubs");
				String bookDate = rs.getString("pub_date");
				int bookAt_id = rs.getInt("author_id");
			
				//BookAllVo 생성
				BookAllVo bavo = new BookAllVo(authorId,authorName,bookDesc,bookId,bookTitle,bookPubs,bookDate,bookAt_id);
				
				//리스트에 순차적으로 넣기
				bookallList.add(bavo);
				
				//System.out.println(authorId + ", " + authorName + ", " + authorDesc + ", " + bookId + ", " + bookTitle + "," + bookPubs + ", " + bookDate + ", " + bookAt_id);
			}
			
			//출력하기
			for(int i = 0; i<bookallList.size(); i++) {
				BookAllVo bookvo = bookallList.get(i);
				System.out.println(bookvo.getAuthorId()+", "+bookvo.getAuthorName()+", "+bookvo.getAuthorDesc()+", "+bookvo.getBookId()+", "+bookvo.getBookTitle()+", "+bookvo.getBookPubs()+", "+bookvo.getBookDate()+", "+bookvo.getBookAtId());
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
