package business;

import database.TransactionDA;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table (name = "TXN")
@IdClass(TransactionPK.class)
public class Transaction implements Serializable {
    @Column (name = "TXN_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionID;
    @Column (name = "TXN_DATE")
    private String transactionDate;
    @Column (name = "ACC_NUM")
    @Id
    private int accountNumber;
    @Column (name = "DESCRIPTION")
    private String description;
    @Column (name = "TXN_AMOUNT")
    private double transactionAmount;
    @Column (name = "ACCOUNTED")
    private int accounted;

    public Transaction() {}

    public Transaction(int transactionID, String transactionDate, int accountNumber, String description, double transactionAmount, int accounted) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.accountNumber = accountNumber;
        this.description = description;
        this.transactionAmount = transactionAmount;
        this.accounted = accounted;
    }

    // takes a list of txns and updates the balance on the acc asscoicated with 
    // transaction ID
    public static void accountTxns(List<Transaction> txns) {
        TransactionDA.accountTxns(txns);
    }

    
    // returns ArrayList of Transactions for given acc
    public static List<Transaction> find(int accountNumber) {
        return TransactionDA.find(accountNumber);
    }

    // simple calculation of total amount in transactions passed
    public static double totalTxnsAmount(List<Transaction> txns) {
        return TransactionDA.totalTxnsAmount(txns);
    }

    @Override
    public String toString() {
        return "Acc#:" + getAccountNumber() + " TransactionID:" 
                + getTransactionID() + " Date:" + getTransactionDate()
                + " Description:" + getDescription()
                + " Amount:" + getTransactionAmount();

    }
    
    public static void create(int accountNumber, String description, double txnAmount, int accounted) {
        TransactionDA.create(accountNumber,  description,  txnAmount, accounted);
    }


    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getAccounted() {
        return accounted;
    }

    public void setAccounted(int accounted) {
        this.accounted = accounted;
    }

    
    
}

