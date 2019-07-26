package sayner.sandbox.services;

import sayner.sandbox.models.BranchShop;

import java.util.List;

public interface BranchShopService {

    List<BranchShop> getAllShops();

    BranchShop getOneById(int id);

    BranchShop addShop(BranchShop shop);

    BranchShop updateShop(BranchShop shop);

    BranchShop deleteShop(BranchShop shop);

    void fillTheDatabase();
}
