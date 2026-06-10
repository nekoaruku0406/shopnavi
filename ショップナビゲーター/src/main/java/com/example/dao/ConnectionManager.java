package com.example.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	// クラス図に記載されている属性（定数として定義）
	// mydb を harumicoffee に変更
	private static final String URL = "jdbc:postgresql://localhost:5432/shop_navigator_db";
	private static final String USER = "postgres";
	private static final String PASS = "1032";

	// クラス図にあるメソッド: + getConnection(): Connection
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);

	}

}