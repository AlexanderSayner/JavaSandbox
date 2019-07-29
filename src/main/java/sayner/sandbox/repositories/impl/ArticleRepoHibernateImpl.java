package sayner.sandbox.repositories.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sayner.sandbox.exceptions.ThereIsNoSuchArticleException;
import sayner.sandbox.models.Article;
import sayner.sandbox.models.Warehouse;
import sayner.sandbox.repositories.ArticleRepoHibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Истольлование инструментоа Hibernate
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class ArticleRepoHibernateImpl implements ArticleRepoHibernate {

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public List<Article> filterFlexibility(String filtered_by, String value) {

        List<Article> articleList = new ArrayList<>();

        Session session = this.entityManagerFactory.unwrap(SessionFactory.class).openSession();
        session.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> articleRoot = criteriaQuery.from(Article.class);

        criteriaQuery.select(articleRoot);
        criteriaQuery.where(criteriaBuilder.equal(articleRoot.get(filtered_by), value));
        articleList = session.createQuery(criteriaQuery).getResultList();

        session.getTransaction().commit();
        session.close();

        return articleList;
    }

    @Override
    public Article findById(int id) {

        Session session = this.entityManagerFactory.unwrap(SessionFactory.class).openSession();
        session.getTransaction().begin();

        Article article = session.get(Article.class, id);

        if (article == null) {
            log.error("Check ThereIsNoSuchArticleException() in ArticleRepoHibernateImpl.findById(" + id + ")");
            session.getTransaction().rollback();
            session.close();
            throw new ThereIsNoSuchArticleException();
        }

        session.getTransaction().commit();
        session.close();

        return article;
    }

    @Override
    public List<Article> findAllLikeNameOrderByTitleUsingCriteriaQuery(String name) {

        Session session = this.entityManagerFactory.unwrap(SessionFactory.class).openSession();
        session.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = criteriaQuery.from(Article.class);

        criteriaQuery
                .select(root)
                .where(criteriaBuilder.like(root.get("name"), "%" + name + "%"))
                .orderBy(criteriaBuilder.desc(root.get("title")));

        Query<Article> articleQuery = session.createQuery(criteriaQuery);

        List<Article> articleList = articleQuery.getResultList();

        session.getTransaction().commit();
        session.close();

        return articleList;
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
    @Override
    public void ArticleSoftDeleteMethod() {

        log.info("=== Article Soft Delete ===");

        Warehouse warehouse = new Warehouse();
        Set<Warehouse> warehouses = new HashSet<>();
        warehouses.add(warehouse);

        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Article article = new Article("aw_title", "aw_manufacturer", "Кефир", 45, "nope", warehouses);

        // Push an entity to the context
        log.info("=== Persist an article ===");
        entityManager.persist(article);
        log.info("=== Persist a warehouse ===");
        entityManager.persist(warehouse);


        if (article != null) {
            log.info("=== Article is not null ===");
        } else {
            log.info("=== Article is null ===");
        }
        entityManager.flush();

        log.info("=== Now an article in the persistent context===");
        log.info("=== " + article.getState() + " ===");

        entityManager.getTransaction().commit();
        entityManager.close();

        log.info("=== Article in the Database ===");
        log.info("=== And Article can be read ===");

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
            log.info("=== articleList.isEmpty(): " + articleList.isEmpty() + " ===");

            if (articleList.isEmpty()) {
                throw new ThereIsNoSuchArticleException();
            }

            for (Article ar :
                    articleList) {
                article = ar;
                log.info("id is " + article.getId() + " | name is " + article.getName() + " | state is " + article.getState());
                counter++;
            }

            log.info("=== Article is got by name-filter ===");
            log.info("=== counter: " + counter + " ===");

        } catch (NoResultException nre) {

            log.error("Article: NoResultException | message: " + nre.getMessage());
            log.info("Article is not found by name-filter");
            article = new Article();

        } catch (NonUniqueResultException nure) {

            log.error("Article: NonUniqueResultException | message: " + nure.getMessage());
            article = new Article();
        } catch (ThereIsNoSuchArticleException tinsae) {

            log.error("Article: ThereIsNoSuchArticleException | message: " + tinsae.getMessage());
            article = new Article();
        }

        log.info("=== An Article is read ===");
        log.info("=== " + article.getState() + " ===");
        log.info("=== Articles name: " + article.getName() + " ===");

        entityManager.remove(article);
        entityManager.flush();

        //==========================================================================

        // id-то не менялся, поэтому должен выдать с новым именем

        log.info("=== An Article is really deleted now ===");
        log.info("=== " + article.getState() + " ===");

        Article artcl = entityManager.find(Article.class, article.getId());
        if (artcl == null) {
            log.info("=== Результат, article = null");
        } else {
            log.error("=== Результат: article != null, id = " + article.getId());
            log.info("=== Articles name: " + artcl.getName() + " ===");
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        //==========================================================================================

        entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // По id найдёт легко

        try {

            article = entityManager.find(Article.class, article.getId());

            log.info("=== article найден, state = " + article.getState());
        } catch (NullPointerException npe) {

            log.info("=== article не найден ===");
        } finally {

            log.info("=== Операция по поиску удалённого объекта завершена ===");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    /**
     *
     */
    @Override
    public void OneMoreCheck() {

        log.info("\n");
        log.info("=================================================");
        log.info("=== Persistent context check has been started ===");
        log.info("=================================================");
        log.info("\n");


        Warehouse warehouse = new Warehouse();
        Set<Warehouse> warehouses = new HashSet<>();
        warehouses.add(warehouse);

//        //////////////////////////////////////////////////////////////////////////////////////////////
//        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        log.info("=== Creating entity manager ===");
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        log.info("=== Opening a transaction ===");
        entityManager.getTransaction().begin();

        log.info("=== Creating an experimental entity ===");
        Article article = new Article("aw_experimental", "aw+experimental_manufacturer", "Lab rat", 200, "phhh", warehouses);

        log.info("=== Push it into the context ===");
        entityManager.persist(article);
        log.info("=== Push many to many warehouses into the context ===");
        entityManager.persist(warehouse);
        entityManager.flush();

        log.info("=== Commit transaction ===");
        entityManager.getTransaction().commit();
        entityManager.close();

//        //////////////////////////////////////////////////////////////////////////////////////////////
//        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        log.info("=== Creating new entity manager ===");
        entityManager = this.entityManagerFactory.createEntityManager();
        log.info("=== Opening a transaction ===");
        entityManager.getTransaction().begin();

        log.info("=== Find entity by id ===");
        Article founded_article = entityManager.find(Article.class, article.getId());

        if (founded_article == null) {
            log.info("=== Результат, article = null, id was " + article.getId() + " ===");
        } else {
            log.error("=== Результат: article != null, id = " + article.getId() + " ===");
            log.info("=== Articles name: " + founded_article.getName() + " ===");
        }

        log.info("=== Commit transaction ===");
        entityManager.getTransaction().commit();
        entityManager.close();

//        //////////////////////////////////////////////////////////////////////////////////////////////
//        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        log.info("=== Creating entity manager ===");
        entityManager = this.entityManagerFactory.createEntityManager();
        log.info("=== Opening a transaction ===");
        entityManager.getTransaction().begin();

        log.info("=== Now change name of the article entity and repeat the experiment");
        article.setName("Lab rabbit");
        log.info("=== This doesn't change anything ===");

        log.info("=== Trying to merge entity ===");
        // Если попытаться сделать persist, то всё упадёт с ошибкой org.springframework.dao.InvalidDataAccessApiUsageException
        // После вызова entityManager.close() сущность перешла в состояние detached
        // persist создаёт новую, для него неприемлимо существование id
        // merge переводит сущность из состояния detached в persist, и все изменения синхронизируются с базой
        entityManager.merge(article);
        log.info("=== Let's see on what it affected ===");
        entityManager.flush();
        log.info("=== And it really works ===");

        // One more experiment inside
        //**************************
        log.info("=== Trying to change persistent entity ===");
        article.setName("How does the persistent update work?");
        log.info("=== No any effect ===");

        entityManager.flush();
        log.info("=== No any effect again===");

        entityManager.merge(article);
        log.info("=== But now only merge works brilliant ===");
        //**************************

        log.info("=== Find entity by id ===");
        founded_article = entityManager.find(Article.class, article.getId());

        if (founded_article == null) {
            log.info("=== Результат, article = null, id was " + article.getId() + " ===");
        } else {
            log.error("=== Результат: article != null, id = " + article.getId() + " ===");
            log.info("=== Articles name: " + founded_article.getName() + " ===");
        }


        log.info("=== Commit transaction ===");
        entityManager.getTransaction().commit();
        entityManager.close();

//        //////////////////////////////////////////////////////////////////////////////////////////////
//        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        log.info("=== Finished ===");
    }

    /**
     * Объявлять транзакцию нужно в сервисе, но здесь эксперимент
     * <p>
     * В примере OneMoreCheck() merge спользовался для обновления данных сущности,
     * в ThirdCheck() достаёт сущность из базы, поэтому merge(warehouse) обязателен
     */
    @Override
    public void ThirdCheck() {

        log.info("\n");
        log.info("===================================================================");
        log.info("=== Persistent context check has been started (Session Factory) ===");
        log.info("===================================================================");
        log.info("\n");

        Warehouse warehouse = new Warehouse();
        Set<Warehouse> warehouses = new HashSet<>();
        warehouses.add(warehouse);

//        //////////////////////////////////////////////////////////////////////////////////////////////
//        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        log.info("=== Open the session ===");
        SessionFactory sessionFactory = this.entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        log.info("=== Begin transaction ===");
        session.beginTransaction();

        log.info("=== Creating an experimental entity ===");
        Article article = new Article("session_experimental", "session_experimental_manufacturer", "session rat", 200, "phhh", warehouses);

//        //////////////////////////////////////////////////////////////////////////////////////////////
//        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        log.info("=== Push it into the context ===");
        session.persist(warehouse);
        session.persist(article);
        session.flush();

        log.info("=== Find entity by id ===");
        Article founded_article = session.find(Article.class, article.getId());

        if (founded_article == null) {
            log.info("=== Результат, article = null, id was " + article.getId() + " ===");
        } else {
            log.error("=== Результат: article != null, id = " + article.getId() + " ===");
            log.info("=== Articles name: " + founded_article.getName() + " ===");
        }

        log.info("=== Commit transaction ===");
        session.getTransaction().commit();
        log.info("=== Closing session ===");
        session.close();

//        //////////////////////////////////////////////////////////////////////////////////////////////
//        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        log.info("=== Open the session ===");
        session = sessionFactory.openSession();
        log.info("=== Begin transaction ===");
        session.beginTransaction();

        log.info("=== Now change name of the article entity and repeat the experiment");
        article.setName("Session rabbit");

        log.info("=== Trying to merge entity ===");
        session.merge(warehouse); // have to merge it too
        session.merge(article);

        log.info("=== Find entity by id ===");
        founded_article = session.find(Article.class, article.getId());

        if (founded_article == null) {
            log.info("=== Результат, article = null, id was " + article.getId() + " ===");
        } else {
            log.error("=== Результат: article != null, id = " + article.getId() + " ===");
            log.info("=== Articles name: " + founded_article.getName() + " ===");
        }

        log.info("=== That's good, SessionFactory works well too ===");

        log.info("=== Commit transaction ===");
        session.getTransaction().commit();
        log.info("=== Closing session ===");
        session.close();

//        //////////////////////////////////////////////////////////////////////////////////////////////
//        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        log.info("=== Finished ===");
    }

    /**
     *
     */
    @Override
    public void addEntitiesToTheDatabase() {

        EntityManager entityManager = this.entityManagerFactory.createEntityManager();

        Warehouse warehouse = new Warehouse();
        Set<Warehouse> warehouses = new HashSet<>();
        warehouses.add(warehouse);

        int counter = 1000;
        while (--counter > 0) {

            entityManager.getTransaction().begin();

            Article article = new Article("aw_title" + "_" + counter % 7, "aw_manufacturer" + "_" + counter % 2, "Кефир" + "_" + counter, 45, "nope", warehouses);

            entityManager.persist(article);
            entityManager.flush();


            entityManager.getTransaction().commit();
        }

        entityManager.close();

        log.info("=== From repo: all articles have been added to the database");
    }
}
