package sayner.sandbox.repositories;

import sayner.sandbox.models.Article;

import java.util.List;

public interface ArticleRepoHibernate {

    Article findById(int id);

    List<Article> findAllLikeNameOrderByTitleUsingCriteriaQuery(String name);

    List<Article> findAllLikeManufacturer(String manufacturer);
}
