package sayner.sandbox.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sayner.sandbox.dao.ArticleRepository;
import sayner.sandbox.models.Article;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    public ArticleRepository articleRepository;

    public List<Article> getAllArticles() {

        List<Article> articles = new ArrayList<>();

        Iterable<Article> articleIterable = articleRepository.findAll();

        if (articleIterable != null) {
            articleIterable.forEach(articles::add);
        }

        return articles;
    }
}