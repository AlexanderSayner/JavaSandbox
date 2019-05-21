package sayner.sandbox.models;

import javax.persistence.*;

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
    private int id;

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
    private double mass_si;

    /**
     * Гарантия
     */
    @Column
    private String garantee;


    /**
     * getter'ы & setter'ы
     */
    // Сначала getter'ы
    public int getId() {
        return id;
    }

    public String getStringId(){
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

    // Теперь setter'ы

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
}
