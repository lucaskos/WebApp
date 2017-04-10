package com.luke;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.luke.controller.RegisterService;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet({ "/registerUser", "/RegisterUser" })
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password_confirm = request.getParameter("password-confirmed");
		String email = request.getParameter("email");
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(currentDate);
		
		
		/*
		 * TODO form validation inside servlet
		 * creating new user for the db
		 * forwarding to the success page
		 */
		if(!password.equalsIgnoreCase(password_confirm)) {
			System.out.println("error");
			response.sendRedirect("register.jsp");
		} else {
			RegisterService rs = new RegisterService(username, password, email, date);
		}
		
		
		
		System.out.println(username + " : " + password + " : " + password_confirm + " : " + email + " : " + date);
	}

}
