import model.Account;
import net.bytebuddy.implementation.bytecode.Throw;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.util.Iterator;
import java.util.List;

public class ManageAccount {
    private static SessionFactory factory;

    public static void main(String[] args) {
        try {

        factory = new Configuration()
                    .configure()
                    .buildSessionFactory();

        } catch (Throwable  ex) {
            System.err.println("Failed  to create sesstion factory." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManageAccount manageAccount =  new ManageAccount();

        manageAccount.addAcount("Alex", 200.12);

        manageAccount.listAccounts();
    }

    public Account addAcount(String name, double amount) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer accountId = null;
        Account account = null;

        try  {
            tx = session.beginTransaction();
            account = new Account(name, amount);
            accountId = (Integer) session.save(account);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return account;
    }

    public void listAccounts() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx  = session.beginTransaction();
            List accounts = session.createQuery("from Account ").list();

            for(Iterator iterator = accounts.iterator(); iterator.hasNext();) {
                Account account = (Account) iterator.next();

                System.out.println("Name: " + account.getName());
                System.out.println("Amount: " + account.getAmount());
            }
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
