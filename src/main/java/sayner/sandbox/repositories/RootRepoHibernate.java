package sayner.sandbox.repositories;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

public interface RootRepoHibernate {

    default <T> T initializeAndUnproxy(T entity) throws NullPointerException {

        if (entity == null) {
            throw new
                    NullPointerException("Entity passed for initialization is null");
        }

        Hibernate.initialize(entity);

        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entity;
    }
}
