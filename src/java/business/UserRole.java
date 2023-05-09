package business;

import database.UserRoleDA;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "USER_ROLE")
public class UserRole implements Serializable {

    @Id
    @Column(name = "USR_ROLE")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int usrRole;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ALL_USERS")
    private int allUsers;

    @Column(name = "CREATE_CUSTOMER")
    private int createCustomer;

    @Column(name = "TRANSFER")
    private int transfer;

    @Column(name = "EDIT_ROLE")
    private int editRole;

    @Column(name = "CREATE_ACCOUNT")
    private int createAccount;

    public UserRole() {}

    public UserRole(int usrRole, String description, int allUsers, int createCustomer, int transfer, int editRole, int createAccount) {
        this.usrRole = usrRole;
        this.description = description;
        this.allUsers = allUsers;
        this.createCustomer = createCustomer;
        this.transfer = transfer;
        this.editRole = editRole;
        this.createAccount = createAccount;
    }

    public static List<UserRole> getAllUserRoles() {
        return UserRoleDA.getAllUserRoles();
    }

    public static void updateUserRole(int usrRole, String description, int allUsers, int createCustomer, int transfer, int editRole, int createAccount) {
        UserRoleDA.updateUserRole(usrRole, description, allUsers, createCustomer, transfer, editRole, createAccount);
    }

    public static void createUserRole(String description, int allUsers, int createCustomer, int transfer, int editRole, int createAccount) {
        UserRoleDA.createUserRole(description, allUsers, createCustomer, transfer, editRole, createAccount);
    }

    public static UserRole find(int userRoleId) {
        return UserRoleDA.find(userRoleId);
    }

    public static void deleteUserRole(int usrRole) {
        UserRoleDA.deleteUserRole(usrRole);
    }

    
    public int getUsrRole() {
        return usrRole;
    }

    public String getDescription() {
        return description;
    }

    public int getAllUsers() {
        return allUsers;
    }

    public int getCreateCustomer() {
        return createCustomer;
    }

    public int getTransfer() {
        return transfer;
    }

    public int getEditRole() {
        return editRole;
    }

    public int getCreateAccount() {
        return createAccount;
    }
    
    public void setUsrRole(int usrRole) {
        this.usrRole = usrRole;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAllUsers(int allUsers) {
        if (allUsers == 0 || allUsers == 1) {
            this.allUsers = allUsers;
        } else {
            throw new IllegalArgumentException("All Users value should be either 0 or 1.");
        }
    }

    public void setCreateCustomer(int createCustomer) {
        if (createCustomer == 0 || createCustomer == 1) {
            this.createCustomer = createCustomer;
        } else {
            throw new IllegalArgumentException("Create Customer value should be either 0 or 1.");
        }
    }

    public void setTransfer(int transfer) {
        if (transfer == 0 || transfer == 1) {
            this.transfer = transfer;
        } else {
            throw new IllegalArgumentException("Transfer value should be either 0 or 1.");
        }
    }

    public void setEditRole(int editRole) {
        if (editRole == 0 || editRole == 1) {
            this.editRole = editRole;
        } else {
            throw new IllegalArgumentException("Edit Role value should be either 0 or 1.");
        }
    }

    public void setCreateAccount(int createAccount) {
        if (createAccount == 0 || createAccount == 1) {
            this.createAccount = createAccount;
        } else {
            throw new IllegalArgumentException("Create Account value should be either 0 or 1.");
        }
    }
    
    @Override
    public String toString() {
        return "UserRole [usrRole=" + usrRole + ", description=" + description + ", allUsers=" + allUsers + ", createCustomer="
        + createCustomer + ", transfer=" + transfer + ", editRole=" + editRole + ", createAccount=" + createAccount + "]\n";
    }

    
    
    
    
    
    
}
