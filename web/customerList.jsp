<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/> 
    <title>Customer List</title>
</head>
<body>
    <h1>Customer List</h1>
    <table>
        <tr>
            <th>User Role</th>
            <th>Customer ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Phone Number</th>
            <th>User ID</th>
            <th>Password</th>
        </tr>
        <c:forEach items="${customerList}" var="customer">
            <tr>
                <td>${customer.getUsrRole()}</td>
                <td>${customer.getCustomerID()}</td>
                <td>${customer.getFirstName()}</td>
                <td>${customer.getLastName()}</td>
                <td>${customer.getPhoneNumber()}</td>
                <td>${customer.getUserID()}</td>
                <td>${customer.getPassword()}</td>
                <td>
                    <form action="Customer" method="post">
                        <input type="hidden" name="option" value="edit">
                         <input type="hidden" name="selectedCust" value="${customer.getCustomerID()}">
                        <input type="submit" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="Customer" method="post">
                        <input type="hidden" name="option" value="delete">
                         <input type="hidden" name="selectedCust" value="${customer.getCustomerID()}">
                        <input type="submit" value="Delete" style="background-color: red;">
                    </form>
                </td>
                <td>
                    <form action="Customer" method="post">
                        <input type="hidden" name="option" value="view">
                         <input type="hidden" name="selectedCust" value="${customer.getCustomerID()}">
                        <input type="submit" value="View Accounts">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <c:if test="${loggedInRole.getCreateCustomer() == 1}">
  <form action="Customer" method="post">
    <input type="hidden" name="option" value="create">
    <input type="submit" value="Create Customer">
  </form>
</c:if>

    <br>
     <form action="Customer" method="post">
        <input type="hidden" name="option" value="backToMain">
        <input type="submit" value="Back">
    </form>
    <!--logout button-->
        <form action="login" method="post">
            <input type="hidden" name="option" value="logout">
            <input type="submit" value="LOGOUT">
        </form>
</body>
</html>
