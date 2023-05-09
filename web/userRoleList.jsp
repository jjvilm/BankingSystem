<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/> 
    <title>User Role List</title>
</head>
<body>
    <h1>User Role List</h1>
    <table>
        <tr>
            <th>User Role</th>
            <th>Description</th>
            <th>All Users</th>
            <th>Create Customer</th>
            <th>Transfer</th>
            <th>Edit Role</th>
            <th>Create Account</th>
        </tr>
        <c:forEach items="${userRoleList}" var="userRole">
            <tr>
                <td>${userRole.getUsrRole()}</td>
                <td>${userRole.getDescription()}</td>
                <td>${userRole.getAllUsers()}</td>
                <td>${userRole.getCreateCustomer()}</td>
                <td>${userRole.getTransfer()}</td>
                <td>${userRole.getEditRole()}</td>
                <td>${userRole.getCreateAccount()}</td>
                <td>
                    <form action="User" method="post">
                        <input type="hidden" name="option" value="edit">
                         <input type="hidden" name="selectedUser" value="${userRole.getUsrRole()}">
                        <input type="submit" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="User" method="post">
                        <input type="hidden" name="option" value="delete">
                         <input type="hidden" name="selectedUser" value="${userRole.getUsrRole()}">
                        <input type="submit" value="Delete" style="background-color: red;">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form action="User" method="post">
      <input type="hidden" name="option" value="create">
      <input type="submit" value="createUser">
    </form>
    <br>
    <form action="User" method="post">
        <input type="hidden" name="option" value="back">
        <input type="submit" value="Back">
    </form>
</body>
</html>