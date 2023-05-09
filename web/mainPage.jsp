<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>${user.getFirstName()}'s Account</title>
  </head>
  <body>
    <h1>Welcome to your Account, ${user.getFirstName()}!</h1>
    <div class="user-details">
      <h3>User Details</h3>
      <ul>
        <li><b>Name:</b> ${user.getFirstName()} ${user.getLastName()}</li>
        <li><b>Phone Number:</b> ${user.getPhoneNumber()}</li>
        <li><b>Customer ID:</b> ${user.getCustomerID()}</li>
        <li><b>Username:</b> ${user.getUserID()}</li>
      </ul>
    </div>
      <br>
  <div class="account-management">
    <h3>Account Management</h3>
    <label>Use the button below to view your accounts:</label>
    <form action="Accounts" method="post">
      <input type="hidden" name="option" value="accounts">
      <input type="submit" value="View Accounts">
    </form>
  </div>
    <c:if test="${loggedInRole.getAllUsers() == 1}">
      <div class="customer-management">
        <h3>Customer Management</h3>
        <label>Use the button below to view all customers:</label>
        <form action="Customer" method="post">
          <input type="hidden" name="option" value="view-all">
          <input type="submit" value="View All Customers">
        </form>
      </div>
    </c:if>
  <c:if test="${loggedInRole.getEditRole() == 1}">
  <div class="user-role-management">
    <h3>User Role Management</h3>
    <lable>Use the button below to view all user roles:</label>
    <form action="User" method="post">
      <input type="hidden" name="option" value="view-all">
      <input type="submit" value="View All Users">
    </form>
   
  </div>
</c:if>

</form>
<!-- logout button-->
<br>
<label>Please use the logout button when you are done looking around.</label>
<form action="login" method="post">
    <input type="hidden" name="option" value="logout">
    <input type="submit" value="LOGOUT">
</form>