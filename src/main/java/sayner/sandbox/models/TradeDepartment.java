package sayner.sandbox.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class TradeDepartment {

    /**
     * Индентификатор БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Предназначение
     */
    @Column
    private String appointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Branch_shops", nullable = false)
    private BranchShop branchShop;

}
