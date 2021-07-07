package sayner.sandbox.repositories.impl;

import org.springframework.stereotype.Repository;
import sayner.sandbox.repositories.JpaRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaRepositoryCustomImpl<T> implements JpaRepositoryCustom<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detachTypifiedEntity(T entity) {

        entityManager.detach(entity);
    }
}
