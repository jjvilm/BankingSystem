package business;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "ASSET")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("1")
public class AssetAccount extends Account implements Serializable {

    @Column (name = "INT_RATE")
    private double interestRate;
    @Column (name = "TXN_FEE")    
    private double transactionFee;

    public AssetAccount() {
        // When an asset account is instantiated it is automatically given 
        // a default acc type of Asset (int 1)
        this.setAccountType(1);
    }

    public AssetAccount(int accountNumber, int accountType, int customerID, String accountName,
            String dateOpened, double interestRate, double transactionFee, double balance) {
        super(accountNumber, accountType, customerID, accountName, dateOpened, balance);
        this.interestRate = interestRate;
        this.transactionFee = transactionFee;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }
    
    @Override
    public String toString() {
        return "Customer ID:" + getCustomerID() 
                + " Acc#:" + getAccountNumber() 
                + " Type:" + getAccountType()
                + " AccNmae:" + getAccountName()
                + " DateOpened:" + getDateOpened()
                + " Balance:" + getBalance()
                + " InterestR:" + getInterestRate()
                + " TXN Fee:" + getTransactionFee()
                + "\n";
    }

}
