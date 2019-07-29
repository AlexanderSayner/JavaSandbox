package sayner.sandbox.repositories.impl;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import sayner.sandbox.models.TradeDepartment;
import sayner.sandbox.repositories.DepartmentRepoHibernate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

@Repository
@Log4j2
public class DepartmentRepoHibernateImpl implements DepartmentRepoHibernate {

    private final SessionFactory sessionFactory;

    public DepartmentRepoHibernateImpl(EntityManagerFactory entityManagerFactory) {

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

        if (sessionFactory == null) {
            log.error("Тут, в DepartmentRepoHibernateImpl при вызове конструктора что-то явно пошло не по плану: entityManagerFactory.unwrap(SessionFactory.class) = NULL");
            throw new NullPointerException("factory is not a hibernate factory");
        } else {
            this.sessionFactory = sessionFactory;
        }
    }

    @Override
    public Collection<TradeDepartment> getAllDepartments() {

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TradeDepartment> criteriaQuery = criteriaBuilder.createQuery(TradeDepartment.class);
        Root<TradeDepartment> root = criteriaQuery.from(TradeDepartment.class);

        criteriaQuery
                .select(root)
        ;

        Query<TradeDepartment> tradeDepartmentQuery = session.createQuery(criteriaQuery);

        Collection<TradeDepartment> tradeDepartmentCollection = tradeDepartmentQuery.getResultList();

        session.getTransaction().commit();
        session.close();

        return tradeDepartmentCollection;
    }

    @Override
    public TradeDepartment getDepartmentById(int id) {

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        TradeDepartment tradeDepartment = session.get(TradeDepartment.class, id);
        if (tradeDepartment == null) {
            log.error("Check  DepartmentRepoHibernateImpl.getDepartmentById(" + id + ") - it is NULL");
        }

        session.getTransaction().commit();
        session.close();

        return tradeDepartment;
    }

    @Override
    public TradeDepartment addOneDepartment(TradeDepartment department) {
        return null;
    }

    @Override
    public TradeDepartment updateDepartment(TradeDepartment department) {
        return null;
    }

    @Override
    public TradeDepartment deleteOneDepartment(TradeDepartment department) {
        return null;
    }

    @Override
    public void addEntitiesToTheDatabase() {
    }
}