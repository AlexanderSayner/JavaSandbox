package sayner.sandbox.repositories;

import sayner.sandbox.models.Warehouse;

import java.util.Collection;

public interface WarehouseRepoHiberanate {

    Collection<Warehouse> getAllWarehouses();

    Warehouse getWarehouseById(int id);

    // must return added
    Warehouse addOneWarehouse(Warehouse warehouse);

    // must return updated
    Warehouse updateWarehouse(Warehouse warehouse);

    // must return deleted
    Warehouse deleteOneWarehouse(Warehouse warehouse);

    void addEntitiesToTheDatabase();
}
