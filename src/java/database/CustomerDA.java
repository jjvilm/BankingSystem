package database;

import business.Customer;
import business.UserRole;
import exceptions.RecordNotFound;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class CustomerDA {

    // Search BankingSystemDB in CUSTOMER table BY userID
    public static Customer find(String userID) throws RecordNotFound {
        EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
        // Java Persistence Query Languge (JPQL)
        String qString = "SELECT c FROM Customer c " +
                "WHERE c.userID = :userID";
        TypedQuery<Customer> q = em.createQuery(qString, Customer.class);
        q.setParameter("userID", userID);
        try {
            Customer cust = q.getSingleResult();
            return cust;
        }
        catch (NoResultException e) {
            System.out.println(e);
            return null;
        }
        finally {
            em.close();
        }
        
    }
    // Search BankingSystemDB in CUSTOMER table BY Cust_ID PK
    public static Customer find(int custid) throws RecordNotFound {
        EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
        
        try {
            Customer cust = em.find(Customer.class, custid);
            return cust;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            em.close();
        }
        
        return null;
    }
    
    
    public static void createCustomer(int usrRole, String firstName, String lastName, String phoneNumber, String userID, String password) throws Exception {
    EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
    EntityTransaction trans = em.getTransaction();
    
    // Check if userID already exists to avoid customers with same userID
    Customer existingCustomer = find(userID);
    if (existingCustomer != null) {
        throw new Exception("Error: User ID already exists.");
    }
    
    try {
        trans.begin();
        Customer newCustomer = new Customer();
        newCustomer.setUsrRole(usrRole);
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setPhoneNumber(phoneNumber);
        newCustomer.setUserID(userID);
        newCustomer.setPassword(password);
        em.persist(newCustomer);
        trans.commit();
    } catch (Exception ex) {
        trans.rollback();
        System.err.println(ex);
    } finally {
        em.close();
    }
}

    
    public static Customer updateCustomer(int usrRole, int customerId, String firstName, String lastName, String phoneNumber, String userId, String password) {
    EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
    Customer customer = null;
    try {
        em.getTransaction().begin();
        customer = em.find(Customer.class, customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return null;
        }
        if (usrRole != 0) {
            UserRole userRole = em.find(UserRole.class, usrRole);
            if (userRole == null) {
                System.out.println("User role not found.");
                return null;
            }
            customer.setUsrRole(usrRole);
        }
        if (firstName != null) {
            customer.setFirstName(firstName);
        }
        if (lastName != null) {
            customer.setLastName(lastName);
        }
        if (phoneNumber != null) {
            customer.setPhoneNumber(phoneNumber);
        }
        if (userId != null) {
            customer.setUserID(userId);
        }
        if (password != null) {
            customer.setPassword(password);
        }
        em.getTransaction().commit();
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    } finally {
        em.close();
    }
    return customer;
}

    public static List<Customer> getCustomers() {
        EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Customer c";
        TypedQuery<Customer> q = em.createQuery(qString, Customer.class);
        List<Customer> customers = null;
        try {
            customers = q.getResultList();
        } catch (NoResultException e) {
            System.out.println(e);
            return null;
        } finally {
            em.close();
        }
        return customers;
    }

    
    // check for userid and password, return true if UserID and Pass match, else false
    public static int validateCredentials(String userID, String password) {
        // returns int 1 if user is found, otherwise returns -1 if user not found
        try {
            Customer customer;
            customer = CustomerDA.find(userID);
            if (customer.getUserID().equals(userID) && customer.getPassword().equals(password)) {
                return 1; // both username and password match
            }

        } catch (RecordNotFound e) {
            System.out.println(e);
            return 0;
        }

        return -1;
    }
    
    public static void deleteCustomer(int customerId) {
        EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, customerId);
            if (customer == null) {
                System.out.println("Customer not found.");
            } else {
                em.remove(customer);
                em.getTransaction().commit();
                System.out.println("Customer with ID " + customerId + " deleted successfully.");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
    }
    
    
}
        
