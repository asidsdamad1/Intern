package service.impl;

import model.Account;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.AccountService;
import utils.HibernateUtils;

import java.util.Iterator;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    @Override
    public Account addAcount(String name, double amount) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            Transaction tx = null;
            Long accountId = null;
            Account account = null;

            try {
                tx = session.beginTransaction();
                account = new Account(name, amount);
                accountId = (Long) session.save(account);
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
}
