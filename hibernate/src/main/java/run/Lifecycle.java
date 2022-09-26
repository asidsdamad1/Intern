package run;

import model.Account;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;

public class Lifecycle {
    public static void main(String[] args) {
        Lifecycle lifecycle = new Lifecycle();
//        lifecycle.persistent();
        lifecycle.update();
    }

    public void persistent() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();

            System.out.println("- Loading user 1");
            User user1 = session.load(User.class, 1L);
            System.out.println("- After called ");
            System.out.println("- FirstName of user 1 " + user1.getFirstName());

            System.out.println("---");

            System.out.println("- Getting user 2");
            User user2 = session.get(User.class, 2L);
            System.out.println("- After called ");
            System.out.println("- FirstName of user 2 " + user2.getFirstName());

            System.out.println("---");

            System.out.println("- Finding user 3");
            User user3 = session.find(User.class, 2L);
            System.out.println("- After called ");
            System.out.println("- FirstName of user 3 " + user3.getFirstName());

            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
        }
    }

    public void update() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {

            Transaction tx1 = null, tx2 = null;

            try {
                tx1 = session.beginTransaction();

                Account acc = new Account();
                acc.setName("Java");

                session.save(acc);
                System.out.println("saved object is managed by hibernate session = " + session.contains(acc));

                session.evict(acc);
                System.out.println("evicted object is managed by hibernate session = " + session.contains(acc));

                acc.setName("Hibernate");

                session.merge(acc);
//                session.update(acc);
                System.out.println("updated object is managed by hibernate session = " + session.contains(acc));

                tx1.commit();
            } catch (HibernateException e) {
                if (tx1 != null)
                    tx1.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }
}
