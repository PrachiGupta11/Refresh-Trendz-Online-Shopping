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
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

public class LoginUser extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("deprecation")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Assuming your GetterSetterFile class has a constructor that accepts email and password
        UserDetails user = new UserDetails();
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("pass"));
        
        Configuration obj = new Configuration();
        obj.configure();

        try (SessionFactory sfactory = obj.buildSessionFactory();
             Session session = sfactory.openSession()) {

            Transaction tran = session.beginTransaction();
            
            // Query to check if the user exists with the provided email and password
            Query<?> query = session.createQuery("FROM UserDetails WHERE email = :email AND password = :password");
            query.setParameter("email", user.getEmail());
            query.setParameter("password", user.getPassword());
            
            UserDetails existingUser = (UserDetails) query.uniqueResult();
            
            tran.commit();
            
            if (existingUser != null) {
            	 // Set session attribute with the username
            	System.out.println("Login Success");
            	
                req.getSession().setAttribute("username", existingUser.getFirstName());
                req.getSession().setAttribute("userId", existingUser.getId());
                // Redirect to index.jsp
                resp.sendRedirect("index.jsp");
            } else {
                resp.setContentType("text/html");
                PrintWriter pw = resp.getWriter();
                pw.println("<h1>Login Failed</h1>");
                pw.println("<a href='Login.html'>Try Again</a>");
                pw.println(user.getEmail());
                pw.println(user.getPassword());
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occurred during login");
        }
    }
}