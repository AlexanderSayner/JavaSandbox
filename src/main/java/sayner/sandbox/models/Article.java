package sayner.sandbox.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import sayner.sandbox.models.enums.ArticleState;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * Класс представляет собой каталог
 * В базе соответствующая таблица называется
 * Article
 * <p>
 * #3
 */
@Entity
// Override default hibernate delete operation
@SQLDelete(sql = "UPDATE Articles_List SET state = 'DELETED' WHERE id = ?", check = ResultCheckStyle.COUNT)
// By default: @SQLDelete(sql = "DELETE from Articles_List WHERE id = ?", check = ResultCheckStyle.COUNT)
@NamedQuery(name = "Article.FindByName", query = "from Article a WHERE a.name like :name")
@Table(name = "Articles_List")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class Article {

    /**
     * Индентификатор БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Integer id;

    @Column
    @Enumerated(EnumType.STRING)
    private ArticleState state;

    /**
     * Наименование товара
     */
    @Column
    private String title;

    /**
     * Производитель
     */
    @Column
    private String manufacturer;

    /**
     * Имя, данное производителем изделия
     */
    @Column
    private String name;

    /**
     * Масса в СИ
     */
    @Column
    private Double massSi;

    /**
     * Гарантия
     */
    @Column
    private String guarantee;

    @Column(updatable = false, name = "creation_date_time")
    private LocalDateTime creationDateTime;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Article_Warehouse",
            joinColumns = {@JoinColumn(name = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "warehouse_id")}
    )
    private Set<Warehouse> warehouses;

    /**
     * extended getter'ы & setter'ы
     */

    public String getStringId() {
        return String.valueOf(getId());
    }

    public Set<Warehouse> getWarehouses() {
        return this.warehouses;
    }

    /**
     * Конструктор для ленивых
     *
     * @param title
     * @param manufacturer
     */
    public Article(String title, String manufacturer) {
        this.title = title;
        this.manufacturer = manufacturer;
        this.name = title + "_" + manufacturer;
        this.massSi = 1d;
        this.guarantee = "нет";
        this.setWarehouses(null);
    }

    /**
     * Конструкор устанавливает некоторые параметры
     *
     * @param title
     * @param manufacturer
     * @param name
     * @param mass_si
     * @param garantee
     */
    public Article(String title, String manufacturer, String name, double mass_si, String garantee, Set<Warehouse> warehouses) {
        this.title = title;
        this.manufacturer = manufacturer;
        this.name = name;
        this.massSi = mass_si;
        this.guarantee = garantee;
        this.setWarehouses(warehouses);
    }

    /**
     * Конструкор устанавливает некоторые параметры + дата создания
     *
     * @param title
     * @param manufacturer
     * @param name
     * @param mass_si
     * @param garantee
     */
    public Article(String title, String manufacturer, String name, double mass_si, String garantee, Set<Warehouse> warehouses, LocalDateTime creationDateTime) {
        this.title = title;
        this.manufacturer = manufacturer;
        this.name = name;
        this.massSi = mass_si;
        this.guarantee = garantee;
        this.setWarehouses(warehouses);
        this.creationDateTime = creationDateTime;
    }

    /**
     * logic
     */

    @PreRemove
    public void deleteAnArticle() {
        log.info("Set to state DELETED");
        this.setState(ArticleState.DELETED);
    }

    /**
     * Overrides
     */

    /**
     * Возращает основные поля
     *
     * @return
     */
    @Override
    public String toString() {

        return "Article entity: " + this.id + ", " + this.name + ", " + this.title + ", " + this.manufacturer + ".";
    }

    /**
     * хэш число на основе всех полей объекта
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                this.title,
                this.manufacturer,
                this.name,
                this.id,
                this.guarantee,
                this.massSi,
                this.creationDateTime,
                this.updatedAt
        );
    }

    /**
     * Осталось с датой решить
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (this.getClass() != o.getClass()) {
            return false;
        } else {
            Article anotherArticle = (Article) o;
            if (this.id != anotherArticle.id)
                return false;
            if (!this.title.equals(anotherArticle.title))
                return false;
            if (!this.manufacturer.equals(anotherArticle.manufacturer))
                return false;
            if (!this.name.equals(anotherArticle.name))
                return false;
            if (this.massSi != anotherArticle.massSi)
                return false;
            // если они оба null, то считается как соответвие, и проверка идёт дальше
            if (this.guarantee != null && anotherArticle.guarantee != null) { //проверка на null
                if (!this.guarantee.equals(anotherArticle.guarantee)) //проверка на соответствие
                    return false;
            }
//            if (this.creationDateTime != anotherArticle.creationDateTime)
//                return false;
//            if (this.updatedAt != anotherArticle.updatedAt)
//                return false;
        }
        return true;
    }
}
