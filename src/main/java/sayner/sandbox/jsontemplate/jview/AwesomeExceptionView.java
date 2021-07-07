package sayner.sandbox.jsontemplate.jview;

/**
 * Управление отбражением json'а с ошибкой
 */
public final class AwesomeExceptionView {

    public interface Message {
    }

    public interface FullMessage extends Message {
    }

    public interface ShowException {
    }

    public interface FullAwesomeException extends FullMessage, ShowException {
    }

}
