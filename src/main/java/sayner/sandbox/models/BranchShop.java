package sayner.sandbox.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

/**
 * Класс представляет собой филиал магазина
 * В базе соответствующая таблица называется
 * Branch_shops
 * <p>
 * #1
 */
@Entity
@Table(name = "Branch_shops")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class BranchShop {

    /**
     * Индентификатор БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Регион или область страны
     */
    @Column
    private String region;

    /**
     * Город магазина
     */
    @Column
    private String city;

    /**
     * Улицы магазина
     */
    @Column
    private String street;

    /**
     * Название данного, конкретного магазина, чтобы их можно было различить
     */
    @Column
    private String name;

}
