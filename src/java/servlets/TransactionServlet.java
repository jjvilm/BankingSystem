package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import business.Account;
import business.Transaction;
import java.util.List;

public class TransactionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // set default url
        String url = "/accounts.jsp";
        // get session to use
        HttpSession session;

        // get current action
        String option = request.getParameter("option");

        if (option == null) {
            // stay on mainpage if option is null
            option = "accounts";
        }

        switch (option) {
            case "back":
                break;
            case "accounts":
                url = "/accounts.jsp";    // the "accounts" page
                break;
            case "viewAcc": {
                url = "/transactions.jsp";
                
                // obtain acc number from session attribute
                String selectedAcc = request.getParameter("selectedAcc");
                System.out.println("SelectedAcc:" + selectedAcc);
                int accNumber = Integer.parseInt(selectedAcc);
                // acc object to pass
                Account acc = Account.find(accNumber);
                System.out.println("Selected Account:" + acc);
                // obtain transaction for selected account
                List<Transaction> txns = Transaction.find(accNumber);
                double totalTxnAmount = Transaction.totalTxnsAmount(txns);

                // set new attribute to to be able to get either asset or liabiliy
                // account dynamically in the same transactions page
                request.setAttribute("selAcc", acc);
                // set request attributes for transactions page
                request.setAttribute("txns", txns);
                request.setAttribute("totalTxnAmount", totalTxnAmount);
                
                break;
            }
            
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
