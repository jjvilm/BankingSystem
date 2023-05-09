package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import business.Account;
import business.Customer;
import business.Transaction;
import business.UserRole;
import exceptions.RecordNotFound;
import java.util.List;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* NOT FOUND page */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>RECORD NOT FOUND!</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>RECORD NOT FOUND!</h1>");
            out.println("<form action=\"login\" method=\"post\">");
            out.println("<input type=\"hidden\" name=\"option\" value=\"loginPage\">");
            out.println("<input type=\"submit\" value=\"User Login Page\" class=\"margin_left\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/index.html";
        // get current action
        String option = request.getParameter("option");
        HttpSession session;

        if (option == null) {
            option = "logout";
        }
        // perform action and set URL to appropriate page
        switch (option) {
            case "logout":
                url = "/index.html";    // the "index" page
                break;
            case "loginPage":
                url = "/login.jsp";   // the "login" page
                break;
            case "validate":
                // code that will throw RecordNotFound exception
                try {
                    // set variables from user input
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");

                    // Validating user credentials 
                    int validation;
                    validation = Customer.validateCredentials(username, password);
                    // user found in this code block
                    if (validation == 1) {
                        // Create session object to save attributes across JSP pages
                        session = request.getSession();
                        // Customer Object of the user that logged in
                        Customer user = Customer.find(username);
                        // setting variable attribute to get user details in JSP pages
                        session.setAttribute("user", user);

                        UserRole role = UserRole.find(user.getUsrRole());
                        session.setAttribute("userRole", role.getDescription());
                        // check to see the role of the user logged in
                        // to give access to certain functionalites
                        session.setAttribute("loggedInRole", role);
                        // user accounts
                        List<Account> accs = Account.getAccounts(user.getCustomerID());
                        session.setAttribute("accounts",accs);
                        
                        // run transactions that are not accounted for to reflect on balance
                        for (Account acc : accs) {
                            try {
                                // gets all tranasactions in account
                                List<Transaction> txns = Transaction.find(acc.getAccountNumber());
                                // update database
                                Transaction.accountTxns(txns);
                            } catch (Exception e) {
                                System.out.println("error here" + e);
                            }
                        }
//                 

                        url = "/mainPage.jsp";
                    } else {
                        processRequest(request, response);
                        throw new RecordNotFound("Record Not Found TRY AGAIN!");
                    }
                } catch (RecordNotFound e) {
                    // handle exception here
                    url = "/login.jsp";
                }
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

}
