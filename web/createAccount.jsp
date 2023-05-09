<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Create New Account</title>
</head>
<body>
	<h1>Create New Account</h1>
	<form action="Accounts" method="post">
        <input type="hidden" name="option" value="createdAccount">
		<label for="accountType">Account Type:</label>
		<select id="accountType" name="accountType" required>
			<option value="1">Asset</option>
			<option value="2">Liability</option>
		</select><br>

		<label for="balance">Balance:</label>
		<input type="number" id="balance" name="balance" step="100" required><br>

		<label for="accountName">Account Name:</label>
		<input type="text" id="accountName" name="accountName" required><br>

		<input type="submit" value="Create Account">
	</form>
        <!--back button-->
        <br>
        <form action="Accounts" method="post">
            <input type="hidden" name="option" value="accounts">
            <input type="submit" value="Back">
        </form>
</body>
</html>
