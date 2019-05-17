package sayner.sandbox.models;

/**
 * Класс представляет собой отделения магазина
 * В базе ссоответствующая таблица называется
 * Trade_Departments
 * <p>
 * #2
 */
public class TradeDepartment {

    /**
     * Индентификатор БД
     */
    private int id;

    /**
     * Магазин, в котором располагается отдел
     * id_branch_shops
     */
    private BranchShop branchShop;

    /**
     * Предназначение
     */
    private String appointment;


    /**
     * getter'ы & setter'ы
     */
    // Сначала getter'ы
    public int getId() {
        return id;
    }

    public BranchShop getBranchShop() {
        return branchShop;
    }

    public String getAppointment() {
        return appointment;
    }

    // Теперь setter'ы

    public void setBranchShop(BranchShop branchShop) {
        this.branchShop = branchShop;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    /**
     * Default конструктор
     */
    public TradeDepartment() {
    }

    /**
     * Конструктор устанавливает магазин, в котором располагается отдел и его название
     * т.к. он инкреминтируется автоматически на уровне СУБД
     *
     * @param branchShop
     * @param appointment
     */
    public TradeDepartment(BranchShop branchShop, String appointment) {
        this.branchShop = branchShop;
        this.appointment = appointment;
    }


}
