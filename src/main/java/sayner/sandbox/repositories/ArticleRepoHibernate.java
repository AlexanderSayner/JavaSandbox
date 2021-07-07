package sayner.sandbox.repositories;

import sayner.sandbox.exceptions.NotFoundByIdException;
import sayner.sandbox.exceptions.ThereIsNoSuchArticleException;
import sayner.sandbox.models.Article;

import java.util.List;

public interface ArticleRepoHibernate extends RootRepoHibernate {

    void save(Article article);

    Article findById(int id) throws NotFoundByIdException;

    List<Article> findAllLikeNameOrderByTitleUsingCriteriaQuery(String name) throws ThereIsNoSuchArticleException;

    List<Article> findAllLikeManufacturer(String manufacturer) throws ThereIsNoSuchArticleException;

    List<Article> filterFlexibility(String filtered_by, String value);

    Integer getLastIdFromArticles();

    void articleSoftDeleteMethod();

    void oneMoreCheck();

    void thirdCheck();

    void addEntitiesToTheDatabase();

    void secondLevelCacheCheck();
}
