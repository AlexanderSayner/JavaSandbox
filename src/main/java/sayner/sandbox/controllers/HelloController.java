package sayner.sandbox.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
