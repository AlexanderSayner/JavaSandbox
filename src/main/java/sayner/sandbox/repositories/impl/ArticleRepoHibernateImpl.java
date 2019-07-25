package sayner.sandbox.repositories.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sayner.sandbox.exceptions.ThereIsNoSuchArticleException;
import sayner.sandbox.models.Article;
import sayner.sandbox.repositories.ArticleRepoHibernate;
import sayner.sandbox.utils.HibernateSessionFactoryUtil;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        if (article != null) {
            logger.info("=== Article is not null ===");
        } else {
            logger.info("=== Article is null ===");
        }
        entityManager.flush();

        // Теперь у article другое имя

        logger.info("=== Now an article in the persistent context===");
        logger.info("=== " + article.getState() + " ===");

        entityManager.getTransaction().commit();
        entityManager.close();

        logger.info("=== Article in the Database ===");
        logger.info("=== And Article can be read ===");

        //==========================================================================================

        entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // NamedQuery is annotated in the model class
        TypedQuery<Article> articleTypedQuery = entityManager.createNamedQuery("Article.FindByName", Article.class);
        articleTypedQuery.setParameter("name", "Кефир");

        // По имени, ясн-понятно ничего не найдёт с таким id

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

        //==========================================================================

        // id-то не менялся, поэтому должен выдать с новым именем

        logger.info("=== An Article is really deleted now ===");
        logger.info("=== " + article.getState() + " ===");

        Article artcl = entityManager.find(Article.class, article.getId());
        if (artcl == null) {
            logger.info("=== Результат, article = null");
        } else {
            logger.error("=== Результат: article != null, id = " + article.getId());
            logger.info("=== Articles name: " + artcl.getName() + " ===");
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        //==========================================================================================

        entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // По id найдёт легко

        try {

            article = entityManager.find(Article.class, article.getId());

            logger.info("=== article найден, state = " + article.getState());
        } catch (NullPointerException npe) {

            logger.info("=== article не найден ===");
        } finally {

            logger.info("=== Операция по поиску удалённого объекта завершена ===");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    /**
     *
     */
    public void OneMoreCheck() {

        logger.info("\n");
        logger.info("=================================================");
        logger.info("=== Persistent context check has been started ===");
        logger.info("=================================================");
        logger.info("\n");

//        //////////////////////////////////////////////////////////////////////////////////////////////
//        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        logger.info("=== Creating entity manager ===");
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        logger.info("=== Opening a transaction ===");
        entityManager.getTransaction().begin();

        logger.info("=== Creating an experimental entity ===");
        Article article = new Article("aw_experimental", "aw+experimental_manufacturer", "Lab rat", 200, "phhh");

        logger.info("=== Push it into the context ===");
        entityManager.persist(article);
        entityManager.flush();

        logger.info("=== Commit transaction ===");
        entityManager.getTransaction().commit();
        entityManager.close();

//        //////////////////////////////////////////////////////////////////////////////////////////////
//        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        logger.info("=== Creating new entity manager ===");
        entityManager = this.entityManagerFactory.createEntityManager();
        logger.info("=== Opening a transaction ===");
        entityManager.getTransaction().begin();

        logger.info("=== Find entity by id ===");
        Article founded_article = entityManager.find(Article.class, article.getId());

        if (founded_article == null) {
            logger.info("=== Результат, article = null, id was " + article.getId() + " ===");
        } else {
            logger.error("=== Результат: article != null, id = " + article.getId() + " ===");
            logger.info("=== Articles name: " + founded_article.getName() + " ===");
        }

        logger.info("=== Commit transaction ===");
        entityManager.getTransaction().commit();
        entityManager.close();

//        //////////////////////////////////////////////////////////////////////////////////////////////
//        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        logger.info("=== Creating entity manager ===");
        entityManager = this.entityManagerFactory.createEntityManager();
        logger.info("=== Opening a transaction ===");
        entityManager.getTransaction().begin();

        logger.info("=== Now change name of the article entity and repeat the experiment");
        article.setName("Lab rabbit");
        logger.info("=== This doesn't change anything ===");

        logger.info("=== Trying to merge entity ===");
        // Если попытаться сделать persist, то всё упадёт с ошибкой org.springframework.dao.InvalidDataAccessApiUsageException
        // После вызова entityManager.close() сущность перешла в состояние detached
        // persist создаёт новую, для него неприемлимо существование id
        // merge переводит сущность из состояния detached в persist, и все изменения синхронизируются с базой
        entityManager.merge(article);
        logger.info("=== Let's see on what it affected ===");
        entityManager.flush();
        logger.info("=== And it really works ===");

        // One more experiment inside
        //**************************
        logger.info("=== Trying to change persistent entity ===");
        article.setName("How does the persistent update work?");
        logger.info("=== No any effect ===");

        entityManager.flush();
        logger.info("=== No any effect again===");

        entityManager.merge(article);
        logger.info("=== But now only merge works brilliant ===");
        //**************************

        logger.info("=== Find entity by id ===");
        founded_article = entityManager.find(Article.class, article.getId());

        if (founded_article == null) {
            logger.info("=== Результат, article = null, id was " + article.getId() + " ===");
        } else {
            logger.error("=== Результат: article != null, id = " + article.getId() + " ===");
            logger.info("=== Articles name: " + founded_article.getName() + " ===");
        }


        logger.info("=== Commit transaction ===");
        entityManager.getTransaction().commit();
        entityManager.close();

//        //////////////////////////////////////////////////////////////////////////////////////////////
//        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        logger.info("=== Finished ===");
    }

    /**
     * Объявлять транзакцию нужно в сервисе, но здесь эксперимент
     */
    public void ThirdCheck() {

        logger.info("\n");
        logger.info("===================================================================");
        logger.info("=== Persistent context check has been started (Session Factory) ===");
        logger.info("===================================================================");
        logger.info("\n");

//        //////////////////////////////////////////////////////////////////////////////////////////////
//        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        logger.info("=== Open the session ===");
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        logger.info("=== Begin transaction ===");
        session.beginTransaction();

        logger.info("=== Creating an experimental entity ===");
        Article article = new Article("session_experimental", "session_experimental_manufacturer", "session rat", 200, "phhh");

//        //////////////////////////////////////////////////////////////////////////////////////////////
//        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        logger.info("=== Push it into the context ===");
        session.persist(article);
        session.flush();

        logger.info("=== Find entity by id ===");
        Article founded_article = session.find(Article.class, article.getId());

        if (founded_article == null) {
            logger.info("=== Результат, article = null, id was " + article.getId() + " ===");
        } else {
            logger.error("=== Результат: article != null, id = " + article.getId() + " ===");
            logger.info("=== Articles name: " + founded_article.getName() + " ===");
        }

        logger.info("=== Commit transaction ===");
        session.getTransaction().commit();
        logger.info("=== Closing session ===");
        session.close();

//        //////////////////////////////////////////////////////////////////////////////////////////////
//        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        logger.info("=== Open the session ===");
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        logger.info("=== Begin transaction ===");
        session.beginTransaction();

        logger.info("=== Now change name of the article entity and repeat the experiment");
        article.setName("Session rabbit");

        logger.info("=== Trying to merge entity ===");
        session.merge(article);

        logger.info("=== Find entity by id ===");
        founded_article = session.find(Article.class, article.getId());

        if (founded_article == null) {
            logger.info("=== Результат, article = null, id was " + article.getId() + " ===");
        } else {
            logger.error("=== Результат: article != null, id = " + article.getId() + " ===");
            logger.info("=== Articles name: " + founded_article.getName() + " ===");
        }

        logger.info("=== That's good, SessionFactory works well too ===");

        logger.info("=== Commit transaction ===");
        session.getTransaction().commit();
        logger.info("=== Closing session ===");
        session.close();

//        //////////////////////////////////////////////////////////////////////////////////////////////
//        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        logger.info("=== Finished ===");
    }

    /**
     *
     */
    public void addEntitiesToTheDatabase() {

        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        int counter = 1000;
        while (--counter > 0) {

            Article article = new Article("aw_title" + "_" + counter % 7, "aw_manufacturer" + "_" + counter % 2, "Кефир" + "_" + counter, 45, "nope");

            entityManager.persist(article);
            entityManager.flush();
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        logger.info("=== From repo: all articles have been added to the database");
    }
}
