package sayner.sandbox.dao;

import org.springframework.data.repository.CrudRepository;
import sayner.sandbox.models.Article;

public interface ArticleRepository extends CrudRepository<Article, String> {
}