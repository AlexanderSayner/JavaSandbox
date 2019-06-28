package sayner.sandbox.modelle;

/**
 * Класс представляет собой скидку
 * В базе соответствующая таблица называется
 * Discounts
 * <p>
 * #5
 */
public class Discount {

    /**
     * Индентификатор БД
     */
    private int id;

    /**
     * Значение скидки
     */
    private short value;

    /**
     * Описание
     */
    private String description;

    /**
     * getter'ы & setter'ы
     */
    // Сначала getter'ы
    public int getId() {
        return id;
    }

    public short getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    // Теперь setter'ы

    public void setValue(short value) {
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Default конструктор
     */
    public Discount() {
    }

    /**
     * Конструктор для ленивых, не устанавливает описание
     *
     * @param value
     */
    public Discount(short value) {
        this.value = value;
        this.description = null;
    }

    /**
     * Устанавливает все параметры, кроме id
     * т.к. он инкреминтируется автоматически на уровне СУБД
     *
     * @param value
     * @param description
     */
    public Discount(short value, String description) {
        this.value = value;
        this.description = description;
    }
}
