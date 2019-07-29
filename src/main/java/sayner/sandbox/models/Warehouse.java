package sayner.sandbox.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.util.Set;

/**
 * Класс представляет собой филиал птовар на склад
 * В базе соответствующая таблица называется
 * Warehouse
 * <p>
 * #4
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class Warehouse {

    /**
     * Индентификатор БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Дата прибытия на склад
     * got_to_store_date
     */
    @Column
    private LocalDateTime gotToStore;

    /**
     * Дата изготовления
     * production_date
     */
    @Column
    private LocalDateTime productionDate;

    /**
     * Дата окончания срока годности
     * expiration_date
     */
    @Column
    private LocalDateTime expirationDate;

    @ManyToMany(mappedBy = "warehouses")
    private Set<Article> articles;

}
