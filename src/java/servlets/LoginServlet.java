/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain.Users;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // more secure, logout if seeing login page
        HttpSession session = request.getSession();
        session.invalidate();

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        AccountService ac = new AccountService();
        if (ac.login(userName, password) != null) {
            Users check = ac.login(userName, password);
            if (check.getIsAdmin()) {
                session.setAttribute("isAdminPage", check.getUsername());
                session.setAttribute("isAdminInventory", check.getUsername());
                session.setAttribute("userInventory", check.getUsername());
                response.sendRedirect("admin");
            } else {
                session.setAttribute("userInventory", userName);
                session.setAttribute("isUserInventory", userName);
                session.setAttribute("userSetting", userName);
                response.sendRedirect("inventory");
            }
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
}
