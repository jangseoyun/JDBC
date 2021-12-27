package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsert {

	public static void main(String[] args) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속완료");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " insert into book ";
			query += " values(seq_book_id.nextval,?,?,?,?) ";
			System.out.println(query);
			
			//문자열 쿼리문으로 만들기(작동할 수 있는 쿼리문으로 만들어줌)
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setString(1, "삼국지");
			pstmt.setString(2, "민음사");
			pstmt.setString(3, "2002-03-01");
			pstmt.setInt(4, 1);
			
			//실행
			int count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건이 저장되었습니다.");
			
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

}
