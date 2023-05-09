package business;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "LIABILITY")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("2")
public class LiabilityAccount extends Account implements Serializable {
    @Column (name = "INT_RATE")
    private double interestRate;
    @Column (name = "MONTHLY_PAYMENT")
    private double monthlyPayment;
    @Column (name = "PAYMENT_DATE")
    private String paymentDate;

    public LiabilityAccount() {
        // When a liability account is instantiated it is automatically given 
        // a default type of Asset
        this.setAccountType(2);
    }

    public LiabilityAccount(int accountNumber, int accountType, int customerID,
            String accountName, String dateOpened, double interestRate,
            double monthlyPayment, String paymentDate, double balance) {
        super(accountNumber, accountType, customerID, accountName, dateOpened, balance);
        this.interestRate = interestRate;
        this.monthlyPayment = monthlyPayment;
        this.paymentDate = paymentDate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

      @Override
    public String toString() {
        return "Customer ID:" + getCustomerID() 
                + " Acc#:" + getAccountNumber() 
                + " Type:" + getAccountType()
                + " AccName:" + getAccountName()
                + " DateOpened:" + getDateOpened()
                + " Balance:" + getBalance()
                + " InterestR:" + getInterestRate()
                + " Mnthly Paymnet:" + getMonthlyPayment()
                + " Payment Date:" + getPaymentDate()
                + "\n";
    }
    
}
