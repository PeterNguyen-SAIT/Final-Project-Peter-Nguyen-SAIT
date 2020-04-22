<%-- 
    Document   : test
    Created on : Feb 14, 2020, 11:50:48 AM
    Author     : Melissa Lee 791397
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
            <li><a href="inventory">Inventory</a></li>
            <li><a href="login">Logout</a></li>
        </ul>
        <h2>Manage Users</h2>
        <h2 hidden${isAdminPage}</h2>
        <table>
            <tr>
                <th>Username</th>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Active</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach var="user" items="${users}" varStatus="status">
                <tr>
                    <td>${user.username}&nbsp;</td>
                    <td>${user.email}&nbsp;</td>
                    <td>${user.firstName}&nbsp;</td>
                    <td>${user.lastName}&nbsp;</td>
                    <td>${user.active}&nbsp;</td>

                    <td>
                        <form action="admin" method="post">
                            <input type="hidden" name="selectedUser" value="${user.username}"/>
                            <input type="hidden" name="action" value="delete" />
                            <input type="submit" value="Delete"/>
                        </form>
                    </td>
                    <td>
                        <form action="admin" method="get">
                            <input type="hidden" name="selectedUser" value="${user.username}"/>
                            <input type="hidden" name="action" value="edit" />
                            <input type="submit" value="Edit"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>  
        </table>
        <h2>Edit User</h2>
        <form action="admin" method="POST">
            Username: <input required type="text" name="username" value="${username}"><br>
            Password: <input required type="text" name="password" value="${password}"><br>
            Email: <input required type="email" name="email" value="${email}"><br>
            First Name: <input required type="text" name="firstName" value="${firstName}"><br>
            Last Name: <input required type="text" name="lastName" value="${lastName}"><br>
            Active: <label class="switch"><input name="active" value="${active}" type="checkbox"><span class="slider"></span></label><br>
            <input type="hidden" name="action" value="save">
            <input type="submit" value="Save">
        </form>
        <h2>Add User</h2>
        <form action="admin" method="POST">
            Username: <input required type="text" name="username2" value="${username2}"><br>
            Password: <input required type="text" name="password2" value="${password2}"><br>
            Email: <input required type="email" name="email2" value="${email2}"><br>
            First Name: <input required type="text" name="firstName2" value="${firstName2}"><br>
            Last Name: <input required type="text" name="lastName2" value="${lastName2}"><br>
            Active: <label class="switch"><input name="active2" value="${active2}" type="checkbox"><span class="slider"></span></label><br>
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add">
        </form>
    </body>
</html>