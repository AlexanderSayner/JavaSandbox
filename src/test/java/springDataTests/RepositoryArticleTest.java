package springDataTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sayner.sandbox.models.Article;
import sayner.sandbox.repositories.ArticleRepoHibernate;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:application.properties")
@SpringBootTest
public class RepositoryArticleTest {

//    @Autowired
//    private ArticleRepoHibernate articleRepoHibernate;

    public RepositoryArticleTest() {
    }

    @Test
    public void LoggerTest() {

        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("TEST LOGGER RECORD");
    }

//    @Test
//    public void RepoTest() {
//
//        Article article = this.articleRepoHibernate.findById(1);
//        System.out.println(article.getId());
//    }

}