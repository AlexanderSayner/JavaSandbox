package sayner.sandbox.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sayner.sandbox.models.BranchShop;

import java.util.Arrays;
import java.util.List;

@RestController
public class ShopsController {

    @RequestMapping("/shops")
    public List<BranchShop> getAllShops() {
        return Arrays.asList(
                new BranchShop("Деева", "Магазин №1"),
                new BranchShop("Деева", "Магазин №2"),
                new BranchShop("Деева", "Магазин №3")
        );
    }
}
