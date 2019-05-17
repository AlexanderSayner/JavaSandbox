package sayner.sandbox.models;

/**
 * Класс представляет собой филиал магазина
 * В базе соответствующая таблица называется
 * Branch_shops
 *
 * #1
 */
public class BranchShop {

    /**
     * Индентификатор БД
     */
    private int id;

    /**
     * Регион или область страны
     */
    private String region;

    /**
     * Город магазина
     */
    private String city;

    /**
     * Улицы магазина
     */
    private String street;

    /**
     * Название данного, конкретного магазина, чтобы их можно было различить
     */
    private String name;


    /**
     * getter'ы & setter'ы
     */
    // Сначала getter'ы

    public int getId() {
        return id;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getName() {
        return name;
    }

    // Теперь setter'ы

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Default конструктор
     */
    public BranchShop() {
    }

    /**
     * Конструктор устанавливает значения улицы и названия магазина
     * Город остаётся по-умолчанию
     *
     * @param street
     * @param name
     */
    public BranchShop(String street, String name) {
        this.region = "Ульяновская обл";
        this.city = "Ульяновск";
        this.street = street;
        this.name = name;
    }

    /**
     * Конструкор устанавливает разом все параметры, кроме id
     * т.к. он инкреминтируется автоматически на уровне СУБД
     *
     * @param region
     * @param city
     * @param street
     * @param name
     */
    public BranchShop(String region, String city, String street, String name) {
        this.region = region;
        this.city = city;
        this.street = street;
        this.name = name;
    }

}
