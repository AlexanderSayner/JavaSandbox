package sayner.sandbox.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sayner.sandbox.jsontemplate.ModelResponse;
import sayner.sandbox.services.BranchShopService;
import sayner.sandbox.services.impl.ArticleServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("/")
@Log4j2
public class HelloController {

    @Autowired
    private ModelResponse modelResponse;
    @Autowired
    private ArticleServiceImpl articleService;
    @Autowired
    private BranchShopService branchShopService;

    @RequestMapping("/hello")
    public String sayHi() {
        return "Hi";
    }

    @GetMapping
    public ResponseEntity<Object> fillTheDatabase() throws IOException {

        log.info("=== Starting to fill the database ===");

        Thread shopsThread = new Thread(() -> branchShopService.fillTheDatabase());
        Thread articleThread = new Thread(() -> articleService.fillTheDatabase());

        // Создаём побочные потоки и наслаждаемся быстрым откликом приложения
        shopsThread.start();
        articleThread.start();

        log.info("=== Process adding entities to the database has started===");

        class DtoClassWithData {
            private String status;

            public DtoClassWithData() {
            }

            public DtoClassWithData(String status) {
                this.status = status;
            }

            public String getStatus() {
                return status;
            }
        }

        return modelResponse.responseEntity(HttpStatus.OK, "like message", new DtoClassWithData("operation complete"), null);
    }

}
