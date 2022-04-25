package com.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionTest4 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//1. Driver load
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("1. Driver load성공");
		//2. Connection
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "tickets";
		String password = "0321";
		Connection conn = DriverManager.getConnection(url, userid, password);
		System.out.println("2. Connection성공");
		//3. SQL문 통로생성(DB와 대화할수 있는 길)
		Statement st = conn.createStatement();
		System.out.println("3. SQL문 통로생성(DB와 대화할수 있는 길) 성공");
		//4. SQL문 실행
		String sql = " SELECT * FROM MEMBER";
		ResultSet rs = st.executeQuery(sql);
		System.out.println("4. SQL문 실행 성공하고 data가 메모리로 들어옴");
		while(rs.next()) {
			System.out.println("부서번호 : "+rs.getString(1));
			System.out.println("인원수 : "+rs.getString(2));
			System.out.println("------------------");
			
		}
		//5. DB연결해제
		conn.close();
		System.out.println("5. DB연결해제 성공");
	}
}
