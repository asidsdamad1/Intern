package run;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import model.User;
import org.hibernate.Session;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import utils.HibernateUtils;

import javax.persistence.Query;
import java.util.List;
import java.util.Set;

public class Hql {
    public static  void main(String[] args) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();

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

//            hql = "From User";
//            List<User> users = session.createQuery(hql, User.class).list();
//            for (User user :
//                    users) {
//                System.out.println(user);
//            }

            session.getTransaction().commit();
        }
    }
}
