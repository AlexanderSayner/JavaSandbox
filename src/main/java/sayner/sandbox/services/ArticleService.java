package sayner.sandbox.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import sayner.sandbox.ausgenommen.ThereIsNoSuchArticleException;
import sayner.sandbox.modelle.Article;
import sayner.sandbox.repositories.ArticleRepository;
import sayner.sandbox.specifications.ArticleSpecs;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class ArticleService {

    /**
     * Подключение репозитория
     * Injects the ArticleRepository instance
     */
    @Autowired
    public ArticleRepository articleRepository;

    //
    // logic methods
    //

    /**
     * Забрать весь список из базы
     *
     * @return
     */
    public List<Article> getAllArticles() {

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
    public Article getAnArticle(int id) {

        Article articleFromDB = new Article();

        try {
            articleFromDB = articleRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new ThereIsNoSuchArticleException();
        }

        return articleFromDB;
    }

    /**
     * Добавляем в наш список новый товар
     *
     * @param article
     */
    @Transactional
    public void addArticle(Article article) {

        articleRepository.save(article);
    }

    /**
     * Изменить что-то в списке товаров
     * Метод .save одинаково работает как для add, так и для update,
     * объект, передающийся в метод уже содержит в себе id,
     * а .save посмотрит содержится ли уже такой в базе, а седловательно так и выполнит запрос
     *
     * @param article
     */
    @Transactional
    public void updateArticle(Article article) {

        articleRepository.save(article);

    }

    /**
     * Удаляет позицию из списка по id
     *
     * @param id
     */
    @Transactional
    public void deleteArticle(int id) {

        articleRepository.deleteById(id);
    }


    /**
     * Pagination
     *
     * @param page
     * @param size
     * @return
     */
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
    public List<Article> findArticlesByName(String name) {

        ArticleSpecs specs = new ArticleSpecs();

        return articleRepository.findAll(specs.getArticlesByName(name));
    }

    /**
     * Пример использования Specification.where
     *
     * @param name
     * @param manufacturer
     * @return
     */
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
    public Page<Article> findByExample(int page, int size, Article exampleArticle) {

        Pageable firstPageWithTwoElements = PageRequest.of(page, size, Sort.by("id").descending().and(Sort.by("name").ascending()));

        Example<Article> example = Example.of(exampleArticle);

        return articleRepository.findAll(example, firstPageWithTwoElements);
    }
}
