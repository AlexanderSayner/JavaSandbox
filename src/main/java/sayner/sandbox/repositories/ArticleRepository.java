package sayner.sandbox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sayner.sandbox.modelle.Article;

/**
 * Once we extend PagingAndSortingRepository,
 * we can add our own methods that take Pageable and Sort as parameters,
 * as we did here with findAllByPrice.
 * JpaRepository extends PagingAndSortingRepository extends CrudRepository.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>,
        JpaSpecificationExecutor<Article> {
}
