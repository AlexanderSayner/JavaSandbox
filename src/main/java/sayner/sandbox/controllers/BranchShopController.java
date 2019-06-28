package sayner.sandbox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sayner.sandbox.modelle.BranchShop;
import sayner.sandbox.services.BranchShopService;

import java.util.List;

/**
 * Управление магазинами
 */
@RestController
public class BranchShopController {

    @Autowired
    public BranchShopService branchShopService;

    /**
     * Отображает все магазины
     *
     * @return
     */
    @RequestMapping("/shops")
    public List<BranchShop> getAllShops() {

        return branchShopService.getAllShops();
    }

    /**
     * Отображает один магазин по его ай-ди
     *
     * @param id
     * @return
     */
    @RequestMapping("/shops/{id}")
    public BranchShop getShop(@PathVariable int id) {

        return branchShopService.getOneById(id);
    }

    /**
     * Добавить новый магазин
     *
     * @param shop
     */
    @RequestMapping(method = RequestMethod.POST, value = "/shops")
    public void addShop(@RequestBody BranchShop shop) {

        branchShopService.addShop(shop);
    }

    /**
     * Обновить параметры магазина
     *
     * @param shop
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/shops")
    public void updateShop(@RequestBody BranchShop shop) {

        branchShopService.updateShop(shop);
    }

    /**
     * Удаляет по ай-дишнику
     *
     * @param id
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "shops/{id}")
    public void deleteShop(@PathVariable int id) {

        branchShopService.deleteShop(id);
    }
}
