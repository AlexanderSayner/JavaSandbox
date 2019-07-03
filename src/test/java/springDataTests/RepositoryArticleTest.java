package springDataTests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sayner.sandbox.modelle.Article;
import sayner.sandbox.repositories.ArticleRepository;
import sayner.sandbox.services.ArticleServiceImpl;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RepositoryArticleTest {

    ArticleServiceImpl articleService = new ArticleServiceImpl();

    public RepositoryArticleTest() {
    }

    @Test
    public void testFindByTitleStartsWith() {

        List<Article> articleList = new ArrayList<>();

        articleList=articleService.getAllArticles();

        articleList.forEach(article -> System.out.println(article.getId() + article.getTitle()));
    }
}