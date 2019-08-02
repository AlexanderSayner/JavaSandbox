package sayner.sandbox.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sayner.sandbox.dto.ArticleDTO;
import sayner.sandbox.dto.SingleResponseObjectDto;
import sayner.sandbox.dto.StatusEnum;
import sayner.sandbox.dto.extd.SingleResponseObjectDtpExt;
import sayner.sandbox.exceptions.NotFoundByIdException;
import sayner.sandbox.exceptions.ThereIsNoSuchArticleException;
import sayner.sandbox.jsontemplate.jview.ArticleViewDto;
import sayner.sandbox.jsontemplate.jview.SingleResponseObjectDtoView;
import sayner.sandbox.mappers.ArticleMapper;
import sayner.sandbox.models.Article;
import sayner.sandbox.models.Warehouse;
import sayner.sandbox.services.ArticleService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Управление каталогом
 */
@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class ArticleController {

    ArticleMapper articleMapper = ArticleMapper.INSTANCE;

    private final ArticleService articleService;


    @GetMapping(value = "/cache")
    public SingleResponseObjectDtpExt<Object> getMyCacheTesting() throws IOException {

        this.articleService.cacheChecking();
        return new SingleResponseObjectDtpExt(StatusEnum.AllDoneWell, "Cache test has started", true, null);
    }

    /**
     * Отображает каталог товаров
     *
     * @return
     */
    @GetMapping
    @JsonView(SingleResponseObjectDtoView.StatusCodeMessageSuccessDataOrExceptionOperationDateAndTime.class)
    public SingleResponseObjectDtpExt<Object> getAllArticlesTest() throws IOException {


        Warehouse warehouse = new Warehouse();
        Set<Warehouse> warehouses = new HashSet<>();
        warehouses.add(warehouse);

        log.debug("This is an {} message.", "info");
        log.info("This is an info message");
        log.error("This is an error message");

        articleService.method1();

        ArticleMapper articleMapper = ArticleMapper.INSTANCE;

        Article article = new Article("df", "ag", "dsf", 13, "hai", warehouses);
        ArticleDTO articleDTO = articleMapper.toArticleDTO(article);
        Article transformed_article = articleMapper.toArticle(articleDTO);

        log.info(article.toString());
        System.out.println(article.toString());
        log.info(articleDTO.toString());
        System.out.println(articleDTO.toString());
        log.info(transformed_article.toString());
        System.out.println(transformed_article.toString());

        List<Article> articleList = new ArrayList<>();
        articleList.add(article);

        return new SingleResponseObjectDtpExt(StatusEnum.AllDoneWell, "Any information", true, articleMapper.toArticleDTOs(articleService.getAllArticles()));
    }

    /**
     * Воздащает по стрингоовому id товар,
     * преобразование типов осуществляет соответствующий геттер
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    @JsonView(SingleResponseObjectDtoView.StatusCodeMessageSuccessDataOrExceptionOperationDateAndTime.class)
    public SingleResponseObjectDto getArticle(@PathVariable int id) throws NotFoundByIdException {

        SingleResponseObjectDto singleResponseObjectDto = new SingleResponseObjectDtpExt<>(
                StatusEnum.AllDoneWell,
                "Вот вам Article",
                true,
                articleMapper.toArticleDTO(articleService.getAnArticle(id))
        );

//        Article articleOne = articleService.getAnArticle(id);
//        Article articleTwo = articleService.getAnArticle(id);
//
//        System.out.println("The first one" + articleOne.toString());
//        System.out.println("The second one" + articleTwo.toString());
//
//        System.out.println(articleOne.getCreationDateTime());
//        System.out.println(articleTwo.getCreationDateTime());
//
//        System.out.println("СРАВНЕНИЕ equals");
//        System.out.println(articleTwo.equals(articleOne));
//
//        System.out.println("СРАВНЕНИЕ hashCode");
//        System.out.println(articleTwo.hashCode() == articleOne.hashCode());

        return singleResponseObjectDto;

    }

    /**
     * Эксперименты с сессией таам всякие
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/session", params = {"name"})
    @JsonView(ArticleViewDto.IdStateTitleManufacturerNameCreationDate.class)
    public SingleResponseObjectDto getSomeArticlesByCriteriaSession(@RequestParam("name") String name) {

        SingleResponseObjectDto singleResponseObjectDto = new SingleResponseObjectDtpExt<>(
                StatusEnum.AllDoneWell,
                "Success. What about any hibernate sessions?",
                true,
                articleMapper.toArticleDTOs(articleService.getArticlesUsingCriteriaSession(name))
        );

        return singleResponseObjectDto;
    }

    @GetMapping(value = "/session", params = {"manufacturer"})
    @JsonView(ArticleViewDto.IdStateTitleManufacturerNameCreationDate.class)
    public SingleResponseObjectDto getSomeArticlesByntityFactoryManagerCriteriaSession(@RequestParam("manufacturer") String manufacturer) {

        SingleResponseObjectDto singleResponseObjectDto = new SingleResponseObjectDtpExt<>(
                StatusEnum.AllDoneWell,
                "Success. What about any hibernate sessions?",
                true,
                articleMapper.toArticleDTOs(articleService.getArticlesLikeManufacturerUsingCriteriaSession(manufacturer))
        );

        return singleResponseObjectDto;
    }


    /**
     * Фильтрация с использованием CriteriaBuilder
     *
     * @return
     */
    @GetMapping(value = "/criteria", params = {"by", "value"})
    @JsonView(ArticleViewDto.IdStateTitleManufacturerNameCreationDate.class)
    public SingleResponseObjectDto getFromCriteriaBuilder(@RequestParam("by") String filtered_by,
                                                         @RequestParam("value") String value) {

        SingleResponseObjectDto singleResponseObjectDto = new SingleResponseObjectDtpExt<>(
                StatusEnum.AllDoneWell,
                "Success. What about any hibernate sessions?",
                true,
                articleMapper.toArticleDTOs(articleService.criterian(filtered_by, value))
        );

        return singleResponseObjectDto;
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
    @JsonView(ArticleViewDto.IdStateTitleManufacturerNameCreationDate.class)
    public SingleResponseObjectDto filterByName(
            @RequestParam("name") String name
    ) {

        SingleResponseObjectDto singleResponseObjectDto = new SingleResponseObjectDtpExt<>(
                StatusEnum.AllDoneWell,
                "Success. What about any hibernate sessions?",
                true,
                articleMapper.toArticleDTOs(articleService.findArticleLikeName(name))
        );

        return singleResponseObjectDto;
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
    @JsonView(ArticleViewDto.IdStateTitleManufacturerNameCreationDate.class)
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
    @JsonView(ArticleViewDto.IdStateTitleManufacturerNameCreationDate.class)
    public List<Article> findBName(@RequestParam("name") String name) {

        return articleService.findByName(name);
    }

    @GetMapping(value = "filter", params = "manufacturer")
    @JsonView(ArticleViewDto.IdStateTitleManufacturerNameCreationDate.class)
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
