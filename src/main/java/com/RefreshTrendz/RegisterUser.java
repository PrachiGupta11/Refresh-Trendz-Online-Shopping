package com.RefreshTrendz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RegisterUser extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Configuration obj=new Configuration();
        obj.configure();
        
        SessionFactory sfactory=obj.buildSessionFactory();
        
        Session session=sfactory.openSession();
        UserDetails obj1=new UserDetails();
        obj1.setFirstName(req.getParameter("firstname"));
        obj1.setLastName(req.getParameter("lastname"));
        obj1.setEmail(req.getParameter("email"));
        obj1.setPassword(req.getParameter("pass"));
        
        Transaction tran=session.beginTransaction();
        session.save(obj1);
        tran.commit();
        session.close();
        
        sfactory.close();
        resp.sendRedirect("SignInSuccessful.html");

	}
}
