import model.Account;
import model.User;
import net.bytebuddy.implementation.bytecode.Throw;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import service.AccountService;
import service.impl.AccountServiceImpl;
import utils.HibernateUtils;


import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

public class ManageAccount {
    private static SessionFactory factory;

    public static void main(String[] args) {
        AccountService accountService = new AccountServiceImpl();
//        try {
//
//        factory = new Configuration()
//                    .configure()
//                    .buildSessionFactory();
//
//        } catch (Throwable  ex) {
//            System.err.println("Failed  to create sesstion factory." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//
//        ManageAccount manageAccount =  new ManageAccount();
//
//        manageAccount.addAcount("Alex", 200.12);
//
//        manageAccount.listAccounts();

//        System.out.println(accountService.updateAccount(1L, 1234));
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();

//            String hql = "From User";
            String hql = "Update User set firstName = :firstName where id = :id";

            Query query = session.createQuery(hql);
            query.setParameter("firstName", "Update");
            query.setParameter("id", 1L);
            query.executeUpdate();

            hql = "From User";
            List<User> users = session.createQuery(hql, User.class).list();
            for (User user:
                    users) {
                System.out.println(user);
            }



            session.getTransaction().commit();
        }
    }

}
