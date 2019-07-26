package sayner.sandbox.repositories.impl;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sayner.sandbox.models.BranchShop;
import sayner.sandbox.repositories.ShopsRepoHibernate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

@Repository
@Log4j2
public class ShopsRepoHibernateImpl implements ShopsRepoHibernate {

    private final SessionFactory sessionFactory;

    @Autowired
    public ShopsRepoHibernateImpl(EntityManagerFactory entityManagerFactory) {

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

        if (sessionFactory == null) {
            log.error("Тут, в ShopsRepoHibernateImpl при вызове конструктора что-то явно пошло не по плану: entityManagerFactory.unwrap(SessionFactory.class) = NULL");
            throw new NullPointerException("factory is not a hibernate factory");
        } else {
            this.sessionFactory = sessionFactory;
        }
    }

    @Override
    public Collection<BranchShop> getAllShops() {

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<BranchShop> criteriaQuery = criteriaBuilder.createQuery(BranchShop.class);
        Root<BranchShop> root = criteriaQuery.from(BranchShop.class);

        criteriaQuery
                .select(root)
        ;

        Query<BranchShop> branchShopQuery = session.createQuery(criteriaQuery);

        Collection<BranchShop> branchShopCollection = branchShopQuery.getResultList();

        session.getTransaction().commit();
        session.close();

        return branchShopCollection;
    }

    @Override
    public BranchShop getShopById(int id) {

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        BranchShop branchShop = session.get(BranchShop.class, id);
        if (branchShop == null) {
            log.error("Check  ShopsRepoHibernateImpl.getShopById(" + id + ") - it is NULL");
        }

        session.getTransaction().commit();
        session.close();

        return branchShop;
    }

    @Override
    public BranchShop addOneShop(BranchShop shop) {
        return null;
    }

    @Override
    public BranchShop updateShop(BranchShop shop) {
        return null;
    }

    @Override
    public BranchShop deleteOneShop(BranchShop shop) {
        return null;
    }
}