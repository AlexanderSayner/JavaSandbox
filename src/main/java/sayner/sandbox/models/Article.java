package sayner.sandbox.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.UpdateTimestamp;
import sayner.sandbox.jsontemplate.jview.ArticleView;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Класс представляет собой каталог
 * В базе соответствующая таблица называется
 * Article
 * <p>
 * #3
 */
@Entity
@Table(name = "Articles_List")
public class Article {

    /**
     * Индентификатор БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ArticleView.Id.class)
    private int id;

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
    private double mass_si;

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

    /**
     * getter'ы & setter'ы
     */
    // Сначала getter'ы
    public int getId() {
        return id;
    }

    public String getStringId() {
        return String.valueOf(getId());
    }

    public String getTitle() {
        return title;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getName() {
        return name;
    }

    public double getMass_si() {
        return mass_si;
    }

    public String getGarantee() {
        return garantee;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    // Теперь setter'ы

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMass_si(double mass_si) {
        this.mass_si = mass_si;
    }

    public void setGarantee(String garantee) {
        this.garantee = garantee;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    /**
     * Default конструктор
     */
    public Article() {
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
        this.mass_si = 1;
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
     * Устанавливает вообще всё. Очень нужен был
     *
     * @param id
     * @param title
     * @param manufacturer
     * @param name
     * @param mass_si
     * @param garantee
     */
    public Article(int id, String title, String manufacturer, String name, double mass_si, String garantee) {
        this.id = id;
        this.title = title;
        this.manufacturer = manufacturer;
        this.name = name;
        this.mass_si = mass_si;
        this.garantee = garantee;
    }


    @Override
    public String toString() {

        return "Article entity: " + this.id + ", " + this.name + ", " + this.title + ", " + this.manufacturer + ".";
    }
}
