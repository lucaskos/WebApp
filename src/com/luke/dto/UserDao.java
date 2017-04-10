package com.luke.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

	private Connection conn;

	public UserDao() {
		conn = DBConnection.getInstance();
	}

	/*
	 * Method to check if User of given username and password exists within db
	 */
	public boolean checkUser(User user) {
		try {
			// Two string of user that is currently checked
			String username = user.getUsername().trim();
			String password = user.getPassword().trim();

			PreparedStatement ps = conn.prepareStatement("select username, password from users");
			ResultSet rs = ps.executeQuery();
			System.err.println(username + " : " + password);
			while (rs.next()) {
				/*
				 * Getting username and password for each column in database
				 */
				String dbUsername = rs.getString("username");
				String dbPassword = rs.getString("password");
				if (dbUsername.equals(username) && dbPassword.equals(password)) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println("error in checkUser() ->	" + e.getMessage());
		}
		return false;
	}

	/*
	 * Method to add new user to database. Returns true if succeed.
	 * 
	 * @User : user
	 */
	public boolean addUser(User user) {
		if (checkUser(user)) {
			return false;
		} else {
			try {
				String addString = "insert into users(username, password, email, register_date) values (?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(addString);
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getRegistrationDate());

				int executeUpdate = ps.executeUpdate();
				if (executeUpdate > 0)
					return true;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;

	}

	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>();
		try {
			Statement statment = conn.createStatement();
			ResultSet rs = statment.executeQuery("select * from users");
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setRegistrationDate(rs.getString("register_date"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;

	}

}
