/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain.Categories;
import domain.Items;
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
import services.Inventory;
import services.UserService;

/**
 *
 * @author 810585
 */
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        UserService us = new UserService();
        String owner = (String) session.getAttribute("userSetting");
        try {
            Users user = us.get(owner);
            request.setAttribute("username23", user.getUsername());
            request.setAttribute("email23", user.getEmail());
            request.setAttribute("firstName23", user.getFirstName());
            request.setAttribute("lastName23", user.getLastName());
            request.setAttribute("username24", user.getUsername());
            request.setAttribute("email24", user.getEmail());
            request.setAttribute("firstName24", user.getFirstName());
            request.setAttribute("lastName24", user.getLastName());
            
            getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        UserService us = new UserService();
        try {
            if (action.equals("save")) {
                String username = request.getParameter("username23");
                String email = request.getParameter("email23");
                String firstName = request.getParameter("firstName23");
                String lastName = request.getParameter("lastName23");
                System.out.println(email);
                us.update(username, "password", firstName, lastName, email, true, false);
                response.sendRedirect("main");
            } else if (action.equals("deactivate")) {
                String username = request.getParameter("username24");
                String email = request.getParameter("email24");
                String firstName = request.getParameter("firstName24");
                String lastName = request.getParameter("lastName24");
                System.out.println(username);
                us.update(username, "password", firstName, lastName, email, false, false);
                session.invalidate();
                response.sendRedirect("login");
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }

    }
}
