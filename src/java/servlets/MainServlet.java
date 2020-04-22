/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String username = request.getParameter("username1");
        String password = request.getParameter("password1");
        String email = request.getParameter("email1");
        String firstName = request.getParameter("firstName1");
        String lastName = request.getParameter("lastName1");

        UserService us = new UserService();
        try {
            if (action.equals("register")) {
                System.out.println(username);
                us.insert(username, password, email, firstName, lastName, true, false);
            } else if (action.equals("save")) {
                us.update(username, password, firstName, lastName, email, true, false);
            } else if (action.equals("delete")) {
                String selectedUser = request.getParameter("selectedUser");
                System.out.println(selectedUser);
                us.delete(selectedUser);
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/successful.jsp").forward(request, response);
    }
}
