<%-- 
    Document   : inventory
    Created on : Mar 24, 2020, 6:14:57 PM
    Author     : 810585
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Final Project</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h3>Menu</h3>
        <ul>
            <c:if test="${isAdminInventory != null}"><li><a href="admin">Admin</a></li><br></c:if>
            <c:if test="${isUserInventory != null}"><li><a href="main">Account Settings</a></li><br></c:if>
                <li><a href="login">Logout</a></li>
            </ul>
            <h2>Inventory for ${userInventory}</h2>
        <table>
            <tr>
                <th>Category</th>
                <th>Name</th>
                <th>Price</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach var="item" items="${items}" varStatus="status">
                <tr>
                    <td>${item.category.categoryName}&nbsp;</td>
                    <td>${item.itemName}&nbsp;</td>
                    <td>$${item.price}&nbsp;</td>
                    <td>
                        <form action="inventory" method="post">
                            <input type="hidden" name="selectedItem" value="${item.itemID}"/>
                            <input type="hidden" name="action" value="delete" />
                            <input type="submit" value="Delete"/>
                        </form>
                    </td>
                    <td>
                        <form action="inventory" method="get">
                            <input type="hidden" name="selectedItem" value="${item.itemID}"/>
                            <input type="hidden" name="action" value="edit" />
                            <input type="submit" value="Edit"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>  
        </table>
        <h3>Add Item</h3>
        <form action="inventory" method="POST">
            Category: 
            <select name="categories">
                <option selected value="1">kitchen</option>
                <option value="2">bathroom</option>
                <option value="3">living room</option>
                <option value="4">basement</option>
                <option value="5">bedroom</option>
                <option value="6">garage</option>
                <option value="7">office</option>
                <option value="8">utility room</option>
                <option value="9">storage</option>
                <option value="10">other</option>
            </select>
            <br>
            Name: <input required type="text" name="itemName" value="${itemName}"/>
            <br/>
            Price: <input required type="text" name="price" value="${price}"/>
            <br/>
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add"/>
        </form>
    </body>
</html>
