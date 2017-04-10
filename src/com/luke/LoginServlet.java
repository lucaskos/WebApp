package com.luke;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.luke.controller.LoginService;
import com.luke.dto.DBConnection;
import com.luke.dto.User;
import com.luke.dto.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/LoginServlet", "/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();

		// TODO sprawdzanie czy taki uzytkownik istnieje!
		// przekierowanie do strony
		LoginService loginService = new LoginService();

		boolean results = loginService.authenticateUser(username, password);
		if (results) {
			session.setAttribute("username", username);
			session.setAttribute("password", password);

			// TODO delete this token after implementing Admin who get all users
			// request.setAttribute("users", new UserDao().getAllUsers());

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile.jsp");

			requestDispatcher.forward(request, response);
			return;
		} else {
			response.sendRedirect("signin.jsp");
			return;
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			response.sendRedirect("profile.jsp");
	}
}
