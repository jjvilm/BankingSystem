package database;

import business.Account;
import business.AssetAccount;
import business.LiabilityAccount;
import business.Transaction;
import exceptions.RecordNotFound;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class AccountDA {
    
    // Finds Account by customerID and accountType and returns it
     public static Account find(int customerID, int accountType) throws RecordNotFound {
            EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
            // Java Persistence Query Languge (JPQL)
            String qString = "SELECT a FROM Account a " +
                    "WHERE a.customerID = :customerID " +
                    "AND a.accountType = :accountType";
            TypedQuery<Account> q = em.createQuery(qString, Account.class);
            q.setParameter("customerID", customerID);
            q.setParameter("accountType", accountType);
            try {
                Account acc = q.getSingleResult();
                return acc;
            }
            catch (NoResultException e) {
                System.out.println(e);
                return null;
            }
            finally {
                em.close();
            }

        } 
    // Finds Account by accountNumber returns it
     public static Account find(int accountNumber) throws RecordNotFound {
            EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
            try {
                Account acc = em.find(Account.class, accountNumber);
                return acc;
            }
            catch (Exception e) {
                System.out.println(e);
            }
            finally {
                em.close();
            }

            return null;
        } 
     
     // create new acc
   public static Account createAccount(int customerID, int accountType, String accountName, double balance) {
    EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
    // Get the current date
    Date today = new Date();
    // Set the date format
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    // Format the date to MM/DD/YYYY format
    String formattedDate = dateFormat.format(today);

    try {
        em.getTransaction().begin();
        
        // Create a new account object
        if (accountType == 1) {
            // creates asset account
            AssetAccount account = new AssetAccount();
            account.setAccountType(accountType);
            account.setBalance(balance);
            account.setCustomerID(customerID);
            account.setAccountName(accountName);
            account.setDateOpened(formattedDate);
            // Persist the account object to the database
            em.persist(account);

            // Commit the transaction and return the account object
            em.getTransaction().commit();
            return account;
        }
        
        if (accountType == 2) {
            // creates liability account
            LiabilityAccount account = new LiabilityAccount();
            account.setAccountType(accountType);
            account.setBalance(balance);
            account.setCustomerID(customerID);
            account.setAccountName(accountName);
            account.setDateOpened(formattedDate);
            // Persist the account object to the database
            em.persist(account);

            // Commit the transaction and return the account object
            em.getTransaction().commit();
            return account;
        }


    } catch (Exception ex) {
        em.getTransaction().rollback();
        throw ex;
    } finally {
        em.close();
    }
    return null;
}
     
     
     
     // updates balance by passing Account obj then getting AccountNumber
    public static Account update(Account account) throws RecordNotFound {
        EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();

        try {
            em.getTransaction().begin();

            // Find the account to update
            Account acc = em.find(Account.class,account.getAccountNumber());

            if (acc == null) {
                throw new RecordNotFound("Account not found");
            }

            // Updates go here
            acc.setBalance(account.getBalance());


            em.getTransaction().commit();

            return acc;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
     // updates balance by passing  AccountNumber and amount
    public static void updateBalance(int accountNumber, double amount) throws RecordNotFound {
        EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();

        try {
            em.getTransaction().begin();

            // Find the account to update
            Account acc = em.find(Account.class,accountNumber);

            if (acc == null) {
                throw new RecordNotFound("Account not found");
            }

            // Updates go here
            double currentBalance = acc.getBalance();
            // set new balance
            acc.setBalance(currentBalance + amount);


            em.getTransaction().commit();

        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
   
   // transfers $$ amount between accounts, and returns the modified accounts (fromAccount, toAccount)
    // uses accountNumber to find the accounts
   public static Account[] transfer(int fromAccount, int toAccount, double amount) throws RecordNotFound {
    EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();

    try {
        em.getTransaction().begin();

        // Find the accounts to update
        Account accFrom = em.find(Account.class, fromAccount);
        Account accTo = em.find(Account.class, toAccount);

        // Check if accounts exist
        if (accFrom == null || accTo == null) {
            throw new RecordNotFound("Account not found");
        }

        // Deduct amount from the balance of fromAccount and add it to toAccount
        if (accFrom.getAccountType() == 1 && accTo.getAccountType() == 2) {
            // Transfer from Asset to Liability
            double fromBalance = accFrom.getBalance() - amount;
            if (fromBalance < 0) {
                
                // Do not allow transfers if not enough balance
                throw new IllegalArgumentException("Insufficient funds");
            }
            accFrom.setBalance(fromBalance);
            // decrease liability
            double toBalance = accTo.getBalance() - amount;
            accTo.setBalance(toBalance);
            
            // create 2 transactions to reflect the transfer
            Transaction.create(accFrom.getAccountNumber(), "Transfer", -1 * amount, 1);
            Transaction.create(accTo.getAccountNumber(), "Transfer", -1 * amount, 1);
           
        } else if (accFrom.getAccountType() == 2 && accTo.getAccountType() == 1) {
            // Transfer from Liability to Asset
            double fromBalance = accFrom.getBalance() + amount;
            accFrom.setBalance(fromBalance);

            double toBalance = accTo.getBalance() + amount;
            accTo.setBalance(toBalance);
            
            // create 2 transactions to reflect the transfer
            Transaction.create(accFrom.getAccountNumber(), "Transfer", amount, 1);
            Transaction.create(accTo.getAccountNumber(), "Transfer", amount, 1);
            
        } else if (accFrom.getAccountType() == 1 && accTo.getAccountType() == 1) {
            // Transfer from Asset to Asset
            double fromBalance = accFrom.getBalance() - amount;
            if (fromBalance < 0) {
                // Do not allow transfers if not enough balance
                throw new IllegalArgumentException("Insufficient funds");
            }
            accFrom.setBalance(fromBalance);
            // incrase asset
            double toBalance = accTo.getBalance() + amount;
            accTo.setBalance(toBalance);
            
            // create 2 transactions to reflect the transfer
            Transaction.create(accFrom.getAccountNumber(), "Transfer", -1 * amount, 1);
            Transaction.create(accTo.getAccountNumber(), "Transfer",  amount, 1);
        } else if (accFrom.getAccountType() == 2 && accTo.getAccountType() == 2) {
            // Transfer from Liability to Liability
            double fromBalance = accFrom.getBalance() + amount;
            if (fromBalance < 0) {
                
                // Do not allow transfers if not enough balance
                throw new IllegalArgumentException("Insufficient funds");
            }
            accFrom.setBalance(fromBalance);
            // decrease liability
            double toBalance = accTo.getBalance() - amount;
            accTo.setBalance(toBalance);
            
            // create 2 transactions to reflect the transfer
            Transaction.create(accFrom.getAccountNumber(), "Transfer", amount, 1);
            Transaction.create(accTo.getAccountNumber(), "Transfer", -1 * amount, 1);
        } else {
            // Invalid transfer
            throw new IllegalArgumentException("Invalid transfer between accounts");
        }

        // Update both accounts in the database
        em.getTransaction().commit();
        update(accFrom);
        update(accTo);
        
        // Return both accounts after the transfer
        Account[] accounts = new Account[2];
        accounts[0] = accFrom;
        accounts[1] = accTo;
        return accounts;

    } catch (Exception ex) {
        em.getTransaction().rollback();
        throw ex;
    } finally {
        em.close();
    }
}

// Lists all accounts for a customer
public static List<Account> getAccounts(int customerID) {
    EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
    String qString = "SELECT a FROM Account a " +
            "WHERE a.customerID = :customerID " +
            "ORDER BY a.accountType";
    TypedQuery<Account> q = em.createQuery(qString, Account.class);
    q.setParameter("customerID", customerID);
    List<Account> accounts = null;
    try {
        accounts = q.getResultList();
    } catch (NoResultException e) {
        System.out.println(e);
        return null;
    } finally {
        em.close();
    }
    return accounts;
}

     
     

}
