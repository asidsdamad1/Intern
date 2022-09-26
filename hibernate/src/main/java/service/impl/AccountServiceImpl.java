package service.impl;

import model.Account;
import org.hibernate.*;
import org.hibernate.stat.Statistics;
import service.AccountService;
import utils.HibernateUtils;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AccountServiceImpl implements AccountService {
    @Override
    public Account addAcount(String name, double amount) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            Transaction tx = null;
            Account account = null;

            try {
                tx = session.beginTransaction();
                account = new Account(name, amount);
                session.save(account);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null)
                    tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }

            return account;
        }

    }

    @Override
    public void listAccounts() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                List accounts = session.createQuery("from Account ").list();

                for (Iterator iterator = accounts.iterator(); iterator.hasNext(); ) {
                    Account account = (Account) iterator.next();

                    System.out.println("Name: " + account.getName());
                    System.out.println("Amount: " + account.getAmount());
                }
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null)
                    tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }

    @Override
    public Account updateAccount(Long id, double amount) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            Transaction tx = null;
            Account account = null;

            try {
                tx = session.beginTransaction();
                account = session.get(Account.class, id);
                account.setAmount(amount);
                session.update(account);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null)
                    tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }

            return account;
        }
    }

    @Override
    public Account getById(Long id) {
        if (id == null) return null;
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            Session session1 =  HibernateUtils.getSessionFactory().openSession();


            Transaction tx = null,  tx1 = null;
            Account account = null;

            try {
                tx = session.beginTransaction();
                tx1 = session1.beginTransaction();
                account = session.get(Account.class, id);
                System.out.println(account.getName());

                Account account1 = session.get(Account.class, id);
                System.out.println(account1.getName());

                Account account2 = session1.get(Account.class, id);
                System.out.println(account2.getName());
//                for (int i = 0; i < 5; i++) {
//                    account = session.get(Account.class, id);
//                    System.out.println(account.getName());
//                }

                tx.commit();
                tx1.commit();
            } catch (HibernateException e) {
                if (tx != null)
                    tx.rollback();
                if(tx1 != null)
                    tx1.rollback();
                e.printStackTrace();
            } finally {
                session.close();
                session1.close();
            }

            return account;
        }
    }
    private static void printStats(Statistics stats) {
        System.out.println("Second Level Hit Count = " + stats.getSecondLevelCacheHitCount());
        System.out.println("Second Level Put Count = " + stats.getSecondLevelCachePutCount());
    }
    @Override
    public void statistics() {
        try (SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
             Session session1 = sessionFactory.openSession();
             Session session2 = sessionFactory.openSession();) {

            Transaction tx1 = null,  tx2 = null;

            try {
                tx1 = session1.beginTransaction();
                tx2 = session2.beginTransaction();

//                for (int i = 0; i < 5; i++) {
//                    account = session.get(Account.class, id);
//                    System.out.println(account.getName());
//                }
                Statistics stats = sessionFactory.getStatistics();
                System.out.println("Stats enabled = " + stats.isStatisticsEnabled());
                stats.setStatisticsEnabled(true);
                System.out.println("Stats enabled = " + stats.isStatisticsEnabled());

                printStats(stats);


                System.out.println("--- 1 ---");
                Account cat1 = session1.get(Account.class,1L);
                System.out.println(cat1.getName());
                printStats(stats);

                System.out.println("--- 2 ---");
                Account cat2 = session2.get(Account.class,1L);
                System.out.println(cat2.getName());
                printStats(stats);

                TimeUnit.SECONDS.sleep(3);

                System.out.println("--- 3 ---");
                Account cat3 = session2.get(Account.class,1L);
                System.out.println(cat3.getName());
                printStats(stats);

                String hql = "FROM Account acc WHERE acc.accountId = :id";
                Query<Account> query = session1.createQuery(hql, Account.class);
                query.setCacheable(true);
                query.setCacheRegion(Account.class.getCanonicalName());
                Account acc1 = query.setParameter("id", 1L).uniqueResult();
                System.out.println("cat1: " + acc1.getName());

                Account acc2 = query.setParameter("id", 1L).uniqueResult();
                System.out.println("cat2: " + acc2.getName());

                tx1.commit();
                tx2.commit();

            } catch (HibernateException e) {
                if (tx1 != null)
                    tx1.rollback();
                if( tx2 != null)
                    tx2.rollback();
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                session1.close();
                session2.close();
            }

        }
    }
}
