package database;

import business.Transaction;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class TransactionDA {
    
    // returns a list of Transactions for the specified acc #
    public static List<Transaction> find(int accountNumber) {
        EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
        // Java Persistence Query Languge (JPQL)
        String qString = "SELECT t FROM Transaction t " + 
                "WHERE t.accountNumber = :accountNumber";
        TypedQuery<Transaction> q = em.createQuery(qString, Transaction.class);
        q.setParameter("accountNumber", accountNumber);
        List<Transaction> txns;
        try {
            txns = q.getResultList();
            if (txns == null || txns.isEmpty()) {
                txns = null;
            }
        }
        finally {
            em.close();
        }
        return txns;
    }

    //returns net total of transactions passed
    public static double totalTxnsAmount(List<Transaction> txns) {
        double total = 0.0;
        for (int i = 0; i < txns.size(); i++) {
            total += txns.get(i).getTransactionAmount();
        }
        return total;
    }
    public static void accountTxns(List<Transaction> txns) {
        for (Transaction txn : txns) {
            //update balance in Account if txn not accounted for
            if (txn.getAccounted() == 0) {
                System.out.println("Accounting for txn:" + txn);
                double amount = txn.getTransactionAmount();
                int accNumber = txn.getAccountNumber();
                try {
                    // update balance on ACC
                    AccountDA.updateBalance(accNumber, amount);
                    
                    // update account column in TXN
                    EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
                    String qString = "UPDATE Transaction t SET t.accounted = 1 WHERE t.transactionID = :transactionID AND t.accountNumber = :accountNumber";
                    TypedQuery<Transaction> q = em.createQuery(qString, Transaction.class);
                    q.setParameter("transactionID", txn.getTransactionID());
                    q.setParameter("accountNumber", accNumber);
                    em.getTransaction().begin();
                    q.executeUpdate();
                    em.getTransaction().commit();
                    em.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            
        }
    }
 
    public static void create(int accountNumber, String description, double txnAmount, int accounted) {
        // Get the current date
        Date today = new Date();
        // Set the date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        // Format the date to MM/DD/YYYY format
        String formattedDate = dateFormat.format(today);
        
        Transaction txn = new Transaction();
        txn.setAccountNumber(accountNumber);
        txn.setTransactionAmount(txnAmount);
        txn.setDescription(description);
        txn.setTransactionDate(formattedDate);
        txn.setAccounted(accounted);

        EntityManager em = BankingSystemDA.getEmFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(txn);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }


    
}
