package sayner.sandbox.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import sayner.sandbox.annotations.Annotation1;
import sayner.sandbox.annotations.SenselessTransaction;
import sayner.sandbox.exceptions.ThereIsNoSuchArticleException;
import sayner.sandbox.models.Article;
import sayner.sandbox.repositories.ArticleRepoHibernate;
import sayner.sandbox.repositories.ArticleRepository;
import sayner.sandbox.services.ArticleService;
import sayner.sandbox.specifications.ArticleSpecs;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class ArticleServiceImpl implements ArticleService {

    /**
     * Тёплый и ламповый способ
     */
    private final ArticleRepository articleRepository;

    /**
     * Хардкорный и беспощадный
     */
    private final ArticleRepoHibernate articleRepoHibernate;

    //
    // logic methods
    //

    @Override
    @SenselessTransaction
    public List<Article> criterian(String filtered_by, String value) {

        return this.articleRepoHibernate.filterFlexibility(filtered_by, value);
    }

    /**
     * Забрать весь список из базы
     *
     * @return
     */
    @Override
    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.READ_UNCOMMITTED,
            propagation = Propagation.REQUIRED
    )
    public List<Article> getAllArticles() {

        // TESTING
        this.articleRepoHibernate.ArticleSoftDeleteMethod();
        this.articleRepoHibernate.OneMoreCheck();
        this.articleRepoHibernate.ThirdCheck();
        // TESTING

        // сюда потом начнут складываться объекты из базы
        List<Article> articles = new ArrayList<>();


        Iterable<Article> articleIterable = articleRepository.findAll();

        if (articleIterable != null) {
            articleIterable.forEach(articles::add);
        }


        return articles;
    }


    /**
     * Получить все позиции из сиписка
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRED
    )
    public Article getAnArticle(int id) {

        Article articleFromDB = new Article();

//            articleFromDB = articleRepository.findById(id).orElseThrow(ThereIsNoSuchArticleException::new);
        articleFromDB = articleRepoHibernate.findById(id);

        return articleFromDB;
    }

    @Override
    @SenselessTransaction
    public List<Article> getArticlesUsingCriteriaSession(String name) {

        return articleRepoHibernate.findAllLikeNameOrderByTitleUsingCriteriaQuery(name);
    }

    @Override
    @SenselessTransaction
    public List<Article> getArticlesLikeManufacturerUsingCriteriaSession(String manufacturer) {

        return articleRepoHibernate.findAllLikeManufacturer(manufacturer);
    }

    /**
     * Добавляем в наш список новый товар
     *
     * @param article
     */
    @Override
    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.READ_UNCOMMITTED,
            propagation = Propagation.REQUIRES_NEW)
    public void addArticle(Article article) {

        articleRepository.save(article);

        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // trigger rollback programmatically
    }

    /**
     * Изменить что-то в списке товаров
     * Метод .save одинаково работает как для add, так и для update,
     * объект, передающийся в метод уже содержит в себе id,
     * а .save посмотрит содержится ли уже такой в базе, а седловательно так и выполнит запрос
     *
     * @param article
     */
    @Override
    @SenselessTransaction
    public void updateArticle(Article article) throws ThereIsNoSuchArticleException {

        articleRepository.save(article);
    }

    /**
     * Удаляет позицию из списка по id
     *
     * @param id
     */
    @Override
    @Transactional
    public void deleteArticle(int id) {

        Article article = articleRepository.findById(id).orElseThrow(ThereIsNoSuchArticleException::new);

        articleRepository.deleteById(id);
    }


    /**
     * Pagination
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Article> findPaginated(int page, int size) {

        // page num and how many articles in it sorted by id and name (in entity class)
        Pageable firstPageWithTwoElements = PageRequest.of(page, size, Sort.by("id").descending().and(Sort.by("name").ascending()));

        Page<Article> paginatedArticles = articleRepository.findAll(firstPageWithTwoElements);

        return paginatedArticles;
    }

    /**
     * it has to find articles by name
     *
     * @param name
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Article> findArticlesByName(String name) {

        ArticleSpecs specs = new ArticleSpecs();

        if (name != "") {
            return articleRepository.findAll(specs.getArticlesByName(name));
        } else {
            return articleRepository.findAll(specs.getAllArticles());
        }
    }

    /**
     * Ищет подобные значения по колонке name
     *
     * @param like_this
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    public List<Article> findArticleLikeName(String like_this) {

        like_this = "%" + like_this + "%";

        ArticleSpecs specs = new ArticleSpecs();

        return articleRepository.findAll(specs.getNameLike(like_this));
    }

    /**
     * Пример использования Specification.where
     *
     * @param name
     * @param manufacturer
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public List<Article> findArticlesByNameAndManufacturer(String name, String manufacturer) {

        ArticleSpecs specs = new ArticleSpecs();

        return articleRepository.findAll(Specification
                .where(specs.getArticlesByName(name))
                .and(specs.getArticlesByManufactorer(manufacturer)));
    }


    /**
     * Фильтрация  +  построничный вывод
     *
     * @param page
     * @param size
     * @param exampleArticle
     * @return
     */
    @Override
    public Page<Article> findByExample(int page, int size, Article exampleArticle) {

        Pageable firstPageWithTwoElements = PageRequest.of(page, size, Sort.by("id").descending().and(Sort.by("name").ascending()));

        Example<Article> example = Example.of(exampleArticle);

        return articleRepository.findAll(example, firstPageWithTwoElements);
    }


    @Override
    public List<Article> findByName(String name) {

        List<Article> articlesFromDB = new ArrayList<>();

        try {
            articlesFromDB = articleRepository.findByName(name);
        } catch (NoSuchElementException ex) {
            throw new ThereIsNoSuchArticleException();
        }

        return articlesFromDB;
    }


    @Override
    @Annotation1
    public void method1() {
        System.out.println("method1");
        this.method2();
    }

    @Override
    @Annotation1
    public void method2() {
        System.out.println("method2");
    }


    @Override
    public List<Article> findByManufacturer(String manufacturer) {

        return articleRepository.findByManufacturer(manufacturer);
    }

    @Override
    public List<Article> findByTitleLike(String titleLike) {

        return articleRepository.findByTitleLike(titleLike);
    }


    @Override
    public List<Article> findNativeAll() {

        return articleRepository.findNativeAll();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void fillTheDatabase() {
        this.articleRepoHibernate.addEntitiesToTheDatabase();
    }
}
