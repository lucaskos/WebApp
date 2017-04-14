package com.luke.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.luke.dto.User;
import com.luke.dto.UserDao;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private boolean userExists;
	private HttpSession session;

	public void init() {
		userDao = new UserDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}

		try {
			switch (action) {
			case "login":
				showLoginPage(request, response);
				break;
			case "edit":
				showEditPage(request, response);
				break;
			case "delte":
				showDeletePage(request, response);
				break;
			case "logout":
				showLogoutPage(request, response);
				break;
			case "register":
				showRegisterPage(request, response);
				break;
			case "insert":
				intertUser(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showRegisterPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("message", "You already logged into an account.\nPlease logout first.");
		RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
		dispatcher.forward(request, response);
	}

	private void showLogoutPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Logout from profil page.

		if (request.getSession().getAttribute("user") != null
				|| request.getSession().getAttribute("username").equals("")) {
			session.invalidate();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void showDeletePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		boolean deleteUser = userDao.deleteUser(user);
		if (deleteUser) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void showEditPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("show edit page");

		RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
		dispatcher.forward(request, response);
	}

	private void intertUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("insert user");
		User sessionUser = (User) request.getSession().getAttribute("user");
		request.setAttribute("user", sessionUser);

		String password = request.getParameter("password");
		String passwordConfirmed = request.getParameter("password-confirm");
		String email = request.getParameter("email");

		if (!password.equals(passwordConfirmed)) {
			System.out.println("passwords not correct");
			request.setAttribute("message", "Both passwords fields must be the same!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
			dispatcher.forward(request, response);
		} else {
			User updatedUser = new User(sessionUser.getUsername(), password, email);
			userDao.addUser(updatedUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void showLoginPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		userExists = userDao.checkUser(new User(username, password));
		if (userExists) {
			User userByUsername = new UserDao().getUserByUsername(username, password);
			session.setAttribute("user", userByUsername);

			// TODO delete this token after implementing Admin who get all
			// users
			// request.setAttribute("users", new UserDao().getAllUsers());

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile.jsp");
			requestDispatcher.forward(request, response);

			return;
		} else {
			request.setAttribute("message", "Unknown login, please try again");
			request.getRequestDispatcher("signin.jsp").forward(request, response);
			return;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse) doPost to
	 * change value of the user
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String username = request.getParameter("username");
		// String password = request.getParameter("password");
		// String email = request.getParameter("email");
		//
		// User user = new User(username, password, email);
		//
		// userDao.updateUser(user);
		// RequestDispatcher dispatcher =
		// request.getRequestDispatcher("profile.jsp");
		// dispatcher.forward(request, response);
		doGet(request, response);
	}

}
