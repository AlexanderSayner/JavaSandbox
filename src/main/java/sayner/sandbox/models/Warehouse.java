package sayner.sandbox.models;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Класс представляет собой филиал птовар на склад
 * В базе соответствующая таблица называется
 * Warehouse
 *
 * #4
 */
public class Warehouse {

    /**
     * Индентификатор БД
     */
    private int id;

    /**
     * Отледение, в котором находится товар
     * id_trade_departments
     */
    private TradeDepartment tradeDepartment;

    /**
     * Позиция в каталоге
     * id_articles_list
     */
    private Article article;

    /**
     * Дата прибытия на склад
     * got_to_store_date
     */
    private Date gotToStore;

    /**
     * Дата изготовления
     * production_date
     */
    private Date productionDate;

    /**
     * Дата окончания срока годности
     * expiration_date
     */
    private Date expirationDate;


    /**
     * getter'ы & setter'ы
     */
    // Сначала getter'ы
    public int getId() {
        return id;
    }

    public TradeDepartment getTradeDepartment() {
        return tradeDepartment;
    }

    public Article getArticle() {
        return article;
    }

    public Date getGotToStore() {
        return gotToStore;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    // Теперь setter'ы

    public void setTradeDepartment(TradeDepartment tradeDepartment) {
        this.tradeDepartment = tradeDepartment;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setGotToStore(Date gotToStore) {
        this.gotToStore = gotToStore;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }


    /**
     * Default конструктор
     */
    public Warehouse() {
    }


    /**
     * Конструктор для ленивых автоматически настраивает даты
     *
     * @param tradeDepartment
     * @param article
     * @param gotToStore
     */
    public Warehouse(TradeDepartment tradeDepartment, Article article, Date gotToStore) {
        this.tradeDepartment = tradeDepartment;
        this.article = article;
        this.gotToStore = gotToStore;

        DateTime dateTime = new DateTime(gotToStore);

        this.productionDate = dateTime.minusDays(1).toDate();
        this.expirationDate = dateTime.plusWeeks(1).toDate();
    }

    /**
     * Конструкор устанавливает разом все параметры, кроме id
     * т.к. он инкреминтируется автоматически на уровне СУБД
     *
     * @param tradeDepartment
     * @param article
     * @param gotToStore
     * @param productionDate
     * @param expirationDate
     */
    public Warehouse(TradeDepartment tradeDepartment, Article article, Date gotToStore, Date productionDate, Date expirationDate) {
        this.tradeDepartment = tradeDepartment;
        this.article = article;
        this.gotToStore = gotToStore;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
    }
}
