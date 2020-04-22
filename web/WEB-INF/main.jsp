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
            Username: <input required type="text" name="username23" value="${username23}"><br>
            Email: <input required type="email" name="email23" value="${email23}"><br>
            First Name: <input required type="text" name="firstName23" value="${firstName23}"><br>
            Last Name: <input required type="text" name="lastName23" value="${lastName23}"><br>
            <input type="hidden" name="action" value="save">
            <input type="submit" value="Save">
        </form><br>
        <form action="main" method="POST">
            <input required type="hidden" name="username24" value="${username24}"><br>
            <input required type="hidden" name="email24" value="${email24}"><br>
            <input required type="hidden" name="firstName24" value="${firstName24}"><br>
            <input required type="hidden" name="lastName24" value="${lastName24}"><br>
            <input type="hidden" name="action" value="deactivate">
            <input type="submit" value="Deactivate Account">
        </form>
    </body>
</html>
