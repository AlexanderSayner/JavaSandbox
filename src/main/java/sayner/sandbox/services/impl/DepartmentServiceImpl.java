package sayner.sandbox.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sayner.sandbox.models.TradeDepartment;
import sayner.sandbox.repositories.DepartmentRepoHibernate;
import sayner.sandbox.services.DepartmentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepoHibernate departmentRepoHibernate;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRES_NEW)
    public List<TradeDepartment> getAllDepartments() {

        List<TradeDepartment> tradeDepartments;
        Collection<TradeDepartment> tradeDepartmentCollection = this.departmentRepoHibernate.getAllDepartments();

        if (tradeDepartmentCollection == null) {
            log.error("====== tradeDepartmentCollection IS NULL !!! =======");
        } else {
            log.info("====== tradeDepartmentCollection is NOT NULL ======");
        }

        if (tradeDepartmentCollection instanceof List) {
            log.debug("====== tradeDepartmentCollection is instanceof List ======");
            tradeDepartments = (List) tradeDepartmentCollection;
        } else {
            tradeDepartments = new ArrayList<>(tradeDepartmentCollection);
            log.info("====== tradeDepartmentCollection is NOT instanceof List ======");
        }

        return tradeDepartments;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    public TradeDepartment getOneById(int id) {
        return this.departmentRepoHibernate.getDepartmentById(id);
    }

    @Override
    public TradeDepartment addDepartment(TradeDepartment department) {
        return this.departmentRepoHibernate.addOneDepartment(department);
    }

    @Override
    public TradeDepartment updateDepartment(TradeDepartment department) {
        return this.departmentRepoHibernate.updateDepartment(department);
    }

    @Override
    public TradeDepartment deleteDepartment(TradeDepartment department) {
        return this.departmentRepoHibernate.deleteOneDepartment(department);
    }

    @Override
    public void fillTheDatabase() {
        this.departmentRepoHibernate.addEntitiesToTheDatabase();
    }
}
