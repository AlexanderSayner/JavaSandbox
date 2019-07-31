package sayner.sandbox.repositories;

import sayner.sandbox.models.TradeDepartment;

import java.util.Collection;

public interface DepartmentRepoHibernate {

    Collection<TradeDepartment> getAllDepartments();

    TradeDepartment getDepartmentById(int id);

    // must return added
    TradeDepartment addOneDepartment(TradeDepartment department);

    // must return updated
    TradeDepartment updateDepartment(TradeDepartment department);

    // must return deleted
    TradeDepartment deleteOneDepartment(TradeDepartment department);

    void addEntitiesToTheDatabase();
}
