<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create User Role</title>
</head>
<body>
    <h1>Create User Role</h1>
    <form action="User" method="post">
    <input type="hidden" name="option" value="addUserRole">
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" required><br><br>
        <label for="allUsers">All Users:</label>
        <select name="allUsers" id="allUsers">
            <option value="1">Yes</option>
            <option value="0">No</option>
        </select><br><br>
        <label for="createCustomer">Create Customer:</label>
        <select name="createCustomer" id="createCustomer">
            <option value="1">Yes</option>
            <option value="0">No</option>
        </select><br><br>
        <label for="transfer">Transfer:</label>
        <select name="transfer" id="transfer">
            <option value="1">Yes</option>
            <option value="0">No</option>
        </select><br><br>
        <label for="editRole">Edit Role:</label>
        <select name="editRole" id="editRole">
            <option value="1">Yes</option>
            <option value="0">No</option>
        </select><br><br>
        <label for="createAccount">Create Account:</label>
        <select name="createAccount" id="createAccount">
            <option value="1">Yes</option>
            <option value="0">No</option>
        </select><br><br>
        <input type="submit" value="Submit">
    </form>
    <br>
    <form action="User" method="post">
        <input type="hidden" name="option" value="view-all">
        <input type="submit" value="Back">
    </form>
</body>
</html>
