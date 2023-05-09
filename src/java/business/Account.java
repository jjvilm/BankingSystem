package business;

import database.AccountDA;
import exceptions.RecordNotFound;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ACC_TYPE", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Account implements Serializable {
    @Column(name = "ACC_NUM")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int accountNumber;
    @Column (name = "ACC_TYPE")
    private int accountType;
    @Column (name = "CUST_ID")
    private int customerID;
    @Column (name = "ACC_NAME")
    private String accountName;
    @Column (name = "DATE_OPENED")
    private String dateOpened;
    @Column (name = "ACC_BALANCE")
    private double balance;
    
    public Account() {}

    public Account(int accountNumber, int accountType, int customerID,
            String accountName, String dateOpened, double balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.customerID = customerID;
        this.accountName = accountName;
        this.dateOpened = dateOpened;
        this.balance = balance;
    }

    public static Account find(int customerID, int accountType) {
        try {
            return AccountDA.find(customerID, accountType);
        }
        catch (RecordNotFound e) {
            System.out.println(e);
        }
        return null;
    }
    public static Account find(int accountNumber) {
        try {
            return AccountDA.find(accountNumber);
        }
        catch (RecordNotFound e) {
            System.out.println(e);
        }
        return null;
    }
    

    public static Account createAccount(int customerID, int accountType, String accountName, double balance) {
        return AccountDA.createAccount(customerID, accountType, accountName, balance);
    }

    public static Account update(Account account) throws RecordNotFound {
        return AccountDA.update(account);
    }

   public static Account[] transfer(int fromAccount, int toAccount, double amount) throws RecordNotFound {
       return AccountDA.transfer(fromAccount, toAccount, amount);
   }

   public static List<Account> getAccounts(int customerID) {
       return AccountDA.getAccounts(customerID);
   }

    
    @Override
    public String toString() {
        return "Customer ID:" + getCustomerID() 
                + " Acc#:" + getAccountNumber() 
                + " Type:" + getAccountType()
                + " AccName:" + getAccountName()
                + " DateOpened:" + getDateOpened()
                + " Balance:" + getBalance()
                + "\n";

    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getDateOpened() {
        return dateOpened;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCustomerID(int cusomterID) {
        this.customerID = cusomterID;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setDateOpened(String dateOpened) {
        this.dateOpened = dateOpened;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}
