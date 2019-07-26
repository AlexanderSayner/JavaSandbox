package sayner.sandbox.repositories;

import sayner.sandbox.models.BranchShop;

import java.util.Collection;

public interface ShopsRepoHibernate {

    Collection<BranchShop> getAllShops();

    BranchShop getShopById(int id);

    // must return added
    BranchShop addOneShop(BranchShop shop);

    // must return updated
    BranchShop updateShop(BranchShop shop);

    // must return deleted
    BranchShop deleteOneShop(BranchShop shop);
}
