package sayner.sandbox.repositories;

import sayner.sandbox.exceptions.ThereIsNoSuchArticleException;
import sayner.sandbox.models.Article;

import java.util.List;

public interface ArticleRepoHibernate {

    Article findById(int id) throws ThereIsNoSuchArticleException;

    List<Article> findAllLikeNameOrderByTitleUsingCriteriaQuery(String name) throws ThereIsNoSuchArticleException;

    List<Article> findAllLikeManufacturer(String manufacturer) throws ThereIsNoSuchArticleException;
}
