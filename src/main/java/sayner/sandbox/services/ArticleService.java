package sayner.sandbox.services;

import org.springframework.data.domain.Page;
import sayner.sandbox.exceptions.NotFoundByIdException;
import sayner.sandbox.exceptions.ThereIsNoSuchArticleException;
import sayner.sandbox.models.Article;

import java.util.List;

public interface ArticleService {

    Article cloneArticle(Integer id);

    Article addArticle(Article article);

    void updateArticle(Article article) throws ThereIsNoSuchArticleException;

    void deleteArticle(int id) throws ThereIsNoSuchArticleException;

    List<Article> getAllArticles();

    List<Article> criterian(String filtered_by, String value);

    Article getAnArticle(int id) throws NotFoundByIdException;

    List<Article> getArticlesUsingCriteriaSession(String name);

    List<Article> getArticlesLikeManufacturerUsingCriteriaSession(String manufacturer);

    Page<Article> findPaginated(int page, int size);

    List<Article> findArticlesByName(String name);

    List<Article> findArticleLikeName(String like_this);

    List<Article> findArticlesByNameAndManufacturer(String name, String manufacturer);

    Page<Article> findByExample(int page, int size, Article exampleArticle);

    List<Article> findByName(String name);

    void method1();

    void method2();

    List<Article> findByManufacturer(String manufacturer);

    List<Article> findByTitleLike(String titleLike);

    List<Article> findNativeAll();

    void fillTheDatabase();

    void cacheChecking();
}
