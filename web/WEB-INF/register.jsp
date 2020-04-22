<%-- 
    Document   : register
    Created on : Apr 22, 2020, 8:51:16 AM
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
        <h3>Register</h3>
        <form action="register" method="POST">
            Username: <input required type="text" name="username1" value="${username1}"><br>
            Password: <input required type="text" name="password1" value="${password1}"><br>
            Email: <input required type="email" name="email1" value="${email1}"><br>
            First Name: <input required type="text" name="firstName1" value="${firstName1}"><br>
            Last Name: <input required type="text" name="lastName1" value="${lastName1}"><br>
            <input type="hidden" name="action" value="register">
            <input type="submit" value="Register">
        </form>
    </body>
</html>
