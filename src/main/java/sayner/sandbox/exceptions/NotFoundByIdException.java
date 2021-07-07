package sayner.sandbox.exceptions;

public class NotFoundByIdException extends RuntimeException {

    public NotFoundByIdException() {
        super();
    }

    public NotFoundByIdException(String message) {
        super(message);
    }
}