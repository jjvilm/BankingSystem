package business;

import database.CustomerDA;
import exceptions.RecordNotFound;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {
    
    @Column (name = "USR_ROLE")
    private int usrRole;
    @Column (name = "CUST_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerID;
    @Column (name = "FIRST_NAME")
    private String firstName;
    @Column (name = "LAST_NAME")
    private String lastName;
    @Column (name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column (name = "USER_ID")
    private String userID;
    @Column (name = "PASSWORD")
    private String password;

    public Customer() {}

    public Customer(int usrRole, int customerID, String firstName, String lastName, String phoneNumber, String userID, String password) {
        this.usrRole = usrRole;
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userID = userID;
        this.password = password;
    }

    // Find by UserID 
    public static Customer find(String userID) {
        try {
            return CustomerDA.find(userID);
        } catch (RecordNotFound e) {
            System.out.println("Record Not Found: " + e.getMessage());
            return null;
        }
    }
    // Find by CustID PK
    public static Customer find(int custID) {
        try {
            return CustomerDA.find(custID);
        } catch (RecordNotFound e) {
            System.out.println("Record Not Found: " + e.getMessage());
            return null;
        }
    }

    public static void createCustomer(int usrRole, String firstName, String lastName, String phoneNumber, String userID, String password) throws Exception {
        try {
            CustomerDA.createCustomer(usrRole, firstName, lastName, phoneNumber, userID, password);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static int validateCredentials(String userID, String password) {
        try {
            return CustomerDA.validateCredentials(userID, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public static Customer updateCustomer(int usrRole, int customerId, String firstName, String lastName, String phoneNumber, String userId, String password) {
        return CustomerDA.updateCustomer(usrRole, customerId, firstName, lastName, phoneNumber, userId, password);
    }

    public static void deleteCustomer(int customerId) {
        CustomerDA.deleteCustomer(customerId);
    }

    
    @Override
    public String toString() {
        return "ID:" + getUserID() + " Name:" + getFirstName() + " " + getLastName()
                + " Phone#:" + getPhoneNumber() + " Pass:" + getPassword();

    }

    public static List<Customer> getCustomers() {
        return CustomerDA.getCustomers();
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUsrRole() {
        return usrRole;
    }

    public void setUsrRole(int usr_role) {
        this.usrRole = usr_role;
    }

}
