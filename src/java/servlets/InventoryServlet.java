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

public class InventoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Inventory inv = new Inventory();
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String owner = (String) session.getAttribute("userInventory");
        UserService us = new UserService();
        try {
            Users sessionUser = us.get(owner);
            if (sessionUser.getIsAdmin()) {
                if (action != null && action.equals("edit")) {
                    String selectedItem = request.getParameter("selectedItem");
                    int itemID = Integer.parseInt(selectedItem);
                    Items item = inv.get(itemID);
                    request.setAttribute("categories", item.getCategory());
                    request.setAttribute("itemName", item.getItemName());
                    request.setAttribute("price", item.getPrice());

                    List<Items> items = null;
                    items = inv.getAll();
                    request.setAttribute("items", items);
                    getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
                } else {
                    List<Items> items = null;
                    items = inv.getAll();
                    request.setAttribute("items", items);
                    getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
                }
            } else if (action != null && action.equals("edit")) {
                String selectedItem = request.getParameter("selectedItem");
                int itemID = Integer.parseInt(selectedItem);
                Items item = new Items(itemID);
                request.setAttribute("categories", item.getCategory());
                request.setAttribute("itemName", item.getItemName());
                request.setAttribute("price", item.getPrice());

                List<Items> items = null;
                try {
                    items = inv.getAll(sessionUser);
                } catch (Exception ex) {
                    Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.setAttribute("items", items);
                getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            } else {
                List<Items> items = null;
                try {
                    items = inv.getAll(sessionUser);
                } catch (Exception ex) {
                    Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("items", items);
                getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String itemName = request.getParameter("itemName");

        String owner = (String) session.getAttribute("userInventory");
        UserService us = new UserService();
        Users sessionUser = null;

        Inventory inv = new Inventory();
        try {
            sessionUser = us.get(owner);
            if (action.equals("add")) {
                String categories = request.getParameter("categories");
                int category = Integer.parseInt(categories);
                String price1 = request.getParameter("price");
                double price = Double.parseDouble(price1);
                Categories cat = inv.getCategory(category);
                inv.insert(itemName, price, sessionUser, cat);
                if (sessionUser.getIsAdmin()) {
                    List<Items> items = null;
                    try {
                        items = inv.getAll();
                    } catch (Exception ex) {
                        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    request.setAttribute("items", items);
                    getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
                } else {
                    List<Items> items = null;
                    try {
                        items = inv.getAll(sessionUser);
                    } catch (Exception ex) {
                        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    request.setAttribute("items", items);
                    getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
                }

            } else if (action.equals("delete")) {
                String selectedItem = request.getParameter("selectedItem");
                int itemID = Integer.parseInt(selectedItem);
                Items item = new Items(itemID);
                inv.delete(item);

                List<Items> items = null;
                try {
                    items = inv.getAll(sessionUser);
                } catch (Exception ex) {
                    Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.setAttribute("items", items);
                getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }
    }
}
