package run;

import model.Account;
import model.User;
import org.hibernate.Session;
import utils.HibernateUtils;

import javax.persistence.criteria.*;
import java.util.List;

public class Hcql {
    public static void main(String[] args) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);

            Root<User> uRoot = query.from(User.class);
            Root<Account> aRoot = query.from(Account.class);

            query.where(builder.equal(uRoot.get("account"), aRoot.get("accountId")));
            query.multiselect(aRoot.get("name"),
                    builder.avg(aRoot.get("amount")),
                    builder.max(aRoot.get("amount")),
                    uRoot.get("firstName"));
            query.orderBy(builder.desc(uRoot.get("firstName")));

            List<Object[]> rs = session.createQuery(query)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            for (Object[] obj : rs) {
                System.out.println(obj[0] + " " + obj[1] + " " + obj[2]
                                    + " " + obj[3]);
            }

            session.getTransaction().commit();
        }
    }
}
