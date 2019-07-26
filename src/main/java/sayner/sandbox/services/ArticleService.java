package sayner.sandbox.services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import sayner.sandbox.models.Article;

public interface ArticleService {

    void addArticle(@RequestBody Article article);

    void updateArticle(@RequestBody Article article);

    void deleteArticle(@PathVariable int id);
}
