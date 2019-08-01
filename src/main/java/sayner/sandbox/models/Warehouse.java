package sayner.sandbox.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CacheConcurrencyStrategy;
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
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    @Column(name = "warehouse_id")
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

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToMany(mappedBy = "warehouses", fetch = FetchType.LAZY)
    private Set<Article> articles;

    /**
     * Min contructor
     *
     * @param articles
     */
    public Warehouse(Set<Article> articles) {
        this.articles = articles;
    }

    /**
     * extended getter'ы & setter'ы
     */

    @JsonIgnore
    public Set<Article> getArticles() {
        return this.articles;
    }
}
