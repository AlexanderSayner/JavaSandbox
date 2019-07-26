package sayner.sandbox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sayner.sandbox.models.BranchShop;
import sayner.sandbox.services.impl.BranchShopServiceImpl;

import java.util.List;

/**
 * Управление магазинами
 */
@RestController
@RequestMapping("/shops")
public class BranchShopController {

    @Autowired
    public BranchShopServiceImpl branchShopService;

    /**
     * Отображает все магазины
     *
     * @return
     */
    @GetMapping
    public List<BranchShop> getAllShops() {

        return branchShopService.getAllShops();
    }

    /**
     * Отображает один магазин по его ай-ди
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
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
     * @param shop
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "shops/{id}")
    public void deleteShop(@RequestBody BranchShop shop) {

        branchShopService.deleteShop(shop);
    }
}
