package sayner.sandbox.services;

import sayner.sandbox.models.Warehouse;

import java.util.List;

public interface WarehouseService {

    List<Warehouse> getAllWarehouses();

    Warehouse getWarehouseById(int id);

    Warehouse addWarehouse(Warehouse warehouse);

    Warehouse updateWarehouse(Warehouse warehouse);

    Warehouse deleteWarehouse(Warehouse warehouse);

    void fillTheDatabase();
}
