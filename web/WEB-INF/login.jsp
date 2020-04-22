<%-- 
    Document   : admin
    Created on : Mar 24, 2020, 6:14:48 PM
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
        <h2>Login</h2>
        <form action="login" method="POST">
            Username: <input required type="text" name="userName"/><br/>
            Password: <input required type="password" name="password"/><br/>
            <input type="hidden" name="action" value="login">
            <input type="submit" value="Login"/>
        </form>
    </body>
    <br>
    <p>Don't have an account?</p>
    <a href="register">Register</a>
</html>
