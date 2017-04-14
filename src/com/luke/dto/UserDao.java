package com.luke.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
 * This DAO Class provides CRUD database operations for the user table in the database.
 */
public class UserDao {

	private Connection conn;

	public UserDao() {
		conn = DBConnection.getInstance();
	}

	private void disconnect() throws SQLException {
		if (!conn.isClosed() && conn != null) {
			conn.close();
		}
	}

	private void connect() throws SQLException {
		if (conn.isClosed() || conn == null) {
			conn = DBConnection.getInstance();
		}
	}

	/*
	 * Method to check if User of given username and password exists within db
	 * takes @User as parameter returns boolean TRUE if user exists
	 */
	public boolean checkUser(User user) {
		String username = user.getUsername().trim();
		String password = user.getPassword().trim();
		try {
			connect();

			PreparedStatement ps = conn.prepareStatement("select username, password from users");
			ResultSet rs = ps.executeQuery();

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
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
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
				connect();
				String addString = "insert into users(username, password, email, register_date) values (?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(addString);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String registrationDate = df.format(Calendar.getInstance().getTime());
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getEmail());
				ps.setString(4, registrationDate);

				int executeUpdate = ps.executeUpdate();
				if (executeUpdate > 0)
					return true;
				disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/*
	 * Returns list of all users from Database
	 */
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>();
		try {
			connect();
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
				rs.close();
				disconnect();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;

	}

	/*
	 * returns true if user deleted
	 */
	public boolean deleteUser(User user) {
		try {
			connect();
			String deleteString = "delete from users where username=? and password=?";
			PreparedStatement ps = conn.prepareStatement(deleteString);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());

			int executeUpdate = ps.executeUpdate();
			if (executeUpdate != 1) {
				return false;
			}
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * Returns given user if params @username and @password are correct.
	 * otherwise returns false
	 */
	public User getUserByUsername(String username, String password) {
		User user = new User();
		String userByUsernameSQL = "select * from users where username=? and password=?";
		try {
			connect();
			PreparedStatement ps = conn.prepareStatement(userByUsernameSQL);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user.setId(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setRegistrationDate(rs.getString("register_date"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean updateUser(User user) {
		String updateSQL = "update users set password=?, email=? where username=?";

		try {
			connect();
			PreparedStatement ps = conn.prepareStatement(updateSQL);

			ps.setString(1, user.getPassword());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getUsername());

			int rowUpdated = ps.executeUpdate();
			if (rowUpdated > 0)
				return true;
			ps.close();
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
