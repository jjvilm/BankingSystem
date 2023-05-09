package servlets;

import business.Account;
import business.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import business.Transaction;
import java.util.List;

public class DisplayAccount extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* Record NOT FOUND page*/
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>RECORD NOT FOUND!</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>RECORD NOT FOUND!</h1>");
            out.println("<form action=\"Accounts\" method=\"post\">");
            out.println("<input type=\"hidden\" name=\"option\" value=\"back\">");
            out.println("<input type=\"submit\" value=\"BACK\" class=\"margin_left\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/mainPage.jsp";
        HttpSession session = request.getSession();
        Customer user;

        // get current action
        String option = request.getParameter("option");

        if (option == null) {
            // stay on mainpage if option is null
            option = "back";

        }

        switch (option) {
            case "accounts":
                user = (Customer) session.getAttribute("user");
                List<Account> accs = Account.getAccounts(user.getCustomerID());
                session.setAttribute("accounts",accs);
                
                url = "/accounts.jsp";    // the "accounts" page
                break;
            case "back":
                url = "/mainPage.jsp";   // the page user lands on after loggin in
                break;
            case "logout":
                url = "/index.html";
                break;
            case "transfer":
                user = (Customer) session.getAttribute("user");
                String sourceAccount = request.getParameter("sourceAccount");
                String destinationAccount = request.getParameter("destinationAccount");
                String amountString = request.getParameter("amount");
                double amount;

                try {
                    // convert to approprite types
                    amount = Double.parseDouble(amountString);
                    int sourceAccountId = Integer.parseInt(sourceAccount);
                    int destinationAccountId = Integer.parseInt(destinationAccount);
                    Account.transfer(sourceAccountId, destinationAccountId, amount);
                    session.setAttribute("accounts",Account.getAccounts(user.getCustomerID()));
                            

                    url = "/accounts.jsp";
                } catch (Exception e) {
                    System.out.println(e);
                    // handle the error when the amountString is not a valid number
                    notANumber(request,response);
                }

                break;
            case "createAccount":
                url = "/createAccount.jsp";
                break;
            case "createdAccount":
                Customer cust = (Customer)session.getAttribute("user");
                int custID = cust.getCustomerID();
                String accountName = request.getParameter("accountName");
                
                String accountTypeStr = request.getParameter("accountType");
                int accountType = Integer.parseInt(accountTypeStr);
                
                String balanceStr = request.getParameter("balance");
                double balance = Double.parseDouble(balanceStr);
                
                // pull attributes from "createAccount" page and 
                // call methods to create account
                Account newAcc = Account.createAccount(custID, accountType, accountName, balance);
                // create a transaction for the newly created account
                Transaction.create(newAcc.getAccountNumber(), "Initial", balance, 1);
                
                // reset the accounts parameter
                List<Account> newAccs = Account.getAccounts(custID);
                session.setAttribute("accounts",newAccs);
                url = "/accounts.jsp";
                break;
            default:
                break;
        }

        // forward request and response objects to specified URL
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    
    protected void notANumber(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* Record NOT FOUND page*/
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>NOT VALID!</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>NOT VALID!</h1>");
            out.println("<form action=\"Accounts\" method=\"post\">");
            out.println("<input type=\"hidden\" name=\"option\" value=\"accounts\">");
            out.println("<input type=\"submit\" value=\"BACK\" class=\"margin_left\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");

        }
    }
    
    
    
}
