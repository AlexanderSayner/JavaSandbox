package sayner.sandbox.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sayner.sandbox.exceptions.ThereIsNoSuchArticleException;
import sayner.sandbox.jsontemplate.ModelResponse;
import sayner.sandbox.jsontemplate.ResponseHandler;
import sayner.sandbox.jsontemplate.jblick.ArticleView;
import sayner.sandbox.models.Article;
import sayner.sandbox.services.ArticleServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Управление каталогом
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;
    @Autowired
    private ModelResponse modelResponse;

    /**
     * Отображает каталог товаров
     *
     * @return
     */
    @GetMapping
    @JsonView(ArticleView.IdTitleDate.class)
    public ResponseEntity<Object> getAllArticlesTest() throws IOException {


        //ResponseHandler responseHandler = new ResponseHandler();


        Logger logger = LoggerFactory.getLogger(this.getClass());


        logger.debug("This is an {} message.", "info");
        logger.info("This is an info message");
        logger.error("This is an error message");

        articleService.method1();


        return modelResponse.responseEntity(HttpStatus.OK, "like message", articleService.getAllArticles(), null);

        //return responseHandler.generateResponse(HttpStatus.OK, true, "Success", articleService.getAllArticles());
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

    /**
     * Pagination
     *
     * @param page
     * @param size
     * @param uriComponentsBuilder
     * @param response
     * @return
     */
    @GetMapping(params = {"page", "size"})
    public List<Article> findPaginated(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            UriComponentsBuilder uriComponentsBuilder,
            HttpServletResponse response
    ) {

        Page<Article> resultPage = articleService.findPaginated(page, size);

        if (page > resultPage.getTotalPages()) {

            throw new ThereIsNoSuchArticleException();
        }

        return resultPage.getContent();
    }

    /**
     * has to find
     *
     * @param name
     * @return
     */
    @GetMapping(params = {"name"})
    public List<Article> filterByName(
            @RequestParam("name") String name
    ) {
        return articleService.findArticlesByName(name);
    }

    /**
     * Вот уже фиьлтруем несколько значений
     *
     * @param filtername
     * @param filtermanufacturer
     * @return
     */
    @GetMapping(params = {"name", "manufacturer"})
    public List<Article> filterByNameAndManufacturer(
            @RequestParam("name") String filtername,
            @RequestParam("manufacturer") String filtermanufacturer
    ) {
        return articleService.findArticlesByNameAndManufacturer(filtername, filtermanufacturer);
    }

    /**
     * ФИльтрация с примером
     *
     * @param page
     * @param size
     * @param article
     * @param uriComponentsBuilder
     * @param response
     * @return
     */
    @PostMapping(value = "filter")
    @JsonView(ArticleView.IdTitleDate.class)
    public Page<Article> filterByExample(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestBody Article article,
            UriComponentsBuilder uriComponentsBuilder,
            HttpServletResponse response
    ) {
        Article article_ = new Article("Молоко", "Волжские просторы");
        return articleService.findByExample(page, size, article_);
    }

    @GetMapping(value = "filter", params = "name")
    @JsonView(ArticleView.IdTitleManufacturerName.class)
    public List<Article> findBName(@RequestParam("name") String name) {

        return articleService.findByName(name);
    }

    @GetMapping(value = "filter", params = "manufacturer")
    @JsonView(ArticleView.IdTitleManufacturerName.class)
    public List<Article> findByManufacturer(@RequestParam("manufacturer") String manufacturer) {

        System.out.println(manufacturer);
        return articleService.findByManufacturer(manufacturer);
    }

    @GetMapping(value = "filter", params = {"name", "manufacturer"})
    public List<Article> secondFilterByNameAndManufacturer(
            @RequestParam("name") String filtername,
            @RequestParam("manufacturer") String filtermanufacturer
    ) {
        return articleService.findArticlesByNameAndManufacturer(filtername, filtermanufacturer);
    }

    @GetMapping(value = "native")
    public List<Article> findNavite() {

        return articleService.findNativeAll();
    }
}
