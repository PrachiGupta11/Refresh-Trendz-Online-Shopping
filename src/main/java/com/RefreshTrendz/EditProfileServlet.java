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


public class EditProfileServlet extends HttpServlet {
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

                // Display user profile information in an editable form
                if (user != null) {
                    out.println("<html><head><title>Edit Profile</title>");
                    out.println("<style>");
                    out.println("body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; padding: 0; }");
                    out.println(".container { max-width: 800px; margin: 50px auto; padding: 20px; background-color: #fff; border-radius: 10px; box-shadow: 0 0 15px rgba(0, 0, 0, 0.1); }");
                    out.println("h1 { color: #333; margin-bottom: 20px; }");
                    out.println("form { margin-bottom: 20px; }");
                    out.println("label { display: block; margin-bottom: 5px; }");
                    out.println("input[type='text'], input[type='email'], input[type='password'] { width: 100%; padding: 10px; margin-bottom: 10px; border: 1px solid #ccc; border-radius: 5px; }");
                    out.println("input[type='submit'] { background-color: #007bff; color: #fff; border: none; padding: 10px 20px; border-radius: 5px; cursor: pointer; }");
                    out.println("</style>");
                    out.println("</head><body>");
                    out.println("<div class='container'>");
                    out.println("<h1>Edit Profile</h1>");
                    out.println("<form method='post'>");
                    out.println("<label for='firstName'>First Name:</label>");
                    out.println("<input type='text' id='firstName' name='firstName' value='" + user.getFirstName() + "' required>");
                    out.println("<label for='lastName'>Last Name:</label>");
                    out.println("<input type='text' id='lastName' name='lastName' value='" + user.getLastName() + "' required>");
                    out.println("<label for='email'>Email:</label>");
                    out.println("<input type='email' id='email' name='email' value='" + user.getEmail() + "' required>");
                    out.println("<label for='password'>Password:</label>");
                    out.println("<input type='password' id='password' name='password' required>");
                    out.println("<input type='submit' value='Save Changes'>");
                    out.println("</form>");
                    out.println("</div>");
                    out.println("</body></html>");
                } else {
                    out.println("<html><head><title>Edit Profile</title></head><body>");
                    out.println("<div class='container'>");
                    out.println("<h1>Edit Profile</h1>");
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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Get the user ID from the session
        HttpSession session = request.getSession();
        Integer userIdObj = (Integer) session.getAttribute("userId");

        // Check if userIdObj is not null before invoking intValue()
        if (userIdObj != null) {
            int userId = userIdObj.intValue();

            // Update user details in the database using Hibernate
            Configuration config = new Configuration().configure();
            try (SessionFactory sessionFactory = config.buildSessionFactory();
                 Session hibernateSession = sessionFactory.openSession()) {

                Transaction transaction = hibernateSession.beginTransaction();

                UserDetails user = hibernateSession.get(UserDetails.class, userId);

                if (user != null) {
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    user.setPassword(password);

                    hibernateSession.update(user);
                    transaction.commit();

                    
                    response.sendRedirect("ProfileUpdated.html");
                
                } else {
                    out.println("<html><head><title>Edit Profile</title></head><body>");
                    out.println("<div class='container'>");
                    out.println("<h1>Error</h1>");
                    out.println("<p>User not found</p>");
                    out.println("<p><a href='profile'>Go back to Profile</a></p>");
                    out.println("</div>");
                    out.println("</body></html>");
                }
            } catch (Exception e) {
                out.println("<html><head><title>Error</title></head><body>");
                out.println("<div class='container'>");
                out.println("<h1>Error</h1>");
                out.println("<p>An error occurred while updating user profile</p>");
                out.println("<p><a href='profile'>Go back to Profile</a></p>");
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
            out.println("<p><a href='profile'>Go back to Profile</a></p>");
            out.println("</div>");
            out.println("</body></html>");
            out.close();
        }
    }
}
