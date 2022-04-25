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
		System.out.println("1. Driver load����");
		//2. Connection
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "tickets";
		String password = "0321";
		Connection conn = DriverManager.getConnection(url, userid, password);
		System.out.println("2. Connection����");
		//3. SQL�� ��λ���(DB�� ��ȭ�Ҽ� �ִ� ��)
		Statement st = conn.createStatement();
		System.out.println("3. SQL�� ��λ���(DB�� ��ȭ�Ҽ� �ִ� ��) ����");
		//4. SQL�� ����
		String sql = " SELECT * FROM MEMBER";
		ResultSet rs = st.executeQuery(sql);
		System.out.println("4. SQL�� ���� �����ϰ� data�� �޸𸮷� ����");
		while(rs.next()) {
			System.out.println("�μ���ȣ : "+rs.getString(1));
			System.out.println("�ο��� : "+rs.getString(2));
			System.out.println("------------------");
			
		}
		//5. DB��������
		conn.close();
		System.out.println("5. DB�������� ����");
	}
}
