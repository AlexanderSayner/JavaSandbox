package sayner.sandbox.repositories.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sayner.sandbox.models.Article;
import sayner.sandbox.repositories.ArticleRepoHibernate;
import sayner.sandbox.utils.HibernateSessionFactoryUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Истольлование инструментоа Hibernate
 */
@Repository
public class ArticleRepoHibernateImpl implements ArticleRepoHibernate {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public Article findById(int id) {

        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Article.class, id);
    }

    @Override
    public List<Article> findAllLikeNameOrderByTitleUsingCriteriaQuery(String name) {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = criteriaQuery.from(Article.class);

        criteriaQuery
                .select(root)
                .where(criteriaBuilder.like(root.get("name"), "%" + name + "%"))
                .orderBy(criteriaBuilder.desc(root.get("title")));

        Query<Article> articleQuery = session.createQuery(criteriaQuery);

        return articleQuery.getResultList();

    }

    @Override
    public List<Article> findAllLikeManufacturer(String manufacturer) {

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = criteriaQuery.from(Article.class);

        criteriaQuery
                .select(root)
                .where(criteriaBuilder.like(root.get("manufacturer"), "%" + manufacturer + "%"))
                .orderBy(criteriaBuilder.asc(root.get("id")));

        Query<Article> articleQuery = session.createQuery(criteriaQuery);

        return articleQuery.getResultList();
    }
}
