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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String sessionName = (String) request.getSession().getAttribute("username");
		String sessionPassword = (String) request.getSession().getAttribute("password");
		if (action.equalsIgnoreCase("delete")) {
			boolean deleteUser = new UserDao().deleteUser(sessionName, sessionPassword);
			if (deleteUser) {
				response.sendRedirect("index.jsp");
			}
		} else if (action.equalsIgnoreCase("edit")) {
			User userByUsername = new UserDao().getUserByUsername(sessionName, sessionPassword);
			request.setAttribute("user", userByUsername);
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile_edit.jsp");
			dispatcher.forward(request, response);
			
		} else {
			//Logout in profil page.
			if (request.getSession().getAttribute("username") != null) {
				request.getSession().removeAttribute("username");
				request.getSession().removeAttribute("password");
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
		System.out.println(username);

	}

}
