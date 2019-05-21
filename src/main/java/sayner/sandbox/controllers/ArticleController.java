package sayner.sandbox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sayner.sandbox.models.Article;
import sayner.sandbox.services.ArticleService;

import java.util.List;

/**
 * Управление каталогом
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * Отображает каталог товаров
     *
     * @return
     */
    @RequestMapping("/articles")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    /**
     * Воздащает по стрингоовому id товар,
     * преобразование типов осуществляет соответствующий геттер
     *
     * @param id
     * @return
     */
    @RequestMapping("/articles/{id}")
    public Article getArticle(@PathVariable int id) {
        return articleService.getAnArticle(id);
    }

    /**
     * Выполнится при вызове метода POST на URL /articles
     * В POST Request отправляется объект классса Article
     */
    @RequestMapping(method = RequestMethod.POST, value = "/articles")
    public void addArticle(@RequestBody Article article) {
        articleService.addArticle(article);
    }


    /**
     * Обычный update запрос, ничгео особенного
     *
     * @param article
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/articles")
    public void updateArticle(@RequestBody Article article) {
        articleService.updateArticle(article);
    }

    /**
     * Удаляет по ай-дишнику
     * @param id
     */
    @RequestMapping(method = RequestMethod.DELETE,value = "/articles/{id}")
    public void deleteArticle(@PathVariable int id){
        articleService.deleteArticle(id);
    }
}
