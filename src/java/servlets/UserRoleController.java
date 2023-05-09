package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import business.UserRole;

public class UserRoleController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/mainPage.jsp";
        // get current action
        String option = request.getParameter("option");
        // initilize variables that will be used throughout the userRoleController
       

        if (option == null) {
            option = "logout";
        }
        // perform action and set URL to appropriate page
        switch (option) {
            case "back":
                url = "/mainPage.jsp";
                break;
                        
            case "create":
                url = "/createUserRole.jsp";    
                break;
            case "delete":
                // deletes the selected customer 
                int selectedUser = Integer.parseInt(request.getParameter("selectedUser"));
                UserRole.deleteUserRole(selectedUser);
                // gets new user list last and forwards it
                request.setAttribute("userRoleList", UserRole.getAllUserRoles());
                
                url = "/userRoleList.jsp";
                break;
            case "edit":
                int userRoleID = Integer.parseInt(request.getParameter("selectedUser"));
                request.setAttribute("userRole", UserRole.find(userRoleID));
                
                url = "/editUserRole.jsp";    
                break;
            case "update":
                // updated selected role to the database
                // add user role to database from submission
                userRoleID = Integer.parseInt(request.getParameter("usrRole"));
                String description = request.getParameter("description");
                
                // convert checkbox "true" variables into integers of 0 or 1
                String allUsersStr = request.getParameter("allUsers");
      
                
                int allUsers = (allUsersStr != null && allUsersStr.equals("1")) ? 1 : 0;

                String createCustomerStr = request.getParameter("createCustomer");
                int createCustomer = (createCustomerStr != null && createCustomerStr.equals("1")) ? 1 : 0;
                
                String transferStr = request.getParameter("transfer");
                int transfer = (transferStr != null && transferStr.equals("1")) ? 1 : 0;
                
                String editRoleStr = request.getParameter("editRole");
                int editRole = (editRoleStr != null && editRoleStr.equals("1")) ? 1 : 0;
                
                String createAccountStr = request.getParameter("createAccount");
                int createAccount = (createAccountStr != null && createAccountStr.equals("1")) ? 1 : 0;
                
                UserRole.updateUserRole(userRoleID, description, allUsers, createCustomer, transfer, editRole, createAccount);
                
                
                // get new userList from database and fordward the object 
                request.setAttribute("userRoleList",UserRole.getAllUserRoles());
                url = "/userRoleList.jsp";    
                break;
            case "addUserRole":
                // add user role to database from submission
                description = request.getParameter("description");
                allUsers = Integer.parseInt(request.getParameter("allUsers"));
                createCustomer = Integer.parseInt(request.getParameter("createCustomer"));
                transfer = Integer.parseInt(request.getParameter("transfer"));
                editRole = Integer.parseInt(request.getParameter("editRole"));
                createAccount = Integer.parseInt(request.getParameter("createAccount"));

                
                try {
                    UserRole.createUserRole(description, allUsers, createCustomer, transfer, editRole, createAccount);
                } catch (Exception e) {
                    System.out.println("Exception creating user role: " + e);
                }
                
                // fetches new user role list and fordwards it
                request.setAttribute("userRoleList",UserRole.getAllUserRoles());
                
                
                url = "/userRoleList.jsp";   
                break;
                
            case "view-all":
                // Views all customers in Database
                request.setAttribute("userRoleList",UserRole.getAllUserRoles());
                System.out.println("View-all: " + UserRole.getAllUserRoles());
                url = "/userRoleList.jsp";    
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
