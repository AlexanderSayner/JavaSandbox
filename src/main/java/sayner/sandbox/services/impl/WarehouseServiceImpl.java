package sayner.sandbox.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sayner.sandbox.models.Warehouse;
import sayner.sandbox.repositories.WarehouseRepoHiberanate;
import sayner.sandbox.services.WarehouseService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepoHiberanate warehouseRepoHiberanate;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRES_NEW)
    public List<Warehouse> getAllWarehouses() {

        List<Warehouse> warehouses;
        Collection<Warehouse> warehouseCollection = this.warehouseRepoHiberanate.getAllWarehouses();

        if (warehouseCollection == null) {
            log.error("====== warehouseCollection IS NULL !!! =======");
        } else {
            log.info("====== warehouseCollection is NOT NULL ======");
        }

        if (warehouseCollection instanceof List) {
            log.debug("====== warehouseCollection is instanceof List ======");
            warehouses = (List) warehouseCollection;
        } else {
            warehouses = new ArrayList<>(warehouseCollection);
            log.info("====== warehouseCollection is NOT instanceof List ======");
        }

        return warehouses;
    }

    @Override
    public Warehouse getWarehouseById(int id) {
        return null;
    }

    @Override
    public Warehouse addWarehouse(Warehouse warehouse) {
        return null;
    }

    @Override
    public Warehouse updateWarehouse(Warehouse warehouse) {
        return null;
    }

    @Override
    public Warehouse deleteWarehouse(Warehouse warehouse) {
        return null;
    }

    @Override
    public void fillTheDatabase() {

    }
}
