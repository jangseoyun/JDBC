package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorInsert {

	public static void main(String[] args) {
		
		//insert문
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기 (연결선 stream같은 개념)
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; //--> 문자열 만들기 , SQL문 준비
			
			/* query = query+"문자열"
			query = query+"insert into author";
			query = query+"values(seq_author_id.nextval, '김문열', '경북 영양')";
			*/
			
			//데이터가 변경되서 들어갈 곳은 ?(물음표)로 표시
			//★띄어쓰기 주의(안전하게 앞 뒤로 다 띄워줌)
			query += " insert into author ";
			query += " values(seq_author_id.nextval, ?, ?) ";
			System.out.println(query);
			
			//문자열 쿼리문으로 만들기(작동할 수 있는 쿼리문으로 만들어줌)
			pstmt = conn.prepareStatement(query);
			
			//바인딩 (순서 중요) 문자열, 숫자 구분
			pstmt.setString(1, "박경리"); //첫번째 물음표의 데이터
			pstmt.setString(2, "경상남도 통영"); //두번째 물음표의 데이터
			
			//실행
			int count = pstmt.executeUpdate(); //쿼리문 실행
			
			
			// 4.결과처리
			System.out.println(count+"건이 저장되었습니다.");
			
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
