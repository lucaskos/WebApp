package com.luke.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.luke.dto.User;
import com.luke.dto.UserDao;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

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
		
		User user = (User) request.getSession().getAttribute("user");
		String sessionName = user.getUsername();
		String sessionPassword = user.getPassword();
		System.out.println(sessionName + " : : " + sessionPassword);
		if (action.equalsIgnoreCase("delete")) {
			User userByUsername = userDao.getUserByUsername(sessionName, sessionPassword);
			boolean deleteUser = userDao.deleteUser(userByUsername);
			if (deleteUser) {
				response.sendRedirect("index.jsp");
			}
		} else if (action.equalsIgnoreCase("edit")) {
			User userByUsername = userDao.getUserByUsername(sessionName, sessionPassword);
			request.setAttribute("user", userByUsername);
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile_edit.jsp");
			dispatcher.forward(request, response);

		} else {
			// Logout in profil page.
			if (request.getSession().getAttribute("user") != null) {
				request.getSession().removeAttribute("user");
			}
			response.sendRedirect("index.jsp");
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		User user = new User(username, password, email);
		
		userDao.updateUser(user);
		System.out.println(username);

	}

}
