<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/main.css" type="text/css"/> 
</head>
<body>
    <h1>Customer</h1>
    <table>
        <thead>
            <tr>
                <th>User Role</th>
                <th>Customer ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Phone Number</th>
                <th>User ID</th>
                <th>Password</th>
            </tr>
        </thead>
        <tbody id="customer-table">
            <tr>
                <td>${customer.usrRole}</td>
                <td>${customer.customerID}</td>
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td>${customer.phoneNumber}</td>
                <td>${customer.userID}</td>
                <td>${customer.password}</td>
            </tr>
        </tbody>
    </table>
    <br>
    <div id="customer-modal" class="modal">
        <div class="modal-content">
            <h2 id="modal-title"></h2>
            <form id="customer-form" action="Customer" method="post">
                <input type="hidden" name="option" value="update">
                <input type="hidden" id="customer-id" name="customerID" value="${customer.customerID}">
                <label for="userRole">User Role:</label>
                <input type="text" id="userRole" name="userRole" value="${customer.usrRole}" required>
                <br>
                <label for="first-name">First Name:</label>
                <input type="text" id="first-name" name="firstName" value="${customer.firstName}" required>
                <br>
                <label for="last-name">Last Name:</label>
                <input type="text" id="last-name" name="lastName" value="${customer.lastName}" required>
                <br>
                <label for="phone-number">Phone Number:</label>
                <input type="text" id="phone-number" name="phoneNumber" value="${customer.phoneNumber}" required>
                <br>
                <label for="user-id">User ID:</label>
                <input type="text" id="user-id" name="userID" value="${customer.userID}" required>
                <br>
                <label for="password">Password:</label>
                <input type="text" id="password" name="password" value="${customer.password}" required>
                <br>
                <button type="submit" id="submit-button">Submit</button>
            </form>
        </div>
    </div>
     <br>
      <form action="Customer" method="post">
        <input type="hidden" name="option" value="view-all">
        <input type="submit" value="Back">
    </form>
</body>
</html>
