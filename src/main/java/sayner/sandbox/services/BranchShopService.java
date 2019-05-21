package sayner.sandbox.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sayner.sandbox.models.BranchShop;
import sayner.sandbox.repositories.BranchShopRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchShopService {

    /**
     * Injects the repo
     */
    @Autowired
    public BranchShopRepository branchShopRepository;

    /**
     * Забирает весь список из базы
     *
     * @return
     */
    public List<BranchShop> getAllShops() {

        // сюда потом начнут складываться объекты из базы
        List<BranchShop> shopList = new ArrayList<>();

        // автоматически находит данные в таблице
        branchShopRepository.findAll()
                .forEach(shopList::add);

        return shopList;
    }

    /**
     * находит необходимый по его id
     *
     * @param id
     * @return
     */
    public BranchShop getOneById(int id) {

        return branchShopRepository.findById(id).get();
    }

    /**
     * Добавить новый
     *
     * @param shop
     */
    public void addShop(BranchShop shop) {

        branchShopRepository.save(shop);
    }

    /**
     * Изменить по ай-дишнику, хранящийся в переданнном объекте
     *
     * @param shop
     */
    public void updateShop(BranchShop shop) {

        branchShopRepository.save(shop);
    }

    /**
     * удаляет по ай-дишнику
     *
     * @param id
     */
    public void deleteShop(int id) {

        branchShopRepository.deleteById(id);
    }
}
