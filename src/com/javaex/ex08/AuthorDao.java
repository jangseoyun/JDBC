
//★Dao 만들기, AuthorVo 사용하기 , 공통변수 + 메소드

package com.javaex.ex08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
	// 필드
	// 0. import java.sql.*;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// 생성자
	public AuthorDao() {
	}

	// 메소드 g,s

	// 메소드 일반
	private void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error:드라이버 로딩 실패 -" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	private void close() {
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

	// insert---------------------------------------------------
	public void authorInsert(AuthorVo authorVo) {

		// 로딩, connection
		this.getConnection(); // this. 생략가능

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 생성
			String query = ""; // --> 문자열 만들기 , SQL문 준비
			query += " insert into author ";
			query += " values(seq_author_id.nextval, ?, ?) ";
			// System.out.println(query);

			// 문자열 쿼리문으로 만들기(작동할 수 있는 쿼리문으로 만들어줌)
			pstmt = conn.prepareStatement(query);

			// ★바인딩 (순서 중요) 문자열, 숫자 구분
			// 메인에서 넣어줄 컬럼 파라미터
			pstmt.setString(1, authorVo.getAuthorName()); // 첫번째 물음표의 데이터
			pstmt.setString(2, authorVo.getAhthorDesc()); // 두번째 물음표의 데이터

			// 실행
			int count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			System.out.println(count + "건이 등록되었습니다.(author)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();// this 생략가능
	}

	// delete-----------------------------------------------
	public void authorDelete(int index) {

		// 로딩, connection
		this.getConnection(); // this. 생략가능

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " delete from author ";
			query += " where author_id = ? ";
			// System.out.println(query);

			// 연결
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setInt(1, index);

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 자원정리
		this.close();

	}

	// update------------------------------------------------------
	public void authorUpdate(AuthorVo authorVo) {

		// 로딩, connection
		this.getConnection(); // this. 생략가능

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 만들기
			String query = "";
			query += " update author ";
			query += " set author_name = ?, ";
			query += "     author_desc = ? ";
			query += " where author_id = ? ";
			// System.out.println(query);

			// 문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩(파라미터 순서대로 쓰지 않아도 된다.)
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAhthorDesc());
			pstmt.setInt(3, authorVo.getAuthorId());

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 수정되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		// 자원정리
		this.close();
	}

	// select 작가 리스트 가져오기--------------------------------------
	public List<AuthorVo> authorSelect() {
		// 리스트 생성
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();

		// 로딩, connection
		this.getConnection(); // this. 생략가능
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  author_id, ";
			query += "         author_name, ";
			query += "         author_desc ";
			query += " from author ";
			query += " order by author_id asc ";
			// System.out.println(query);

			// 문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩은 물음표가 없으니까 생략

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				// 리스트에 생성자를 통해 데이터 넣기
				AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);

				// 리스트 배열에 add 순차적으로 데이터 넣기
				authorList.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		// 5. 자원정리
		this.close();

		return authorList;
	}

}
