package com.RefreshTrendz;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutUser extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the session
        request.getSession().invalidate();
        System.out.println("session invalidated");        // Redirect to login page or any other appropriate page
        response.sendRedirect("index.jsp");
    }
}