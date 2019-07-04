package sayner.sandbox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sayner.sandbox.modelle.Article;

import java.util.List;

/**
 * Once we extend PagingAndSortingRepository,
 * we can add our own methods that take Pageable and Sort as parameters,
 * as we did here with findAllByPrice.
 * JpaRepository extends PagingAndSortingRepository extends CrudRepository.
 * Repository is the base repository that doesn't have any methods
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>,
        JpaSpecificationExecutor<Article> {

    List<Article> findByTitleStartsWith(String titleStartsWith);

    List<Article> findByName(String name);

    List<Article> findByManufacturer(String manufacturer);

    List<Article> findByTitleLike(String titleLike);

    @Query(value = "SELECT * FROM Articles_List", nativeQuery = true)
    List<Article> findNativeAll();



    /*

@Query(value = "SELECT * FROM Users u WHERE u.status = :status and u.name = :name",
  nativeQuery = true)
User findUserByStatusAndNameNamedParamsNative(
  @Param("status") Integer status, @Param("name") String name);

     */
}
