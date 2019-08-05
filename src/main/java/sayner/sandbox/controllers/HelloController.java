package sayner.sandbox.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sayner.sandbox.dto.SingleResponseObjectDto;
import sayner.sandbox.dto.StatusEnum;
import sayner.sandbox.dto.extd.SingleResponseObjectDtpExt;
import sayner.sandbox.services.ArticleService;
import sayner.sandbox.services.BranchShopService;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@RestController
@RequestMapping("/")
@Log4j2
public class HelloController {

    private final ArticleService articleService;

    private final BranchShopService branchShopService;

    @GetMapping("/hello")
    public String sayHi() {
        return "Hi";
    }

    @GetMapping
    public SingleResponseObjectDto fillTheDatabase() throws IOException {

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

        SingleResponseObjectDto singleResponseObjectDto = new SingleResponseObjectDtpExt<>(
                StatusEnum.AllDoneWell,
                "Механизм заполнения базы запущем",
                true,
                new DtoClassWithData("operation complete")
        );

        return singleResponseObjectDto;
    }

}
