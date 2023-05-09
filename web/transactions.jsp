<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/> 
        <title>${user.getFirstName()}'s ${selAcc.getAccountName()} Account</title>
    </head>
    <body>
        <h1>${user.getFirstName()}'s ${selAcc.getAccountName()} Account </h1>
        <p><b>Account#:${selAcc.getAccountNumber()}</b></p>
        <br>
        <h1>Account Transactions</h1>
        <table>
            <tr>
                <th>Transaction ID</th>
                <th class="centered">Description</th>
                <th class="centered">Date</th>
                <th>Transaction Amount</th>
                <th class="right">Actual Balance</th>
                <th class="right">Running Balance</th>
            </tr>
            <c:set var="runningBalance" value="${selAcc.getBalance() - totalTxnAmount}"/>
            <c:forEach var="txn" items="${txns}">
                <tr>
                    <td class="centered"><c:out value='${txn.getTransactionID()}'/></td>
                    <td class="centered"><c:out value='${txn.getDescription()}'/></td>
                    <td class="centered"><c:out value='${txn.getTransactionDate()}'/></td>
                    <td class="right">$<c:out value='${txn.getTransactionAmount()}'/></td>
                    <td class="right">$<c:out value='${runningBalance}'/></td>
            <!--variable to hold running balance--> 
                <c:choose>
                    <c:when test="${selAcc.getAccountType() == 1}">
                        <c:set var="runningBalance" 
                               value="${runningBalance + txn.getTransactionAmount()}" />
                        </c:when>
                    <c:when test="${selAcc.getAccountType() == 2}">
                        <c:set var="runningBalance" 
                               value="${runningBalance + txn.getTransactionAmount()}" />
                        </c:when>
                </c:choose>
                    <td class="right">$<c:out value='${runningBalance}'/></td>
                </tr>
            
            
            </c:forEach>
        </table>

        
        <!--back button-->
        <br>
        <form action="transactions" method="post">
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
