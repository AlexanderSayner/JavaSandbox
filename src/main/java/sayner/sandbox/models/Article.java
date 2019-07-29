package sayner.sandbox.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import sayner.sandbox.jsontemplate.jview.ArticleView;
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
    @JsonView(ArticleView.Id.class)
    private Integer id;

    @Column
    @Enumerated(EnumType.STRING)
    private ArticleState state;

    /**
     * Наименование товара
     */
    @Column
    @JsonView(ArticleView.IdTitle.class)
    private String title;

    /**
     * Производитель
     */
    @Column
    @JsonView({ArticleView.IdTitleManufacturer.class, ArticleView.Id.class})
    private String manufacturer;

    /**
     * Имя, данное производителем изделия
     */
    @Column
    @JsonView(ArticleView.IdTitleManufacturerName.class)
    private String name;

    /**
     * Масса в СИ
     */
    @Column
    @JsonView(ArticleView.FullArticle.class)
    private Double mass_si;

    /**
     * Гарантия
     */
    @Column
    @JsonView(ArticleView.IdTitleManufacturerNameGarantee.class)
    private String garantee;

    @Column(updatable = false, name = "creation_date_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM HH:mm")
    @JsonView(ArticleView.IdTitleDate.class)
    private LocalDateTime creationDateTime;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
//
//    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "Article_Warehouse",
//            joinColumns = {@JoinColumn(name = "article_id")},
//            inverseJoinColumns = {@JoinColumn(name = "warehouse_id")}
//    )
//    private Set<Warehouse> warehouses;

    /**
     * extended getter'ы & setter'ы
     */

    public String getStringId() {
        return String.valueOf(getId());
    }

//    @JsonIgnore
//    public Set<Warehouse> getWarehouses() {
//        return this.warehouses;
//    }

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
        this.mass_si = 1d;
        this.garantee = "нет";
    }

    /**
     * Конструкор устанавливает разом все параметры, кроме id
     * т.к. он инкреминтируется автоматически на уровне СУБД
     *
     * @param title
     * @param manufacturer
     * @param name
     * @param mass_si
     * @param garantee
     */
    public Article(String title, String manufacturer, String name, double mass_si, String garantee) {
        this.title = title;
        this.manufacturer = manufacturer;
        this.name = name;
        this.mass_si = mass_si;
        this.garantee = garantee;
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
                this.garantee,
                this.mass_si,
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
            if (this.mass_si != anotherArticle.mass_si)
                return false;
            // если они оба null, то считается как соответвие, и проверка идёт дальше
            if (this.garantee != null && anotherArticle.garantee != null) { //проверка на null
                if (!this.garantee.equals(anotherArticle.garantee)) //проверка на соответствие
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
