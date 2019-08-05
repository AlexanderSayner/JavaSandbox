package sayner.sandbox.repositories.impl;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sayner.sandbox.models.BranchShop;
import sayner.sandbox.models.User;
import sayner.sandbox.repositories.UserRepo;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

@Repository
@Log4j2
public class UserRepoImpl implements UserRepo {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepoImpl(EntityManagerFactory entityManagerFactory){
        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Override
    public List<User> getAllUsers() throws NullPointerException {

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery
                .select(root)
        ;

        Query<User> userQuery = session.createQuery(criteriaQuery);

        List<User> userList = userQuery.getResultList();

        session.getTransaction().commit();
        session.close();

        return userList;
    }
}
