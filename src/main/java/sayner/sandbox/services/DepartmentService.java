package sayner.sandbox.services;

import sayner.sandbox.models.TradeDepartment;

import java.util.List;

public interface DepartmentService {

    List<TradeDepartment> getAllDepartments();

    TradeDepartment getOneById(int id);

    TradeDepartment addDepartment(TradeDepartment department);

    TradeDepartment updateDepartment(TradeDepartment department);

    TradeDepartment deleteDepartment(TradeDepartment department);

    void fillTheDatabase();
}
