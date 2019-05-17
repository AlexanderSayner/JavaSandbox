package sayner.sandbox.models;

import java.util.Date;
import java.util.List;

/**
 * Класс представляет собой чек
 * В базе соответствующая таблица называется
 * cash_voucher
 * <p>
 * #7
 */
public class CashVoucher {

    /**
     * Индентификатор БД
     */
    private int id;

    /**
     * Позиция в архиве продаж
     * id_sold
     */
    private Sold sold;

    /**
     * Номер чека
     * invoice_num
     */
    private int invoiceNum;

    /**
     * Дата выдачи чека
     * check_received
     */
    private Date checkReceived;

    /**
     * Список покупок
     */
    private List<Warehouse> shoppingList;


    /**
     * getter'ы & setter'ы
     */
    // Сначала getter'ы
    public int getId() {
        return id;
    }

    public Sold getSold() {
        return sold;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public Date getCheckReceived() {
        return checkReceived;
    }

    public List<Warehouse> getShoppingList() {
        return shoppingList;
    }

    // Теперь setter'ы

    public void setSold(Sold sold) {
        this.sold = sold;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public void setCheckReceived(Date checkReceived) {
        this.checkReceived = checkReceived;
    }


    /**
     * Default конструктор
     */
    public CashVoucher() {
    }

    /**
     * Нужны именно эти параметры, так как остальные можно(нужно) вычислить
     *
     * @param sold
     * @param invoiceNum
     * @param checkReceived
     */
    public CashVoucher(Sold sold, int invoiceNum, Date checkReceived) {
        this.sold = sold;
        this.invoiceNum = invoiceNum;
        this.checkReceived = checkReceived;
    }
}
