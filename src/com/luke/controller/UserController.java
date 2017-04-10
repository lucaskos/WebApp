package com.luke.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String session_name = (String) request.getSession().getAttribute("username");
		String session_pass = (String) request.getSession().getAttribute("password");
		if(action.equalsIgnoreCase("delete")) {
			boolean deleteUser = new UserDao().deleteUser(session_name, session_pass);
			if(deleteUser) {
				response.sendRedirect("index.jsp");
			}
		} 
	}

}
