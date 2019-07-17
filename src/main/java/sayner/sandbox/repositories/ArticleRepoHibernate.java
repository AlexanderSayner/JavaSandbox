package sayner.sandbox.repositories;

import sayner.sandbox.models.Article;

public interface ArticleRepoHibernate {

    Article findById(int id);
}
