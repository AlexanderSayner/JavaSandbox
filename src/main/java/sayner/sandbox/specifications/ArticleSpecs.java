package sayner.sandbox.specifications;

import org.springframework.data.jpa.domain.Specification;
import sayner.sandbox.models.Article ;

public class ArticleSpecs {

    /*
    public Specification<Article> getArticlesByName(String name){

        return new Specification<Article>() {

            @Override
            public Predicate toPredicate(
                    Root<Article> root,
                    CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder
            ) {
                Predicate equalPredicate =criteriaBuilder.equal(root.get("name"), name);
                return equalPredicate;
            }
        };
    }

     */

    /**
     * Фильтрация по имени
     *
     * @param name
     * @return
     */
    public Specification<Article> getArticlesByName(String name) {

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    /**
     * Фильтрация по производителю
     *
     * @param manufactorer
     * @return
     */
    public Specification<Article> getArticlesByManufactorer(String manufactorer) {

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("manufacturer"), manufactorer);
    }
}
