<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/main.css" type="text/css"/> 
</head>
<body>
    <h1>User Role</h1>
    <table>
        <thead>
            <tr>
                <th>User Role</th>
                <th>Description</th>
                <th>All Users</th>
                <th>Create Customer</th>
                <th>Transfer</th>
                <th>Edit Role</th>
                <th>Create Account</th>
            </tr>
        </thead>
        <tbody id="customer-table">
            <tr>
                <td>${userRole.usrRole}</td>
                <td>${userRole.description}</td>
                <td>${userRole.allUsers}</td>
                <td>${userRole.createCustomer}</td>
                <td>${userRole.transfer}</td>
                <td>${userRole.editRole}</td>
                <td>${userRole.createAccount}</td>
            </tr>
        </tbody>
    </table>
    <br>   
    
    <form id="userrole-form" action="User" method="post">
  <input type="hidden" name="option" value="update">
  <input type="hidden" id="usrRole" name="usrRole" value="${userRole.usrRole}">
  <label for="description">Description:</label>
  <input type="text" id="description" name="description" value="${userRole.description}" required>
  <br>
  <label for="allUsers">All Users:</label>
  <input type="checkbox" id="allUsers" name="allUsers" value="1" ${userRole.allUsers == 1 ? 'checked' : ''}>
    <input type="hidden" id="allUsers" name="allUsers" value="0">

  <br>
  <label for="createCustomer">Create Customer:</label>
  <input type="checkbox" id="createCustomer" name="createCustomer" value="1" ${userRole.createCustomer == 1 ? 'checked' : ''}>
    <input type="hidden" id="createCustomer" name="createCustomer" value="0">

  <br>
  <label for="transfer">Transfer:</label>
  <input type="checkbox" id="transfer" name="transfer" value="1" ${userRole.transfer == 1 ? 'checked' : ''}>
    <input type="hidden" id="transfer" name="transfer" value="0">

  <br>
  <label for="editRole">Edit Role:</label>
  <input type="checkbox" id="editRole" name="editRole" value="1" ${userRole.editRole == 1 ? 'checked' : ''}>
    <input type="hidden" id="editRole" name="editRole" value="0">

  <br>
  <label for="createAccount">Create Account:</label>
  <input type="checkbox" id="createAccount" name="createAccount" value="1" ${userRole.createAccount == 1 ? 'checked' : ''}>
    <input type="hidden" id="createAccount" name="createAccount" value="0">

  <br>
  <button type="submit" id="submit-button">Submit</button>
</form>


      <br>
      <form action="User" method="post">
        <input type="hidden" name="option" value="view-all">
        <input type="submit" value="Back">
    </form>
</body>
</html>
