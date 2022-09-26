package repo;

import model.Account;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class AccountRepo {
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public int insert(Account furniture) {
        return (Integer) hibernateTemplate.save(furniture);
    }
    public Account getAccount(int roll) {
        return hibernateTemplate.get(Account.class, roll);
    }


}
