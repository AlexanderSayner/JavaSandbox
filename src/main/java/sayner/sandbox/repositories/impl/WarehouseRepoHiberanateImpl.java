package sayner.sandbox.repositories.impl;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sayner.sandbox.models.Warehouse;
import sayner.sandbox.repositories.WarehouseRepoHiberanate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

@Repository
@Log4j2
public class WarehouseRepoHiberanateImpl implements WarehouseRepoHiberanate {

    private final SessionFactory sessionFactory;

    @Autowired
    public WarehouseRepoHiberanateImpl(EntityManagerFactory entityManagerFactory) {

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

        if (sessionFactory == null) {
            log.error("Тут, в WarehouseRepoHiberanateImpl при вызове конструктора что-то явно пошло не по плану: entityManagerFactory.unwrap(SessionFactory.class) = NULL");
            throw new NullPointerException("factory is not a hibernate factory");
        } else {
            this.sessionFactory = sessionFactory;
        }
    }

    @Override
    public Collection<Warehouse> getAllWarehouses() {

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Warehouse> criteriaQuery = criteriaBuilder.createQuery(Warehouse.class);
        Root<Warehouse> root = criteriaQuery.from(Warehouse.class);

        criteriaQuery
                .select(root)
        ;

        Query<Warehouse> warehouseQuery = session.createQuery(criteriaQuery);

        Collection<Warehouse> warehouseCollection = warehouseQuery.getResultList();

        session.getTransaction().commit();
        session.close();

        return warehouseCollection;
    }

    @Override
    public Warehouse getWarehouseById(int id) {
        return null;
    }

    @Override
    public Warehouse addOneWarehouse(Warehouse warehouse) {
        return null;
    }

    @Override
    public Warehouse updateWarehouse(Warehouse warehouse) {
        return null;
    }

    @Override
    public Warehouse deleteOneWarehouse(Warehouse warehouse) {
        return null;
    }

    @Override
    public void addEntitiesToTheDatabase() {

    }
}
