package database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// this DA will create the database conneciton for all DA classes
public class BankingSystemDA {
    
    public static EntityManagerFactory getEmFactory() {
        try {
            final EntityManagerFactory EMF = 
            // the value passed "BankingSystemPU" must match the name of the persisnte-unti i  in ht epersistnece.xml file
                    Persistence.createEntityManagerFactory("BankingSystemPU");
            return EMF;
        }
        catch (javax.persistence.PersistenceException pe) {
            System.out.println("\nMake sure BankingSystemDB is populated and DB is ready for conneciton\n" + pe);
            
        }
        return null;
    }
    
}
