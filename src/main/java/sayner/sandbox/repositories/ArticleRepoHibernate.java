package sayner.sandbox.repositories;

import org.springframework.stereotype.Repository;
import sayner.sandbox.models.Article;

public interface ArticleRepoHibernate {

    Article findById(int id);
}
