/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain.Users;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.UserService;

/**
 *
 * @author 810585
 */
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService us = new UserService();
        String action = request.getParameter("action");
        try {
            String check = (String) session.getAttribute("isAdminPage");
            if (check == null) {
                response.sendRedirect("login");
            } else if (action != null && action.equals("edit")) {
                String selectedUser = request.getParameter("selectedUser");
                Users user = us.get(selectedUser);
                request.setAttribute("username", user.getUsername());
                request.setAttribute("password", user.getPassword());
                request.setAttribute("email", user.getEmail());
                request.setAttribute("firstName", user.getFirstName());
                request.setAttribute("lastName", user.getLastName());

                List<Users> users = null;
                try {
                    users = us.getAll();
                } catch (Exception ex) {
                    Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("users", users);
                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
            } else {
                List<Users> users = null;
                try {
                    users = us.getAll();
                } catch (Exception ex) {
                    Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("users", users);
                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        UserService us = new UserService();
        try {
            if (action.equals("add")) {
                String username = request.getParameter("username2");
                String password = request.getParameter("password2");
                String email = request.getParameter("email2");
                String firstName = request.getParameter("firstName2");
                String lastName = request.getParameter("lastName2");

                us.insert(username, password, email, firstName, lastName, true, false);
            } else if (action.equals("save")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");

                us.update(username, password, firstName, lastName, email, true, false);
            } else if (action.equals("delete")) {
                String selectedUser = request.getParameter("selectedUser");
                System.out.println(selectedUser);
                us.delete(selectedUser);
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }

        List<Users> users = null;
        try {
            users = us.getAll();
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }
}
