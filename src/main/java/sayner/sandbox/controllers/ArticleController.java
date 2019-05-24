package sayner.sandbox.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sayner.sandbox.jsonpattern.ResponseHandler;
import sayner.sandbox.jsonpattern.jviews.ArticleView;
import sayner.sandbox.models.Article;
import sayner.sandbox.services.ArticleService;

import java.time.LocalDateTime;

/**
 * Управление каталогом
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * Отображает каталог товаров
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> getAllArticlesTest() {

        ResponseHandler responseHandler = new ResponseHandler();

        return responseHandler.generateResponse(HttpStatus.OK, true, "Success",
                articleService.getAllArticles());
    }

    /**
     * Воздащает по стрингоовому id товар,
     * преобразование типов осуществляет соответствующий геттер
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    @JsonView(ArticleView.IdTitleDate.class)
    public ResponseEntity<Object> getArticle(@PathVariable int id) {
        ResponseHandler responseHandler = new ResponseHandler();

        return responseHandler.generateResponse(HttpStatus.OK, true, "Success",
                articleService.getAnArticle(id));
    }

    /**
     * Выполнится при вызове метода POST на URL /articles
     * В POST Request отправляется объект классса Article
     */
    @PostMapping
    public void addArticle(@RequestBody Article article) {
        article.setCreationDateTime(LocalDateTime.now());
        articleService.addArticle(article);
    }


    /**
     * Обычный update запрос, ничгео особенного
     *
     * @param article
     */
    @PutMapping
    public void updateArticle(@RequestBody Article article) {
        articleService.updateArticle(article);
    }

    /**
     * Удаляет по ай-дишнику
     *
     * @param id
     */
    @DeleteMapping(value = "/{id}")
    public void deleteArticle(@PathVariable int id) {
        articleService.deleteArticle(id);
    }
}
