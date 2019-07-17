package sayner.sandbox.services;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import sayner.sandbox.models.Article;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ArticleService {

    void addArticle(@RequestBody Article article);

    void updateArticle(@RequestBody Article article);

    void deleteArticle(@PathVariable int id);
}
