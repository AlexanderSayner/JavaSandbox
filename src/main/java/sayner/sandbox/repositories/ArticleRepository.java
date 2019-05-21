package sayner.sandbox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import sayner.sandbox.models.Article;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
}
