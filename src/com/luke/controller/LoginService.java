package com.luke.controller;

import com.luke.dto.User;
import com.luke.dto.UserDao;

public class LoginService {

	public LoginService() {
	}

	/*
	 * @username, @password
	 * We use UserDao class to check whether the user exists in the database. @returns boolean
	 */
	public boolean authenticateUser(String username, String password) {
		User user = new User(username.toString(), password);
		System.out.println("LoginService -> authenticateUser() : " + new UserDao().checkUser(user));
		if (new UserDao().checkUser(user) != true) {
			return false;
		} else {
			return true;
		}
	}
}
