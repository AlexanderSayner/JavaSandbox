package sayner.sandbox.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sayner.sandbox.models.BranchShop;
import sayner.sandbox.repositories.ShopsRepoHibernate;
import sayner.sandbox.services.BranchShopService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
// @__ is especially for javac 7 (why not)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class BranchShopServiceImpl implements BranchShopService {

    /**
     * Injects the repo
     */
    private final ShopsRepoHibernate shopsRepoHibernate;

    /**
     * Забирает весь список из базы
     *
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRES_NEW)
    public List<BranchShop> getAllShops() {

        //TESTING
        this.shopsRepoHibernate.oneToManyCheckExample();
        //TESTING

        List<BranchShop> shops;
        Collection<BranchShop> branchShopCollection = shopsRepoHibernate.getAllShops();

        if (branchShopCollection == null) {
            log.error("====== branchShopCollection IS NULL !!! =======");
        } else {
            log.info("====== branchShopCollection is NOT NULL ======");
        }

        if (branchShopCollection instanceof List) {
            log.debug("====== branchShopCollection is instanceof List ======");
            shops = (List) branchShopCollection;
        } else {
            shops = new ArrayList<>(branchShopCollection);
            log.info("====== branchShopCollection is NOT instanceof List ======");
        }

        return shops;
    }

    /**
     * находит необходимый по его id
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    public BranchShop getOneById(int id) {

        return shopsRepoHibernate.getShopById(id);
    }

    /**
     * Добавить новый
     *
     * @param shop
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW)
    public BranchShop addShop(BranchShop shop) {

        return shopsRepoHibernate.addOneShop(shop);
    }

    /**
     * Изменить по ай-дишнику, хранящийся в переданнном объекте
     *
     * @param shop
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public BranchShop updateShop(BranchShop shop) {

        return shopsRepoHibernate.updateShop(shop);
    }

    /**
     * удаляет по ай-дишнику
     *
     * @param shop
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public BranchShop deleteShop(BranchShop shop) {

        return shopsRepoHibernate.deleteOneShop(shop);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void fillTheDatabase() {
        this.shopsRepoHibernate.
                addEntitiesToTheDatabase();
    }
}
