package business;

import java.io.Serializable;
import java.util.Objects;

public class TransactionPK implements Serializable {
    private int transactionID;
    private int accountNumber;

    public TransactionPK() {
    }

    public TransactionPK(int transactionID, int accountNumber) {
        this.transactionID = transactionID;
        this.accountNumber = accountNumber;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionPK)) return false;
        TransactionPK that = (TransactionPK) o;
        return transactionID == that.transactionID && accountNumber == that.accountNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionID, accountNumber);
    }
}
