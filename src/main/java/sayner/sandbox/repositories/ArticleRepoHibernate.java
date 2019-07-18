package sayner.sandbox.repositories;

import org.springframework.stereotype.Repository;
import sayner.sandbox.models.Article;

import java.util.List;

public interface ArticleRepoHibernate {

    Article findById(int id);

    List<Article> findAllOrderByNameUsingCriteriaQuery(String name);
}