package com.luke.controller;

import com.luke.dto.User;
import com.luke.dto.UserDao;

public class RegisterService {

	public RegisterService(String username, String password, String email, String date) {
		User user = new User(username, password, email, date);
		UserDao userDao = new UserDao();
		boolean checkUser = userDao.checkUser(user);
		if (checkUser == false) {
			System.err.println("User doesn't exist! userDao -> checkUser() returns true");
			userDao.addUser(user);
		} else {
			
			System.err.println("User exists! userDao -> checkUser() return false");
		}
	}

}
