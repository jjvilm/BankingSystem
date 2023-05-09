<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/> 
        <title>Accounts</title>
    </head>
    <body>
        <h1>${user.getFirstName()}'s Banking Accounts</h1>
        <br>
         <!--create button-->
        <br>
        <c:if test="${loggedInRole.getCreateAccount() == 1}">
        <form action="Accounts" method="post">
            <input type="hidden" name="option" value="createAccount">
            <input type="submit" value="Create Account">
        </form>
        </c:if>
        <!------------------------->
        <br>
        <h1>Accounts:</h1>
        <table>
            <tr>
                <th class="right">Date Opened:</th>
                <th class="right">Account #:</th>
                <th class="centered">Account Type</th>
                <th class="centered">Account Name</th>
                <th class="right">Balance</th>
                <th class="right">Transactions</th>
            </tr>
            <c:forEach var="account" items="${accounts}">
                <tr>
                    <td class="right">${account.getDateOpened()}</td>
                    <td class="right">${account.getAccountNumber()}</td>
                    <td class="centered">
                        <c:choose>
                            <c:when test="${account.getAccountType() == 1}">Asset</c:when>
                            <c:when test="${account.getAccountType() == 2}">Liability</c:when>
                        </c:choose>
                    </td>

                    <td class="centered">${account.getAccountName()}</td>
                    <td class="right">${account.getBalance() + aaTotalTxnAmount}</td>
                    <td>
                        <form action="transactions" method="post" class="centered">
                        <input type="hidden" name="option" value="viewAcc">
                        <input type="hidden" name="selectedAcc" value="${account.getAccountNumber()}">
                        <input type="submit" value="View">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            
        </table>
                
        <!--transfer button-->    
        <br>
        <c:if test="${loggedInRole.getTransfer() == 1}">
        <form action="Accounts" method="post">
        <input type="hidden" name="option" value="transfer">

        <label for="sourceAccount">Source Account:</label>
        <select id="sourceAccount" name="sourceAccount">
            <c:forEach var="account" items="${accounts}">
                <option value="${account.getAccountNumber()}">${account.getAccountName()}</option>
            </c:forEach>
        </select>

        <label for="destinationAccount">Destination Account:</label>
        <select id="destinationAccount" name="destinationAccount">
            <c:forEach var="account" items="${accounts}">
                <option value="${account.getAccountNumber()}" ${account.getAccountNumber() == sourceAccount ? 'disabled' : ''}>${account.getAccountName()}</option>
            </c:forEach>
        </select>

        <label for="amount">Amount:</label>
        <input type="text" id="amount" name="amount">

        <input type="submit" value="Transfer Funds">
        </form>
        </c:if>



        <!--back button-->
        <br>
        <form action="Accounts" method="post">
            <input type="hidden" name="option" value="back">
            <input type="submit" value="Back">
        </form>
        <br>
        <br>
        <!--logout button-->
        <form action="login" method="post">
            <input type="hidden" name="option" value="logout">
            <input type="submit" value="LOGOUT">
        </form>
    </body>
</html>
