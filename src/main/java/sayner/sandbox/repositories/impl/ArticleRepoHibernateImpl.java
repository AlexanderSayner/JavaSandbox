package sayner.sandbox.repositories.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sayner.sandbox.exceptions.ThereIsNoSuchArticleException;
import sayner.sandbox.models.Article;
import sayner.sandbox.repositories.ArticleRepoHibernate;
import sayner.sandbox.utils.HibernateSessionFactoryUtil;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Iterator;
import java.util.List;

/**
 * Истольлование инструментоа Hibernate
 */
@Repository
public class ArticleRepoHibernateImpl implements ArticleRepoHibernate {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public Article findById(int id) {

        Article article = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Article.class, id);
        if (article == null) {
            logger.error("Check ThereIsNoSuchArticleException() in ArticleRepoHibernateImpl.findById(" + id + ")");
            throw new ThereIsNoSuchArticleException();
        }
        return article;
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

    /**
     * Сделано в одном методе для пущей наглядности
     */
    public void ArticleSoftDeleteMethod() {

        logger.info("=== Article Soft Delete ===");

        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Article article = new Article("aw_title", "aw_manufacturer", "Кефир", 45, "nope");

        // Push an entity to the context
        entityManager.persist(article);
        entityManager.flush();

        logger.info("=== Now an article in the persistent context===");
        logger.info("=== " + article.getState() + " ===");

        entityManager.getTransaction().commit();
        entityManager.close();

        logger.info("=== Article in the Database ===");
        logger.info("=== And Article can be read ===");

        entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // NamedQuery is annotated in the model class
        TypedQuery<Article> articleTypedQuery = entityManager.createNamedQuery("Article.FindByName", Article.class);
        articleTypedQuery.setParameter("name", "Кефир");

        try {

            int counter = 0;
            List<Article> articleList = articleTypedQuery.getResultList();
            logger.info("=== articleList.isEmpty(): " + articleList.isEmpty() + " ===");

            if (articleList.isEmpty()) {
                throw new ThereIsNoSuchArticleException();
            }

            for (Article ar :
                    articleList) {
                article = ar;
                logger.info("id is " + article.getId() + " | name is " + article.getName() + " | state is " + article.getState());
                counter++;
            }

            logger.info("=== Article is got by name-filter ===");
            logger.info("=== counter: " + counter + " ===");

        } catch (NoResultException nre) {

            logger.error("Article: NoResultException | message: " + nre.getMessage());
            logger.info("Article is not found by name-filter");
            article = new Article();

        } catch (NonUniqueResultException nure) {

            logger.error("Article: NonUniqueResultException | message: " + nure.getMessage());
            article = new Article();
        } catch (ThereIsNoSuchArticleException tinsae) {

            logger.error("Article: ThereIsNoSuchArticleException | message: " + tinsae.getMessage());
            article = new Article();
        }

        logger.info("=== An Article is read ===");
        logger.info("=== " + article.getState() + " ===");
        logger.info("=== Articles name: " + article.getName() + " ===");

        entityManager.remove(article);
        entityManager.flush();

        logger.info("=== An Article is really deleted now ===");
        logger.info("=== " + article.getState() + " ===");

        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
