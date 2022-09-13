

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import service.AccountService;
import service.impl.AccountServiceImpl;
import utils.HibernateUtils;

import javax.persistence.Query;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;

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
//            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator =   Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .getValidator();

            String hql = "Update User set firstName = :firstName where id = :id";


            User userUpdate = new User();
            userUpdate.setId(1L);
            userUpdate.setFirstName("Up");

            Set<ConstraintViolation<User>> violations = validator.validate(userUpdate);
            for (ConstraintViolation<User> violation : violations) {
                System.err.println(violation.getMessage());
            }

            Query query = session.createQuery(hql);

            query.setParameter("firstName", userUpdate.getFirstName());
            query.setParameter("id", userUpdate.getId());

            query.executeUpdate();

            hql = "From User";
            List<User> users = session.createQuery(hql, User.class).list();
            for (User user :
                    users) {
                System.out.println(user);
            }
//
//            CriteriaBuilder builder = session.getCriteriaBuilder();
//
//            CriteriaQuery<User> query = builder.createQuery(User.class);
//            Root<User> root = query.from(User.class);
//            query.select(root);
//            query.orderBy(builder.desc(root.get("firstName")), builder.desc(root.get("lastName")));
//            List<User> users = session.createQuery(query)
//                    .setFirstResult(2)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            System.out.println(users);
            session.getTransaction().commit();
        }
    }

}
