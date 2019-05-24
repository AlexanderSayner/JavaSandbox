package sayner.sandbox.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sayner.sandbox.exceptions.ThereIsNoSuchArticleException;
import sayner.sandbox.models.Article;
import sayner.sandbox.repositories.ArticleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
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
    public void updateArticle(Article article) {

        articleRepository.save(article);

    }

    /**
     * Удаляет позицию из списка по id
     *
     * @param id
     */
    public void deleteArticle(int id) {

        articleRepository.deleteById(id);
    }
}
