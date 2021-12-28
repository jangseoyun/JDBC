package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorSelect {

	public static void main(String[] args) {

		// 작가 데이터 가져오기
		
		//리스트
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //select문
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속완료");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//문자열 만들기
			String query = "";
			query += " select  author_id, ";
			query += "         author_name, ";
			query += "         author_desc ";
			query += " from author ";
			System.out.println(query);
			
			//문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩은 물음표가 없으니까 생략
			
			//실행
			rs = pstmt.executeQuery();
			
			// 4.결과처리 (숫자 혹은 컬럼명으로 사용할 수 있다.)
			while(rs.next()) {
				/* 컬럼명이 바뀔 경우에는 다시 바꿔줘야함
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				*/
				
				int authorId = rs.getInt(1); //순서로 써줄 수 있다. 순서를 알아야함
				String authorName = rs.getString(2);
				String authorDesc = rs.getString(3);
				
				//리스트에 생성자를 통해 데이터 넣기
				AuthorVo vo = new AuthorVo(authorId,authorName,authorDesc);
				
				//리스트 배열에 add 순차적으로 데이터 넣기
				authorList.add(vo);
				
				//print는 한번 보여지고 소멸
				//System.out.println(authorId+"\t "+authorName+"\t "+authorDesc);
			
			}
			
			
			//리스트 출력
			for(int i =0; i<authorList.size(); i++) {
				AuthorVo authorVo = authorList.get(i);
				System.out.println(authorVo.getAuthorId() + ", " + authorVo.getAuthorName() + ", " + authorVo.getAuthorDesc());
			}
			
			//첫번째 작가 이름만 출력
			AuthorVo authorVo = authorList.get(0);
			System.out.println(authorVo.getAuthorName());
			
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
