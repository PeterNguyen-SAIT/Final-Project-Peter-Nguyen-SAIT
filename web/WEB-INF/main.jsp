<%-- 
    Document   : main
    Created on : Apr 22, 2020, 12:41:42 PM
    Author     : 810585
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Final Project</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <ul>
            <li><a href="inventory">Inventory</a></li>
            <li><a href="login">Logout</a></li>
        </ul>
        <h3>Account Settings</h3>
        <h2>Account settings for ${userSetting}</h2>
        <form action="main" method="POST">
            Username: <input required type="text" name="username2" value="${username2}"><br>
            Email: <input required type="email" name="email2" value="${email2}"><br>
            First Name: <input required type="text" name="firstName2" value="${firstName2}"><br>
            Last Name: <input required type="text" name="lastName2" value="${lastName2}"><br>
            <input type="hidden" name="action" value="save">
            <input type="submit" value="Save">
        </form><br>
        <form action="main" method="POST">
            <input type="hidden" name="action" value="deactivate">
            <input type="submit" value="Deactivate Account">
        </form>
    </body>
</html>
