package sayner.sandbox.modelle;

import java.util.Date;

/**
 * Класс представляет собой архив продаж
 * В базе соответствующая таблица называется
 * Sold
 * <p>
 * №6
 */
public class Sold {

    /**
     * Индентификатор БД
     */
    private int id;

    /**
     * Позиция на складе
     * id_warehouse
     */
    private Warehouse warehouse;

    /**
     * Время продажи
     * sold_date_time
     */
    private Date soldDateTime;

    /**
     * Стоимость закупки
     * Сost_price
     */
    private float costPrice;

    /**
     * Стоимость продажи
     * sale_price
     */
    private float salePrice;

    /**
     * Скидка
     * id_discounts
     */
    private Discount discount;


    /**
     * getter'ы & setter'ы
     */
    // Сначала getter'ы
    public int getId() {
        return id;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Date getSoldDateTime() {
        return soldDateTime;
    }

    public float getCostPrice() {
        return costPrice;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public Discount getDiscount() {
        return discount;
    }

    // Теперь setter'ы

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void setSoldDateTime(Date soldDateTime) {
        this.soldDateTime = soldDateTime;
    }

    public void setCostPrice(float costPrice) {
        this.costPrice = costPrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }


    /**
     * Default конструктор
     */
    public Sold() {
    }

    /**
     * Конструктор для ленивых
     *
     * @param warehouse
     * @param soldDateTime
     * @param salePrice
     */
    public Sold(Warehouse warehouse, Date soldDateTime, float salePrice) {
        this.warehouse = warehouse;
        this.soldDateTime = soldDateTime;
        this.costPrice = salePrice * 0.8f;
        this.salePrice = salePrice;
        this.discount = null;
    }

    /**
     * Конструкор устанавливает разом все параметры, кроме id
     * т.к. он инкреминтируется автоматически на уровне СУБД
     *
     * @param warehouse
     * @param soldDateTime
     * @param costPrice
     * @param salePrice
     * @param discount
     */
    public Sold(Warehouse warehouse, Date soldDateTime, float costPrice, float salePrice, Discount discount) {
        this.warehouse = warehouse;
        this.soldDateTime = soldDateTime;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.discount = discount;
    }
}
