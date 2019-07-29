package sayner.sandbox.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

/**
 * Класс представляет собой отделения магазина
 * В базе ссоответствующая таблица называется
 * Trade_Departments
 * <p>
 * #2
 */
@Entity
@Table(name = "Trade_Departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class TradeDepartment {

    /**
     * Индентификатор БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_department_id")
    private Integer id;

    /**
     * Предназначение
     */
    @Column
    private String appointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_shop_id")
    private BranchShop branchShop;

    public TradeDepartment(String appointment, BranchShop branchShop) {
        this.appointment = appointment;
        this.branchShop = branchShop;
    }

}
