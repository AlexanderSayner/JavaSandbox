package sayner.sandbox.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sayner.sandbox.dto.ArticleDTO;

import java.util.Arrays;
import java.util.List;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String sayHi() {
        return "Hi";
    }

    @RequestMapping("/")
    public String index() {
        return "Welcome";
    }

    @RequestMapping("/getWithoutRequestParam")
    public List<Object> getWithoutRequestParam(ArticleDTO personDTO) {
        return Arrays.asList(
                personDTO.getAnyDate(),
                personDTO.getId(),
                personDTO.getName());
    }
}
