<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Login</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/> 
    </head>
    <body>
        <h1>Money Savings Customer Login</h1>
        <p>Enter your username and password to login, or logout to go back to main page</p>
        <form action="login" method="post">
        <input type="hidden" name="option" value="validate">        
        <label class="pad_top">username:</label>
        <input type="text" name="username" required><br>
        <label class="pad_top">password:</label>
        <input type="text" name="password" required><br>
        <label>&nbsp;</label>
        <input type="submit" value="LOGIN" class="margin_left">
    </form>
    <br>
    <br>
    <br>
    <form action="login" method="post">
        <input type="hidden" name="option" value="logout">
        <input type="submit" value="GO BACK">
    </form>
    </body>
</html>


