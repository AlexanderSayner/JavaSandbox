package sayner.sandbox.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sayner.sandbox.log.LogbackConfigXml;
import sayner.sandbox.models.Article;
import sayner.sandbox.services.ArticleService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<Article> getAllArticles() throws IOException {

        LogbackConfigXml logbackConfigXml = new LogbackConfigXml();
        logbackConfigXml.performTask();

        return articleService.getAllArticles();
    }
}