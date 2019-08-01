package sayner.sandbox.jsontemplate.jview;

/**
 * Здесь можно настроить все необходимы поля для отображения сущности Article
 * Связь между интерфейсами и полем сущности прописывается с помощью аннотации в классе объекта,
 * а используется уже при использовании соответствуещей аннотации в контроллере
 */
public final class ArticleViewDto {

    /**
     * Интерфейс-маркер для поля Id
     */
    public interface Id {
    }

    public interface State {
    }

    public interface Title {
    }

    public interface Manufacturer {
    }

    public interface Name {
    }

    public interface Mass {
    }

    public interface Garantee {
    }

    public interface CreationDate {
    }

    public interface UpdateDate {
    }

    public interface Warehouses {
    }

    public interface IdStateName extends Id, State, Name {
    }

    public interface IdStateTitleManufacturerNameCreationDate extends Id, State, Title, Manufacturer, Name, CreationDate, Warehouses {
    }

    public interface FullArticle extends Id, State, Title, Manufacturer, Name, Mass, Garantee, CreationDate, Warehouses {
    }
}
