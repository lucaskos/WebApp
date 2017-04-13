package com.luke.dto;

import java.util.Date;

public class User {
	private int id;
	private String username, password;
	private String email;
	private String registrationDate;

	/*
	 * contructor for login in already existing user takes 2 parameters:
	 * 
	 * @username, @password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	/*
	 * constructor for registering new user. takes arguments:
	 * 
	 * @username, @password, @email, @registrationDate
	 */
	public User(String username, String password, String email, String registrationDate) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.registrationDate = registrationDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + username + ", password=" + password + ", email=" + email
				+ ", registrationDate=" + registrationDate + "]";
	}

}
