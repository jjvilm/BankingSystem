package servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import business.Account;
import business.Customer;
import business.Transaction;
import java.util.List;

public class CustomerController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/mainPage.jsp";
        // get current action
        // initialize variables that will be used throughout
        String option = request.getParameter("option");
        HttpSession session = request.getSession();
        int userRole;
        Customer cust;
        String firstName;
        String lastName;
        String phoneNumber;
        String userID;
        String password;
        List<Customer> customerList;
        

        if (option == null) {
            option = "logout";
        }
        // perform action and set URL to appropriate page
        switch (option) {
            case "create":
                url = "/createCustomer.jsp";    
                break;
            case "delete":
                // deletes the selected customer 
                int selectedCust = Integer.parseInt(request.getParameter("selectedCust"));
                Customer.deleteCustomer(selectedCust);
                // gets new customer list and forwards it
                request.setAttribute("customerList", Customer.getCustomers());
                
                
                url = "/customerList.jsp";    
                break;
            case "view-all":
                // Views all customers in Database
                session.setAttribute("customerList",Customer.getCustomers());
                url = "/customerList.jsp";    
                break;
            case "view":
                // views accounts for selected customer
                
                // get the User/Customer selected to view Accounts
                int custID = Integer.parseInt(request.getParameter("selectedCust"));
                // get the user/customer's accounts
                request.setAttribute("accounts", Account.getAccounts(custID));
               
                request.setAttribute("selCust",Customer.find(custID));
                url = "/accountsManagement.jsp";
                break;
            case "viewAcc":
                // views the account txn for the selected customer's acc
                
                // obtain acc number from session attribute
                String selectedAcc = request.getParameter("selectedAcc");
                int accNumber = Integer.parseInt(selectedAcc);
                // cust objcted from 
                // acc object to pass
                Account acc = Account.find(accNumber);
                // obtain transaction for selected account
                List<Transaction> txns = Transaction.find(accNumber);
                double totalTxnAmount = Transaction.totalTxnsAmount(txns);

                // set new attribute to to be able to get either asset or liabiliy
                // account dynamically in the same transactions page
                
                custID = Integer.parseInt(request.getParameter("selectedCust"));
                cust = Customer.find(custID);
                request.setAttribute("user", cust);
                
                request.setAttribute("selAcc", acc);
                // set request attributes for transactions page
                request.setAttribute("txns", txns);
                request.setAttribute("totalTxnAmount", totalTxnAmount);
                
                url = "/transactionsManagement.jsp";
                break;
            case "edit":
                // edits customer for selected customer
                custID = Integer.parseInt(request.getParameter("selectedCust"));
                cust = Customer.find(custID);
                request.setAttribute("customer", cust);
                
                url = "/editCustomer.jsp";
                break;
            case "update":
                try {
                    custID = Integer.parseInt(request.getParameter("customerID"));
                    String userRoleStr = request.getParameter("userRole");
                    userRole = Integer.parseInt(userRoleStr);
                    firstName = request.getParameter("firstName");
                    lastName = request.getParameter("lastName");
                    phoneNumber = request.getParameter("phoneNumber");
                    userID = request.getParameter("userID");
                    password = request.getParameter("password");

                    Customer.updateCustomer(userRole, custID, firstName, lastName, phoneNumber, userID, password);
                    
                } catch (Exception e) {
                    System.out.println(e);
                }
                // forward new accounts to page
                // Views all customers in Database
                session.setAttribute("customerList",Customer.getCustomers());

                url = "/customerList.jsp";
                break;
            case "back":
                url = "/customerList.jsp";    
                break;
            case "backToMain":
                url = "/mainPage.jsp";    
                break;
            case "addCustomer":
                // add customer to database from submission
                String roleStr = request.getParameter("role");
                int role = Integer.parseInt(roleStr);
                
                firstName = request.getParameter("firstName");
                lastName = request.getParameter("lastName");
                phoneNumber = request.getParameter("phoneNumber");
                userID = request.getParameter("userID");
                password = request.getParameter("password");
                
                try {
                    Customer.createCustomer(role, firstName, lastName, phoneNumber, userID, password);
                } catch (Exception e) {
                    System.out.println("in addCustomer" + e);
                }
                
                // get new customer list and forward it
                session.setAttribute("customerList",Customer.getCustomers());
                url = "/customerList.jsp";   
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
