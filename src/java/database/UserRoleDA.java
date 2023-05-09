package database;

import business.UserRole;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UserRoleDA {
        // View all user roles
    public static List<UserRole> getAllUserRoles() {
        EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
        String qString = "SELECT r FROM UserRole r";
        TypedQuery<UserRole> q = em.createQuery(qString, UserRole.class);
        List<UserRole> userRoles = null;
        try {
            userRoles = q.getResultList();
            System.out.println("got roles");
            
        } catch (NoResultException e){
            System.out.println(e);
            System.out.println("Didnt' get roles");
            return null;
        } 
        finally {
            em.close();
        }
        return userRoles;
    }

    public static void updateUserRole(int usrRole, String description, int allUsers, int createCustomer, int transfer, int editRole, int createAccount) {
        EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
        UserRole userRole = em.find(UserRole.class, usrRole);
        if (userRole != null) {
            em.getTransaction().begin();
            userRole.setDescription(description);
            userRole.setAllUsers(allUsers);
            userRole.setCreateCustomer(createCustomer);
            userRole.setTransfer(transfer);
            userRole.setEditRole(editRole);
            userRole.setCreateAccount(createAccount);
            em.getTransaction().commit();
            System.out.println("User role with ID " + usrRole + " has been updated.");
        } else {
            System.out.println("User role with ID " + usrRole + " not found!");
        }
        em.close();
    }

    public static void createUserRole(String description, int allUsers, int createCustomer, int transfer, int editRole, int createAccount) {
        EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
        UserRole userRole = new UserRole();
        userRole.setDescription(description);
        userRole.setAllUsers(allUsers);
        userRole.setCreateCustomer(createCustomer);
        userRole.setTransfer(transfer);
        userRole.setEditRole(editRole);
        userRole.setCreateAccount(createAccount);
        try {
            em.getTransaction().begin();
            em.persist(userRole);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    
    public static UserRole find(int userRoleId) {
        EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
        UserRole userRole = null;
        try {
            userRole = em.find(UserRole.class, userRoleId);
        } catch (NoResultException e) {
            System.out.println("User role with ID " + userRoleId + " not found!");
        } finally {
            em.close();
        }
        return userRole;
    }

    public static void deleteUserRole(int usrRole) {
    EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
    UserRole userRole = em.find(UserRole.class, usrRole);
    if (userRole != null) {
        em.getTransaction().begin();
        em.remove(userRole);
        em.getTransaction().commit();
        System.out.println("User role with ID " + usrRole + " has been deleted.");
    } else {
        System.out.println("User role with ID " + usrRole + " not found!");
    }
    em.close();
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
