package sayner.sandbox.repositories;

/**
 * Позволяет расширить функционал обычного JpaRepository
 * @param <T> Любой тип сущности
 */
public interface JpaRepositoryCustom<T> {
    /**
     * Выгружает сущность из контекста
     * @param entity сущность
     */
    void detachTypifiedEntity(T entity);
}
