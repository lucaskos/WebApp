package com.luke;

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
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/LoginServlet", "/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected HttpSession session;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();

		
		
		boolean result = new UserDao().checkUser(new User(username, password));
		if (result) {
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("profile.jsp");
	}
}
