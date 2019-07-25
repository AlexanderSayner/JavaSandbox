package sayner.sandbox.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sayner.sandbox.dto.ArticleDTO;
import sayner.sandbox.jsontemplate.ModelResponse;
import sayner.sandbox.services.impl.ArticleServiceImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class HelloController {

    @Autowired
    private ModelResponse modelResponse;
    @Autowired
    private ArticleServiceImpl articleService;

    @RequestMapping("/hello")
    public String sayHi() {
        return "Hi";
    }

    @GetMapping
    public ResponseEntity<Object> fillTheDatabase() throws IOException {

        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("=== Starting to fill the database ===");

        articleService.fillTheDatabase();

        logger.info("=== Entities have added to the database ===");

        return modelResponse.responseEntity(HttpStatus.OK, "like message", "{ status : operation complete }", null);
    }

}
