package sayner.sandbox.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.util.Set;

/**
 * Класс представляет собой филиал магазина
 * В базе соответствующая таблица называется
 * Branch_shops
 * <p>
 * #1
 */
@Entity
@Table(name = "Branch_shops")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class BranchShop {

    /**
     * Индентификатор БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_shop_id")
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

    // mappedBy = "НАЗВАНИЕ_ПОЛЯ_В_ДРУГОЙ_СУЩНОСТИ_С_КОТОРЫМ_БУДЕТ_СВЯЗАНО_ДАННОЕ_ПОЛЕ"
    @OneToMany(mappedBy = "branchShop", fetch = FetchType.LAZY)
    private Set<TradeDepartment> tradeDepartments;

    public BranchShop(String region, String city, String street, String name) {

        this.setRegion(region);
        this.setCity(city);
        this.setStreet(street);
        this.setName(name);
    }

    @JsonIgnore // to avoid an error: Could not write JSON: failed to lazily initialize a collection of role
    public Set<TradeDepartment> getTradeDepartments() {
        return this.tradeDepartments;
    }
}
