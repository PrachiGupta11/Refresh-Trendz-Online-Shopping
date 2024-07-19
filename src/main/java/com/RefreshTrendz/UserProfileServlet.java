package com.RefreshTrendz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get the user ID from the session
        HttpSession session = request.getSession();
        Integer userIdObj = (Integer) session.getAttribute("userId");

        // Check if userIdObj is not null before invoking intValue()
        if (userIdObj != null) {
            int userId = userIdObj.intValue();

            // Fetch user details from the database using Hibernate
            Configuration config = new Configuration().configure();
            try (SessionFactory sessionFactory = config.buildSessionFactory();
                 Session hibernateSession = sessionFactory.openSession()) {

                Transaction transaction = hibernateSession.beginTransaction();

                UserDetails user = hibernateSession.get(UserDetails.class, userId);

                transaction.commit();

                // Display user profile information
                if (user != null) {
                    out.println("<html><head><title>User Profile</title>");
                    out.println("<style>");
                    out.println("body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; padding: 0; }");
                    out.println(".container { max-width: 800px; margin: 50px auto; padding: 20px; background-color: #fff; border-radius: 10px; box-shadow: 0 0 15px rgba(0, 0, 0, 0.1); }");
                    out.println("h1 { color: #333; margin-bottom: 20px; }");
                    out.println("p { color: #555; margin-bottom: 10px; }");
                    out.println("a { color: #007bff; text-decoration: none; transition: color 0.3s; }");
                    out.println("a:hover { color: #0056b3; }");
                    out.println(".edit-link { color: #28a745; font-weight: bold; }");
                    out.println(".edit-link:hover { color: #218838; text-decoration: underline; }");
                    out.println("</style>");
                    out.println("</head><body>");
                    out.println("<div class='container'>");
                    out.println("<h1>User Profile</h1>");
                    out.println("<p><strong>Name:</strong> " + user.getFirstName() + " " + user.getLastName() + "</p>");
                    out.println("<p><strong>Email:</strong> " + user.getEmail() + "</p>");
                    out.println("<p><a href='editprofile' class='edit-link'>Edit Profile</a></p>");
                    out.println("</div>");
                    out.println("</body></html>");
                } else {
                    out.println("<html><head><title>User Profile</title></head><body>");
                    out.println("<div class='container'>");
                    out.println("<h1>User Profile</h1>");
                    out.println("<p>Error: User not found</p>");
                    out.println("</div>");
                    out.println("</body></html>");
                }
            } catch (Exception e) {
                out.println("<html><head><title>Error</title></head><body>");
                out.println("<div class='container'>");
                out.println("<h1>Error</h1>");
                out.println("<p>An error occurred while fetching user profile</p>");
                out.println("</div>");
                out.println("</body></html>");
                e.printStackTrace();
            } finally {
                out.close();
            }
        } else {
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<div class='container'>");
            out.println("<h1>Error</h1>");
            out.println("<p>User ID not found in session</p>");
            out.println("</div>");
            out.println("</body></html>");
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
