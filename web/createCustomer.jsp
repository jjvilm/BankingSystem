<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Customer</title>
</head>
<body>
    <h1>Create Customer</h1>
    <form action="Customer" method="post">
    <input type="hidden" name="option" value="addCustomer">
        <label for="role">Role:</label>
        <select name="role" id="role">
            <option value="1">Customer</option>
            <option value="2">Manager</option>
            <option value="3">Admin</option>
        </select><br><br>
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName"><br><br>
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName"><br><br>
        <label for="phoneNumber">Phone Number:</label>
        <input type="text" id="phoneNumber" name="phoneNumber"><br><br>
        <label for="userID">User ID:</label>
        <input type="text" id="userID" name="userID"><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br><br>
        
        <input type="submit" value="Submit">
    </form>
    <!--back button-->
        <br>
        <form action="Customer" method="post">
            <input type="hidden" name="option" value="back">
            <input type="submit" value="Back">
        </form>
</body>
</html>
