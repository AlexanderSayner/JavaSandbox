package sayner.sandbox.jsontemplate.jblick;

/**
 * Здесь можно настроить все необходимы поля для отображения сущности Article
 * Связь между интерфейсами и полем сущности прописывается с помощью аннотации в классе объекта,
 * а используется уже при использовании соответствуещей аннотации в контроллере
 */
public final class ArticleView {

    /**
     * Интерфейс-маркер для поля Id
     */
    public interface Id {
    }

    ;

    /**
     * Будет отображать всё то, что унаследует, так ещё и свои поля добавит
     */
    public interface IdTitle extends Id {
    }

    ;

    /**
     *
     */
    public interface IdTitleManufacturer extends IdTitle {
    }

    ;

    /**
     * Всё самое необходимое
     */
    public interface IdTitleDate extends IdTitle {
    }

    ;

    /**
     *
     */
    public interface IdTitleManufacturerName extends IdTitleManufacturer {
    }

    ;

    /**
     *
     */
    public interface IdTitleManufacturerNameGarantee extends IdTitleManufacturerName {
    }

    ;

    /**
     * Вообще всё, что только можно
     */
    public interface FullArticle extends IdTitleManufacturerNameGarantee {
    }

    ;
}
