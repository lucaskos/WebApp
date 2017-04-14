package com.luke.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static String db_url = "jdbc:mysql://localhost";
	private static String db_name = "login_page";
	private static String db_user = "root";
	private static String db_password = "lucas7";
	private static Connection connection;

	private DBConnection() {
		connection = DBConnection.getConnection();
	}

	public static Connection getInstance() {
		return DBConnection.getConnection();
	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static Connection getConnection() {
		String url = "" + db_url + "/" + db_name + "?autoReconnect=true&useSSL=false";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, db_user, db_password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
}