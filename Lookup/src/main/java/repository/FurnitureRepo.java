package repository;

import model.Furniture;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class FurnitureRepo {
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public int insert(Furniture furniture) {
        return (Integer) hibernateTemplate.save(furniture);
    }


}
